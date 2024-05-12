package es.unex.cum.tw.controllers;

import es.unex.cum.tw.models.User;
import es.unex.cum.tw.services.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

import static es.unex.cum.tw.util.MetodosUtilizables.obtenerLibrosUsuario;

/**
 * Servlet que se encarga de reservar un libro
 * @author Jose Luis Obiang Ela Nanguang
 * @version 1.0 12-05-2024, Sun, 12:02
 */
@WebServlet(
        name = "reservarLibroServlet",
        value = "/reservarLibro"
)
public class ReservarLibroServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");

        Connection con = (Connection) request.getAttribute("con");
        String idLibro = request.getParameter("id"); // id del libro a reservar
        if(idLibro != null){ // si el id del libro no es nulo
            LoginService loginService = new LoginServiceImpl();
            Optional<User> userOptional = loginService.authenticate(request);
            UserService userService = new UserServiceJDBCImpl(con);

            if(userOptional.isPresent()){ // si el usuario esta autenticado
                ReservaService reservaService = null;
                try {
                    // Obtener usuario de la base de datos
                    userOptional = userService.findByUsernameAndPassword(userOptional.get().getUsername(), userOptional.get().getPassword());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                try {
                    reservaService = new ReservaServiceJDBCImpl(con);

                    if(reservaService.addLibroToReserva(userOptional.get(), Integer.parseInt(idLibro))){ // si se ha reservado el libro
                        request.setAttribute("mensaje", "Libro reservado correctamente");
                        obtenerLibrosUsuario(con, userOptional, request.getSession()); // obtener libros del usuario
                    }else{
                        request.setAttribute("mensaje", "Error al reservar el libro");
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }else{ // si el usuario no esta autenticado
                request.setAttribute("mensaje", "Usuario no autenticado");
            }

        }

        // Redirigir a la pagina principal que es donde se está reservando el libro
        request.getRequestDispatcher("/index.jsp").forward(request, response);

    }
}
