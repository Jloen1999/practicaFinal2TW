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

/**
 * Servlet que muestra los libros disponibles de la base de datos.
 * <ul>
 *     <li>Si el usuario es administrador, se le permite añadir y eliminar libros.</li>
 *     <li>Si el usuario no es administrador, se le permite ver los libros, reservar y valorarlos.</li>
 *     <li>Se muestra la puntuación media de cada libro.</li>
 * </ul>
 * @author Jose Luis Obiang Ela Nanguang
 * @version 1.0 12-05-2024, Sun, 11:57
 */
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
        Optional<User> userOptional = loginService.authenticate(req); // Obtener usuario de la sesión
        if (userOptional.isPresent()) { // Si el usuario está logueado

            if (userOptional.get().getUsername() != null && userOptional.get().getPassword() != null) { // Si el usuario es administrador
                if (userOptional.get().getUsername().equals(USERNAMEADMIN) && userOptional.get().getPassword().equals(PASSWORDADMIN)) {
                    req.setAttribute("admin", true); // Permitir añadir y eliminar libros
                }
            }

        }

        Connection con = (Connection) req.getAttribute("con");
        LibroService libroService = new LibroServiceJDBCImpl(con);
        try {
            List<Libro> libros = libroService.findAll(); // Obtener todos los libros de la base de datos
            List<Libro> booksWithPuntuacionMedia = new ArrayList<>(); // Lista de libros con la puntuación media actualizada
            for (Libro libro : libros) {
                List<Valoracion> valoraciones = libroService.getValoracionesByBookId(libro.getIdLibro()); // Obtener valoraciones de un libro en concreto
                float puntuacion = 0;
                for (Valoracion valoracion : valoraciones) { // Calcular puntuación media
                    puntuacion += valoracion.getPuntuacion();
                }
                if (!valoraciones.isEmpty()) { // Si hay valoraciones
                    puntuacion /= valoraciones.size();
                }

                libro.setPuntuacion(puntuacion); // Actualizar puntuación del libro en la lista
                booksWithPuntuacionMedia.add(libro);

                // Actualiza puntuacion del libro en la base de datos
                libroService.updatePuntuacion(libro);

            }

            if (userOptional.isPresent()) { // Si el usuario está logueado
                UserService userService = new UserServiceJDBCImpl(con);
                try {
                    // Obtener usuario de la base de datos
                    userOptional = userService.findByUsernameAndPassword(userOptional.get().getUsername(), userOptional.get().getPassword());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                // Obtener libros del usuario
                obtenerLibrosUsuario(con, userOptional, req.getSession());
            }

            req.getSession().setAttribute("libros", booksWithPuntuacionMedia);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        req.getRequestDispatcher("/mostrarLibros.jsp").forward(req, resp);


    }
}
