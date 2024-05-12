package es.unex.cum.tw.controllers;

import es.unex.cum.tw.models.User;
import es.unex.cum.tw.services.LoginService;
import es.unex.cum.tw.services.LoginServiceImpl;
import es.unex.cum.tw.services.UserService;
import es.unex.cum.tw.services.UserServiceJDBCImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

import static es.unex.cum.tw.util.MetodosUtilizables.obtenerLibrosUsuario;

/**
 * Servlet que muestra los libros reservados por un usuario.
 * <ul>
 *     <li>Obtiene el usuario logueado.</li>
 *     <li>Obtiene los libros reservados por el usuario.</li>
 *     <li>Redirige a la vista mostrarLibrosReservados.jsp.</li>
 *     <li>Si no hay usuario logueado, redirige a la página de inicio.</li>
 * </ul>
 * @author Jose Luis Obiang Ela Nanguang
 * @version 1.0 12-05-2024, Sun, 11:53
 */
@WebServlet(
        name = "MostrarLibrosReservadosServlet",
        value = "/verLibrosReservados"
)
public class MostrarLibrosReservadosServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");

        String idLibro = req.getParameter("id") == null ? "" : req.getParameter("id"); // id del libro a reservar
        req.setAttribute("idLibro", idLibro);

        HttpSession session = req.getSession();
        LoginService loginService = new LoginServiceImpl();
        Optional<User> userOptional = loginService.authenticate(req); // Obtener usuario de la sesión

        if (userOptional.isPresent()) { // Si hay un usuario en la sesión
            Connection con = (Connection) req.getAttribute("con");
            UserService userService = new UserServiceJDBCImpl(con);
            Optional<User> user = Optional.empty();
            try {
                // Obtener usuario de la base de datos con el nombre de usuario y contraseña
                 user = userService.findByUsernameAndPassword(userOptional.get().getUsername(), userOptional.get().getPassword());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            obtenerLibrosUsuario(con, user, session); // Obtener libros reservados por el usuario
            req.getRequestDispatcher("/mostrarLibrosReservados.jsp").forward(req, resp);
        } else { // Si no hay usuario en la sesión
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
        }


    }
}
