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
import java.util.List;

/**
 * Se encarga de recibir los usuarios a eliminar y eliminarlos de la base de datos.
 * Además, actualiza la lista de usuarios en la sesión y redirige a la página de administrador.
 * <ul>
 *     <li>Si no se ha seleccionado ningún usuario, no se elimina ninguno y se muestra un mensaje de error.</li>
 *     <li>Si se han seleccionado usuarios, se eliminan y se muestra un mensaje con los usuarios eliminados.</li>
 * </ul>
 *
 * @author Jose Luis Obiang Ela Nanguang
 * @version 1.0 12-05-2024, Sun, 11:37
 *
 *
 */
@WebServlet(
        name = "EliminarUsuarioServlet",
        value = "/eliminarUsuario"
)
public class EliminarUsuarioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");

        if(req.getParameterValues("eliminarUsuarios") != null && req.getParameterValues("eliminarUsuarios").length != 0) { // Si se han seleccionado usuarios
            Connection con = (Connection) req.getAttribute("con");
            String[] usuariosAEliminar = req.getParameterValues("eliminarUsuarios"); // Usuarios a eliminar
            UserService userService = new UserServiceJDBCImpl(con);

           StringBuilder usuariosEliminados = new StringBuilder("[");

            for (String usuario : usuariosAEliminar) {
                try {
                    User user = userService.findUserById(Integer.parseInt(usuario)).get(); // Obtener usuario de la base de datos por su id
                    if (userService.deleteUserById(Integer.parseInt(usuario))) { // Eliminar usuario
                        usuariosEliminados.append(user.getUsername()).append(", ");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            usuariosEliminados.append("]");

            // Mostrar mensaje
            if(usuariosEliminados.length() > 2) {
                req.setAttribute("mensaje", "Usuarios eliminados: " + usuariosEliminados.toString());
            }else{
                req.setAttribute("mensaje", "No se ha podido eliminar ningún usuario");
            }

            List<User> users = null;
            try {
                // Actualizar lista de usuarios en la sesión
                users = userService.findAll();
                req.getSession().setAttribute("users", users);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        // Redirigir a la página de administrador
        req.getRequestDispatcher("/admin.jsp").forward(req, resp);


    }
}
