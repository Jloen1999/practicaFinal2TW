package es.unex.cum.tw.controllers;

import es.unex.cum.tw.models.Libro;
import es.unex.cum.tw.models.Reserva;
import es.unex.cum.tw.models.User;
import es.unex.cum.tw.models.UserBuilder;
import es.unex.cum.tw.services.ReservaService;
import es.unex.cum.tw.services.ReservaServiceJDBCImpl;
import es.unex.cum.tw.services.UserService;
import es.unex.cum.tw.services.UserServiceJDBCImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

@WebServlet(
        name = "LoginServlet",
        value = "/login"
)
public class LoginServlet extends HttpServlet {
    private Properties props;
    private String USERNAMEADMIN;
    private String PASSWORDADMIN;
    private static String username;
    private static String password;

    @Override
    public void init() throws ServletException {
        super.init();
        props = new Properties();
        try {
            props.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
            USERNAMEADMIN = props.getProperty("USERNAME");
            PASSWORDADMIN = props.getProperty("PASSWORD");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        username = "";
        password = "";
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");

        username = request.getParameter("username");
        password = request.getParameter("password");

        Connection conn = (Connection) request.getAttribute("con");
        UserService service = new UserServiceJDBCImpl(conn);
        Optional<User> userOptional = service.login(username, password);

        HttpSession session = request.getSession();
        if (userOptional.isPresent()) {
            session.setAttribute("user", userOptional.get());
            request.setAttribute("mensaje", "El usuario " + userOptional.get().getUsername() + " ya está registrado en la base de datos");

            ReservaService serviceReserva = new ReservaServiceJDBCImpl(conn);
            try {
                List<Reserva> reservas = serviceReserva.getReservasByUserId(userOptional.get());
                List<Libro> libros = new ArrayList<>();
                session.setAttribute("reservas", reservas);
                // Obtener el libro de cada reserva
                for (Reserva reserva : reservas) {
                    Optional<Libro> libroOptional = serviceReserva.getLibroByReservaId(reserva.getIdLibro());
                    libroOptional.ifPresent(libros::add);
                }
                session.setAttribute("libros", libros);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


            if (username.equals(USERNAMEADMIN) && password.equals(PASSWORDADMIN)) {

            } else {


            }

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
            try {
                dispatcher.forward(request, response);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            }

        } else {
            request.setAttribute("mensaje", "Usuario no registrado, por favor regístrese");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
            try {
                dispatcher.forward(request, response);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            }

        }


    }


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        username = request.getParameter("username");
        password = request.getParameter("password");

        Connection conn = (Connection) request.getAttribute("con");
        UserService service = new UserServiceJDBCImpl(conn);
        Optional<User> userOptional = service.login(username, password);

        if (userOptional.isPresent()) {
            if (userOptional.get().getUsername().equals(USERNAMEADMIN) && userOptional.get().getPassword().equals(PASSWORDADMIN)) {

            } else {
                HttpSession session = request.getSession();
                session.setAttribute("user", userOptional.get());
                request.setAttribute("mensaje", "El usuario " + userOptional.get().getUsername() + " ya está registrado en la base de datos");
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
                try {
                    dispatcher.forward(request, response);
                } catch (ServletException e) {
                    throw new RuntimeException(e);
                }

            }
        } else {
            request.setAttribute("mensaje", "Usuario no registrado");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
            try {
                dispatcher.forward(request, response);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            }

        }

    }
}
