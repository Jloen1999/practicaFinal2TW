package es.unex.cum.tw.controllers;

import es.unex.cum.tw.models.User;
import es.unex.cum.tw.models.UserBuilder;
import es.unex.cum.tw.services.UserService;
import es.unex.cum.tw.services.UserServiceJDBCImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;

/**
 * Servlet que se encarga de crear un usuario en la base de datos
 * @author Jose Luis Obiang Ela Nanguang
 * @version 1.0 06/05/2024
 * @see jakarta.servlet.http.HttpServlet
 * @see jakarta.servlet.annotation.WebServlet
 * @see jakarta.servlet.http.HttpServletRequest
 * @see jakarta.servlet.http.HttpServletResponse
 * @see java.io.IOException
 * @see java.sql.Connection
 * @see es.unex.cum.tw.models.User
 * @see es.unex.cum.tw.models.UserBuilder
 * @see es.unex.cum.tw.services.UserService
 * @see es.unex.cum.tw.services.UserServiceJDBCImpl
 * @since JDK 21
 * @since Tomcat 10
 * @since JSP
 * @since MySQL 8
 */
@WebServlet(
        name = "CrearUsuarioServlet",
        value = "/crearUsuario"
)
public class CrearUsuarioServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");

        // Recogemos los datos del formulario para crear un usuario
        String name = req.getParameter("name");
        String lastname = req.getParameter("lastname");
        String email = req.getParameter("email");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        UserService userService = new UserServiceJDBCImpl((Connection) req.getAttribute("con"));
        try {
            // Creamos el usuario con los datos recogidos del formulario
            User user = new UserBuilder().setNombre(name).setApellidos(lastname).setEmail(email).setUsername(username).setPassword(password).build();
            if (userService.save(user)) { // Guardamos el usuario en la base de datos
                req.setAttribute("mensaje", "Usuario creado correctamente");
            } else { // Si no se ha podido guardar el usuario en la base de datos porque ya existe o por otro motivo
                req.setAttribute("mensaje", "Error al crear el usuario");
            }

            // Volvemos a la página de CrearUsuario.jsp para poder crear otro usuario
            req.getRequestDispatcher("/CrearUsuario.jsp").forward(req, resp);

        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
