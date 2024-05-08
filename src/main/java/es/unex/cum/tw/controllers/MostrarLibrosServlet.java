package es.unex.cum.tw.controllers;

import es.unex.cum.tw.models.Libro;
import es.unex.cum.tw.models.User;
import es.unex.cum.tw.models.Valoracion;
import es.unex.cum.tw.services.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import static es.unex.cum.tw.util.MetodosUtilizables.obtenerLibrosUsuario;

@WebServlet(
        name = "MostrarLibrosServlet",
        value = "/verLibros"
)
public class MostrarLibrosServlet extends HttpServlet {

    private Properties props;
    private String USERNAMEADMIN;
    private String PASSWORDADMIN;

    @Override
    public void init() throws ServletException {
        super.init();
        props = new Properties();
        try {
            props.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
            USERNAMEADMIN = props.getProperty("USERNAME");
            PASSWORDADMIN = props.getProperty("PASSWORD");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");

        LoginService loginService = new LoginServiceImpl();
        Optional<User> userOptional = loginService.authenticate(req);
        if (userOptional.isPresent()) {

            if (userOptional.get().getUsername() != null && userOptional.get().getPassword() != null) {
                if (userOptional.get().getUsername().equals(USERNAMEADMIN) && userOptional.get().getPassword().equals(PASSWORDADMIN)) {
                    req.setAttribute("admin", true);
                }
            }

        }

        Connection con = (Connection) req.getAttribute("con");
        LibroService libroService = new LibroServiceJDBCImpl(con);
        try {
            List<Libro> libros = libroService.findAll();
            List<Libro> booksWithPuntuacionMedia = new ArrayList<>();
            for (Libro libro : libros) {
                List<Valoracion> valoraciones = libroService.getValoracionesByBookId(libro.getIdLibro());
                float puntuacion = 0;
                for (Valoracion valoracion : valoraciones) {
                    puntuacion += valoracion.getPuntuacion();
                }
                if (!valoraciones.isEmpty()) {
                    puntuacion /= valoraciones.size();
                }

                libro.setPuntuacion(puntuacion);
                booksWithPuntuacionMedia.add(libro);

                // Actualiza puntuacion del libro en la base de datos
                libroService.updatePuntuacion(libro);

            }

            if (userOptional.isPresent()) {
                UserService userService = new UserServiceJDBCImpl(con);
                try {
                    userOptional = userService.findByUsernameAndPassword(userOptional.get().getUsername(), userOptional.get().getPassword());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                obtenerLibrosUsuario(con, userOptional, req.getSession());
            }

            req.getSession().setAttribute("libros", booksWithPuntuacionMedia);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        req.getRequestDispatcher("/mostrarLibros.jsp").forward(req, resp);


    }
}
