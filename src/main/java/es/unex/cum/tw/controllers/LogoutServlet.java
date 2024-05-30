package es.unex.cum.tw.controllers;

import es.unex.cum.tw.models.User;
import es.unex.cum.tw.services.LoginService;
import es.unex.cum.tw.services.LoginServiceImpl;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Optional;

/**
 * Servlet que se encarga de cerrar la sesión de un usuario.
 * @author Jose Luis Obiang Ela Nanguang
 * @version 1.0 12-05-2024, Sun, 11:51
 */
@WebServlet(
        name = "LogoutServlet",
        value = "/logout"
)
public class LogoutServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");

        LoginService auth = new LoginServiceImpl();
        Optional<User> userOptional = auth.authenticate(request); // Obtener el usuario de la sesión

        // Enviar un atributo a todas las vistas jsp

        if (userOptional.isPresent()) { // Si el usuario está logueado
            HttpSession session = request.getSession();
            session.invalidate(); // Invalidar la sesión
        }

        response.sendRedirect(request.getContextPath() + "/index.jsp");

    }

}
