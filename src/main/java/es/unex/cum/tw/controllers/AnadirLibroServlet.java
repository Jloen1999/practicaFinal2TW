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

/**
 * Servlet que añade un libro a la base de datos
 * @author Jose Luis Obiang Ela Nanguang
 * @version 1.0 06/05/2024
 * @see jakarta.servlet.http.HttpServlet
 * @see jakarta.servlet.annotation.WebServlet
 * @see java.io.IOException
 * @see java.sql.Connection
 * @see es.unex.cum.tw.models.Libro
 * @see es.unex.cum.tw.services.LibroService
 * @see es.unex.cum.tw.services.LibroServiceJDBCImpl
 * @since 1.0
 */
@WebServlet(
        name = "anadirLibroServlet",
        value = "/anadirLibro"
)
public class AnadirLibroServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");

        // Se obtiene los valores de los campos del formulario para añadir un libro
        String titulo = req.getParameter("titulo");
        String autor = req.getParameter("autor");
        String descripcion = req.getParameter("descripcion");
        String urlImagen = req.getParameter("imagenLibro");
        String tematica = req.getParameter("tematica");
        boolean novedad = req.getParameter("novedad") != null && req.getParameter("novedad").equals("on");

        LibroService libroService = null;

        try {
            libroService = new LibroServiceJDBCImpl((Connection) req.getAttribute("con"));
            // Se crea un objeto libro con los valores obtenidos del formulario
            Libro libro = new Libro(
                    titulo,
                    autor,
                    tematica,
                    urlImagen,
                    novedad,
                    descripcion
            );

            if (libroService.addBook(libro)) { // Si se añade el libro correctamente
                req.setAttribute("mensaje", "Libro añadido correctamente");
            } else { // Si no se añade el libro correctamente porque ya existe o por otro motivo
                req.setAttribute("mensaje", "Error al añadir el libro");
            }

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("mensaje", "Error de conexión a la base de datos");
        }

        // Finalmente se redirige nuevamente a la página de añadir libro
        req.getRequestDispatcher("/AnadirLibro.jsp").forward(req, resp);



    }
}
