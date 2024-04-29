package es.unex.cum.tw.controllers;

import es.unex.cum.tw.models.User;
import es.unex.cum.tw.services.LoginService;
import es.unex.cum.tw.services.LoginServiceImpl;
import es.unex.cum.tw.services.UserService;
import es.unex.cum.tw.services.UserServiceJDBCImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Optional;

@WebServlet(
        name = "ActionServlet",
        value = "/action"
)
public class ActionServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");

        String action = request.getParameter("accion");


        if (action != null && !action.isBlank()) {
            RequestDispatcher requestDispatcher = null;
            switch (action) {
                case "login":
                    requestDispatcher = getServletContext().getRequestDispatcher("/login");
                    try {
                        requestDispatcher.forward(request, response);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "signup":
                    HttpSession session = request.getSession();
                    session.setAttribute("username", request.getParameter("username"));
                    session.setAttribute("password", request.getParameter("password"));
                    if(session.getAttribute("isSignUp") == null){
                        session.setAttribute("isSignUp", true);
                        requestDispatcher = getServletContext().getRequestDispatcher("/index.jsp");
                    }else{
                        requestDispatcher = getServletContext().getRequestDispatcher("/registro");
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
