package es.unex.cum.tw.controllers;


import es.unex.cum.tw.models.Libro;
import es.unex.cum.tw.services.LibroService;
import es.unex.cum.tw.services.LibroServiceJDBCImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet que se encarga de filtrar los libros por su título.
 * <ul>
 *     <li>Este servlet recibe un parámetro por GET llamado "buscarLibro" que se utiliza para filtrar los libros por su título.</li>
 *     <li>Si no se recibe ningún parámetro, se muestran todos los libros.</li>
 *     <li>Si no se encuentran libros con el título recibido, se muestra un mensaje de error y se muestran todos los libros.</li>
 *     <li>Se almacenan los libros filtrados en el contexto de la aplicación.</li>
 *     <li>Se redirige a la página principal.</li>
 * </ul>
 * @author Jose Luis Obiang Ela Nanguang
 * @version 1.0 12-05-2024, Sun, 11:45
 */
@WebServlet(
        name = "filtroLibroServlet",
        value = "/filtroLibros"
)
public class FiltroLibroServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");

        // Recuperamos el parámetro de búsqueda
        String buscarLibro = req.getParameter("buscarLibro") == null ? "" : req.getParameter("buscarLibro");

        LibroService libroService = new LibroServiceJDBCImpl((Connection) req.getAttribute("con"));
        List<Libro> libros = new ArrayList<>();
        try {
            libros = libroService.findAll(); // Obtenemos todos los libros
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (!buscarLibro.isBlank()) { // Si se ha introducido un título, filtramos los libros
            libros = filtrarLibrosPorTitulo(libros, buscarLibro);
        }

        if (libros.isEmpty()) { // Si no se han encontrado libros, mostramos un mensaje de error
            req.setAttribute("mensaje", "No se han encontrado libros con el título: " + buscarLibro);
            try {
                libros = libroService.findAll(); // Obtenemos todos los libros
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        getServletContext().setAttribute("books", libros); // Almacenamos los libros en el contexto de la aplicación

        req.getRequestDispatcher("/index.jsp").forward(req, resp);

    }

    /**
     * Filtra los libros por su título.
     * @param libros Lista de libros a filtrar.
     * @param titulo Título por el que filtrar los libros.
     * @return Lista de libros filtrados por el título.
     */
    private List<Libro> filtrarLibrosPorTitulo(List<Libro> libros, String titulo) {
        List<Libro> librosFiltrados = new ArrayList<>();
        for (Libro libro : libros) {
            if (libro.getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
                librosFiltrados.add(libro);
            }
        }
        return librosFiltrados;
    }

}
