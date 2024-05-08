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
import java.util.Arrays;
import java.util.Optional;

import static es.unex.cum.tw.util.MetodosUtilizables.obtenerLibrosUsuario;

@WebServlet(
        name = "reservarLibroServlet",
        value = "/reservarLibro"
)
public class ReservarLibroServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");

        Connection con = (Connection) request.getAttribute("con");
        String idLibro = request.getParameter("id");
        if(idLibro != null){
            LoginService loginService = new LoginServiceImpl();
            Optional<User> userOptional = loginService.authenticate(request);
            UserService userService = new UserServiceJDBCImpl(con);

            if(userOptional.isPresent()){
                ReservaService reservaService = null;
                try {
                    userOptional = userService.findByUsernameAndPassword(userOptional.get().getUsername(), userOptional.get().getPassword());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                try {
                    reservaService = new ReservaServiceJDBCImpl(con);

                    if(reservaService.addLibroToReserva(userOptional.get(), Integer.parseInt(idLibro))){
                        request.setAttribute("mensaje", "Libro reservado correctamente");
                        obtenerLibrosUsuario(con, userOptional, request.getSession());
                    }else{
                        request.setAttribute("mensaje", "Error al reservar el libro");
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }else{
                request.setAttribute("mensaje", "Usuario no autenticado");
            }

        }

        request.getRequestDispatcher("/index.jsp").forward(request, response);

    }
}
