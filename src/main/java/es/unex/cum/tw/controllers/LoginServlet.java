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

/**
 * Servlet que gestiona el login de los usuarios.
 * <ul>
 *     <li>Si el usuario es correcto, se le redirige a la página principal con un mensaje de bienvenida</li>
 *     <li>Si el usuario no es correcto, se le redirige a la página principal con un mensaje de error.</li>
 *     <li>Si el usuario ya está registrado, se le redirige a la página principal con un mensaje de error.</li>
 *     <li>Si el usuario es administrador, se le redirige a la página principal con un mensaje de bienvenida.</li>
 *     <li>Si el usuario no está registrado, se le redirige a la página principal con un mensaje de error.</li>
 * </ul>
 * @author Jose Luis Obiang Ela Nanguang
 * @version 1.0 12-05-2024, Sun, 11:47
 */
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
            // Por motivos de seguridad, se ha almacenado el nombre de usuario y la contraseña del administrador en el archivo de configuración config.properties
            USERNAMEADMIN = props.getProperty("USERNAME"); // Obtenemos el nombre de usuario del administrador
            PASSWORDADMIN = props.getProperty("PASSWORD"); // Obtenemos la contraseña del administrador
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
        Optional<User> userOptional = service.login(username, password); // Comprobamos si el usuario está registrado(en la base de datos)

        HttpSession session = request.getSession();
        if (userOptional.isPresent()) { // Si el usuario está registrado
            session.setAttribute("user", userOptional.get()); // Guardamos el usuario en la sesión
            request.setAttribute("mensaje", "Bienvenido " + userOptional.get().getUsername()); // Mostramos un mensaje de bienvenida

            obtenerLibrosUsuario(conn, userOptional, session); // Obtenemos los libros del usuario

            if (username.equals(USERNAMEADMIN) && password.equals(PASSWORDADMIN)) { // Si el usuario es el administrador
                request.setAttribute("mensaje", "Bienvenido administrador");
                session.setAttribute("admin", true);
            }

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
            try {
                dispatcher.forward(request, response);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            }

        } else { // Si el usuario no está registrado
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
        Optional<User> userOptional = loginService.authenticate(request); // Obtener el usuario de la sesión

        if (userOptional.isPresent()) { // Si el usuario está registrado
                obtenerLibrosUsuario(conn, userOptional, request.getSession()); // Obtenemos los libros del usuario
                request.setAttribute("mensaje", "Error, el usuario " + userOptional.get().getUsername() + " ya está registrado en la base de datos");
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
                try {
                    dispatcher.forward(request, response);
                } catch (ServletException e) {
                    throw new RuntimeException(e);
                }

        } else { // Si el usuario no está registrado
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
