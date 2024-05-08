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
 * Servlet que elimina un libro reservado por un usuario
 * @author Jose Luis Obiang Ela Nanguang
 * @version 1.0 06/05/2024
 *
 */
@WebServlet(
        name = "eliminarReservaLibroServlet",
        value = "/eliminarReservaLibro"
)
public class EliminarReservaLibroServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");

        Connection con = (Connection) request.getAttribute("con"); // Obtenemos la conexion almacenada en el request
        String idLibro = request.getParameter("id"); // id del libro a eliminar de la reserva

        if(idLibro != null){ // Si el id del libro no es nulo
            LoginService loginService = new LoginServiceImpl();
            Optional<User> userOptional = loginService.authenticate(request);

            UserService userService = new UserServiceJDBCImpl(con);
            if(userOptional.isPresent()){ // Si el usuario esta autenticado
                ReservaService reservaService = null;
                try {
                    reservaService = new ReservaServiceJDBCImpl(con); // Instanciamos un objeto de ReservaService para eliminar el libro de la reserva
                    if(reservaService.dropLibroFromReserva(Integer.parseInt(idLibro))){ // Si se elimina el libro de la reserva
                        // Mostramos un mensaje de exito
                        request.setAttribute("mensaje", "Libro eliminado de la reserva correctamente");
                        // Obtenemos el usuario autenticado
                        userOptional = userService.findByUsernameAndPassword(userOptional.get().getUsername(), userOptional.get().getPassword());
                        // Obtenemos los libros reservados por el usuario
                        obtenerLibrosUsuario(con, userOptional, request.getSession());
                    }else{ // Si no se elimina el libro de la reserva
                        request.setAttribute("mensaje", "Error al eliminar el libro de la reserva");
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }else{
                request.setAttribute("mensaje", "Usuario no autenticado");
            }

        }

        // Redirigimos a la pagina principal
        request.getRequestDispatcher("/index.jsp").forward(request, response);

    }
}
