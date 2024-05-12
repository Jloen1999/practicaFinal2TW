package es.unex.cum.tw.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet que gestiona las acciones de inicio de sesión y registro de usuario
 * @author Jose Luis Obiang Ela Nanguang
 * @version 1.0 12-05-2024, Sun, 12:34
 * @see jakarta.servlet.http.HttpServlet
 * @see jakarta.servlet.annotation.WebServlet
 * @see java.io.IOException
 * @since 1.0

 */
@WebServlet(
        name = "ActionServlet",
        value = "/actionSignInUp"
)
public class ActionSignInUpServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");

        String action = request.getParameter("accion"); // login o signup

        if (action != null && !action.isBlank()) { // Si la acción no es nula ni vacía
            RequestDispatcher requestDispatcher = null;
            switch (action) { // Dependiendo de la acción, se redirige a una página u otra
                case "login":
                    requestDispatcher = getServletContext().getRequestDispatcher("/login"); // Redirige a la página de login
                    try {
                        requestDispatcher.forward(request, response);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "signup": // Si la accion es signup
                    HttpSession session = request.getSession();
                    session.setAttribute("username", request.getParameter("username"));
                    session.setAttribute("password", request.getParameter("password"));
                    if(session.getAttribute("isSignUp") == null){
                        session.setAttribute("isSignUp", true); // Se marca la sesión como registro si no se ha registrado anteriormente
                        requestDispatcher = getServletContext().getRequestDispatcher("/index.jsp"); // Redirige a la página principal de la aplicación y mostrará el formulario de registro
                    }else{
                        requestDispatcher = getServletContext().getRequestDispatcher("/registro"); // Redirige al servlet de registro para que se registre el usuario
                    }

                    try {
                        requestDispatcher.forward(request, response);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                default:
                    response.getWriter().println("<h1 style=\"red\">Acción no válida</h1>");
            }
        } else {
            response.getWriter().println("<h1 style='red'>Debes introducir una acción</h1>");
        }

    }
}
