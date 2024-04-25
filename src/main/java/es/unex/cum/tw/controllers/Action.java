package es.unex.cum.tw.controllers;

import es.unex.cum.tw.services.LoginService;
import es.unex.cum.tw.services.LoginServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(
        name = "Action",
        value = "/action"
)
public class Action extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        String action = request.getParameter("accion");
        if (action != null && !action.isBlank()) {
            switch (action) {
                case "anadir":
                    RequestDispatcher dispatcherAnadir = request.getRequestDispatcher("/carta/anadir");
                    try {
                        dispatcherAnadir.forward(request, response);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case "listar":
                    RequestDispatcher dispatcherListar = request.getRequestDispatcher("/carta/listar");
                    try {
                        dispatcherListar.forward(request, response);
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
