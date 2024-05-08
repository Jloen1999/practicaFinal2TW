package es.unex.cum.tw.controllers;

import es.unex.cum.tw.models.User;
import es.unex.cum.tw.services.LoginService;
import es.unex.cum.tw.services.LoginServiceImpl;
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
import java.util.Optional;
import java.util.Properties;

import static es.unex.cum.tw.util.MetodosUtilizables.obtenerLibrosUsuario;

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
            request.setAttribute("mensaje", "Bienvenido " + userOptional.get().getUsername());

            obtenerLibrosUsuario(conn, userOptional, session);

            if (username.equals(USERNAMEADMIN) && password.equals(PASSWORDADMIN)) {
                request.setAttribute("mensaje", "Bienvenido administrador");
                session.setAttribute("admin", true);
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
        Connection conn = (Connection) request.getAttribute("con");
        LoginService loginService = new LoginServiceImpl();
        Optional<User> userOptional = loginService.authenticate(request);

        if (userOptional.isPresent()) {
                obtenerLibrosUsuario(conn, userOptional, request.getSession());
                request.setAttribute("mensaje", "Error, el usuario " + userOptional.get().getUsername() + " ya está registrado en la base de datos");
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
                try {
                    dispatcher.forward(request, response);
                } catch (ServletException e) {
                    throw new RuntimeException(e);
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
