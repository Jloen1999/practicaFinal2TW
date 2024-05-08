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

@WebServlet(
        name = "EliminarUsuarioServlet",
        value = "/eliminarUsuario"
)
public class EliminarUsuarioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");

        if(req.getParameterValues("eliminarUsuarios") != null && req.getParameterValues("eliminarUsuarios").length != 0) {
            Connection con = (Connection) req.getAttribute("con");
            String[] usuariosAEliminar = req.getParameterValues("eliminarUsuarios");
            UserService userService = new UserServiceJDBCImpl(con);

           StringBuilder usuariosEliminados = new StringBuilder("[");

            for (String usuario : usuariosAEliminar) {
                try {
                    User user = userService.findUserById(Integer.parseInt(usuario)).get();
                    if (userService.deleteUserById(Integer.parseInt(usuario))) {
                        usuariosEliminados.append(user.getUsername()).append(", ");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            usuariosEliminados.append("]");

            if(usuariosEliminados.length() > 2) {
                req.setAttribute("mensaje", "Usuarios eliminados: " + usuariosEliminados.toString());
            }else{
                req.setAttribute("mensaje", "No se ha podido eliminar ningún usuario");
            }

            List<User> users = null;
            try {
                users = userService.findAll();
                req.getSession().setAttribute("users", users);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        req.getRequestDispatcher("/admin.jsp").forward(req, resp);


    }
}
