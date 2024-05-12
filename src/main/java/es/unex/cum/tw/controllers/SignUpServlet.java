package es.unex.cum.tw.controllers;

import es.unex.cum.tw.models.User;
import es.unex.cum.tw.models.UserBuilder;
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
import java.util.Optional;


/**
 * Servlet que se encarga de registrar a un usuario en la base de datos.
 * <ul>
 *     <li>Si el usuario no está registrado, se registra en la base de datos.</li>
 *     <li>Si el usuario ya está registrado, se muestra un mensaje de error.</li>
 * </ul>
 */
@WebServlet(
        name = "RegistroServlet",
        value = "/registro"
)
public class SignUpServlet extends HttpServlet {
    private static String username;
    private static String password;

    @Override
    public void init() throws ServletException {
        super.init();

        username = "";
        password = "";
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");

        // Obtenemos los parámetros del formulario
        username = request.getParameter("username");
        password = request.getParameter("password");

        Connection conn = (Connection) request.getAttribute("con");
        UserService service = new UserServiceJDBCImpl(conn);
        Optional<User> userOptional = service.login(username, password); // Comprobamos si el usuario ya está registrado

        HttpSession session = request.getSession();
        if (userOptional.isEmpty()) { // Si el usuario no está registrado
            // Obtenemos los parámetros del formulario para registrar al usuario
            String name = request.getParameter("name");
            String lastname = request.getParameter("lastname");
            String email = request.getParameter("email");

            if (name == null || name.isBlank() || lastname == null || lastname.isBlank() || email == null || email.isBlank()) {
                request.setAttribute("mensaje", "Debes rellenar todos los campos");
            }

            UserService userService = new UserServiceJDBCImpl(conn);
            // Creamos el usuario
            User user = new UserBuilder()
                    .setUsername(username)
                    .setPassword(password)
                    .setNombre(name)
                    .setApellidos(lastname)
                    .setEmail(email)
                    .build();

            try {
                // Guardamos el usuario en la base de datos
                if (!userService.save(user)) {
                    request.setAttribute("mensaje", "Error al registrar el usuario");
                }else{
                    session.setAttribute("user", user);
                    request.setAttribute("mensaje", "Usuario registrado correctamente");
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        } else {
            request.setAttribute("mensaje", "Error, el usuario " + userOptional.get().getUsername() + " ya está registrado en la base de datos");
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
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
            HttpSession session = request.getSession();
            session.setAttribute("user", userOptional.get());
            request.setAttribute("mensaje", "Error, el usuario " + userOptional.get().getUsername() + " ya está registrado en la base de datos");


        } else {
            request.setAttribute("mensaje", "Usuario no registrado");
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }

    }
}
