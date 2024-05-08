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

@WebServlet(
        name = "MostrarLibrosReservadosServlet",
        value = "/verLibrosReservados"
)
public class MostrarLibrosReservadosServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");

        String idLibro = req.getParameter("id") == null ? "" : req.getParameter("id");
        req.setAttribute("idLibro", idLibro);

        HttpSession session = req.getSession();
        LoginService loginService = new LoginServiceImpl();
        Optional<User> userOptional = loginService.authenticate(req);

        if (userOptional.isPresent()) {
            Connection con = (Connection) req.getAttribute("con");
            UserService userService = new UserServiceJDBCImpl(con);
            Optional<User> user = Optional.empty();
            try {
                 user = userService.findByUsernameAndPassword(userOptional.get().getUsername(), userOptional.get().getPassword());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            obtenerLibrosUsuario(con, user, session);
            req.getRequestDispatcher("/mostrarLibrosReservados.jsp").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
        }


    }
}
