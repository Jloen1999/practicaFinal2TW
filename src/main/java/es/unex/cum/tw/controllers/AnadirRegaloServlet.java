package es.unex.cum.tw.controllers;

import es.unex.cum.tw.models.Carta;
import es.unex.cum.tw.models.Regalo;
import es.unex.cum.tw.models.User;
import es.unex.cum.tw.services.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

@WebServlet(
        name = "AnadirRegalo",
        value = "/carta/anadir"
)
public class AnadirRegaloServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        PrintWriter print = response.getWriter();

        Connection conn = (Connection) request.getAttribute("conn");
        CartaService cartaService = new CartaServiceJDBCImpl(conn);
        UserService userService = new UserServiceJDBCImpl(conn);

        String nombreRegalo = request.getParameter("regalo");
        if(nombreRegalo != null && !nombreRegalo.isEmpty()) {
            HttpSession session = request.getSession();
            Optional<User> userOptional = userService.login((String) request.getAttribute("username"), (String) request.getAttribute("password"));

            print.println("<p>Nombre del regalo: " + nombreRegalo + "</p>");
            print.println("<p>Usuario: " + userOptional.get().getUsername() + "</p>");

            Carta carta = (Carta) session.getAttribute("carta");
            try {
                carta = cartaService.findCartaByUser(userOptional.get()).orElse(carta);
                print.println("<p>Carta: " + carta.getIdCarta() + "</p>");

                Regalo regalo = new Regalo(nombreRegalo,carta.getIdCarta());
                print.println("<p>Regalo: " + regalo.getNombre() + "</p>");

                if(cartaService.addRegaloToCarta(carta, regalo)){
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/IntroducirOK.html");
                    try {
                        dispatcher.include(request, response);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/IntroducirError.html");
                    try {
                        dispatcher.include(request, response);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            } catch (SQLException e) {
                throw new ServiceJdbcException(e.getMessage(), e.getCause());
            }

        }

    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        doGet(req, res);
    }

}
