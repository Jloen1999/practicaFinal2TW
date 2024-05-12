package es.unex.cum.tw.controllers;

import es.unex.cum.tw.models.User;
import es.unex.cum.tw.services.UserService;
import es.unex.cum.tw.services.UserServiceJDBCImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 * Servlet que se encarga de mostrar la lista de usuarios registrados en la base de datos.
 * Solo puede ser accedido por el administrador.
 * @author Jose Luis Obiang Ela Nanguang
 * @version 1.0 12-05-2024, Sun, 12:34
 * @see jakarta.servlet.http.HttpServlet
 * @see jakarta.servlet.annotation.WebServlet
 * @see java.io.IOException
 * @see java.sql.SQLException
 * @see java.util.List
 * @see java.util.Properties
 * @since 1.0
 */
@WebServlet(
        name = "AdminController",
        value = "/admin"
)
public class AdminController extends HttpServlet {

    private Properties props; // Propiedades del archivo config.properties
    private String USERNAMEADMIN; // Nombre de usuario del administrador
    private String PASSWORDADMIN; // Contraseña del administrador
    @Override
    public void init() throws ServletException {
        super.init();
        props = new Properties();
        try { // Cargamos el archivo config.properties para obtener el nombre de usuario y contraseña del administrador
            props.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
            USERNAMEADMIN = props.getProperty("USERNAME");
            PASSWORDADMIN = props.getProperty("PASSWORD");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");

        UserService userService = new UserServiceJDBCImpl((Connection) req.getAttribute("con"));
        try {
            List<User> users = userService.findAll(); // Obtenemos la lista de usuarios registrados en la base de datos
            users.removeIf(user -> user.getUsername().equals(USERNAMEADMIN) && user.getPassword().equals(PASSWORDADMIN)); // Eliminamos al administrador de la lista para que no se muestre en la vista
            req.getSession().setAttribute("users", users); // Guardamos la lista de usuarios en la sesión para mostrarla en la vista admin.jsp
            req.getRequestDispatcher("/admin.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
