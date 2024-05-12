package es.unex.cum.tw.controllers;


import es.unex.cum.tw.services.LibroService;
import es.unex.cum.tw.services.LibroServiceJDBCImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;


/**
 * Clase EliminarLibros que extiende de HttpServlet e implementa un servlet que se encarga de eliminar los libros seleccionados por el usuario.
 * @author Jose Luis Obiang Ela Nanguang
 * @version 1.0 12-05-2024, Sun, 12:35
 * @since 1.0
 * @see jakarta.servlet.http.HttpServlet
 * @see jakarta.servlet.annotation.WebServlet
 * @see jakarta.servlet.http.HttpServletRequest
 * @see jakarta.servlet.http.HttpServletResponse
 * @see java.io.IOException
 * @see java.sql.Connection
 * @see es.unex.cum.tw.services.LibroService
 * @see es.unex.cum.tw.services.LibroServiceJDBCImpl
 * @see jakarta.servlet.ServletException
 *
 */
@WebServlet(
        name = "EliminarLibros",
        value = "/eliminarLibros"
)
public class EliminarLibros extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");

        // Comprobamos si se han seleccionado libros a eliminar
        if(req.getParameterValues("librosAEliminar") != null && req.getParameterValues("librosAEliminar").length != 0) {
            // Obtenemos los libros a eliminar a partir de los checkbox seleccionados, los cuales tienen como valor del ID del libro
            String[] librosAEliminar = req.getParameterValues("librosAEliminar");
            StringBuilder librosEliminados = new StringBuilder("["); // String para almacenar los libros eliminados y asi poder mostrarlos en el mensaje de confirmación
            for (String libro : librosAEliminar) { // Recorremos los id de los libros a eliminar
                try {
                    // Obtenemos el servicio de libros y eliminamos el libro a partir de su id
                    LibroService libroService = new LibroServiceJDBCImpl((Connection) req.getAttribute("con"));
                    librosEliminados.append(libroService.findById(Integer.parseInt(libro)).get().getTitulo()).append(", ");
                    libroService.deleteBookById(Integer.parseInt(libro));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            librosEliminados.append("]");
            if(librosEliminados.length() > 2) { // Si se ha eliminado algún libro, mostramos un mensaje de confirmación
                req.setAttribute("mensaje", "Libros eliminados: " + librosEliminados);
            }else{ // Si no se ha eliminado ningún libro, mostramos un mensaje de error
                req.setAttribute("mensaje", "Error, No se ha eliminado ningún libro.");
            }
        }

        // Volvemos a la vista de ver libros para mostrar los cambios realizados y poder eliminar más libros si se desea
        req.getRequestDispatcher("/verLibros").forward(req, resp);
    }
}
