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

@WebServlet(
        name = "filtroLibroServlet",
        value = "/filtroLibros"
)
public class FiltroLibroServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");

        String buscarLibro = req.getParameter("buscarLibro") == null ? "" : req.getParameter("buscarLibro");

        LibroService libroService = new LibroServiceJDBCImpl((Connection) req.getAttribute("con"));
        List<Libro> libros = new ArrayList<>();
        try {
            libros = libroService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!buscarLibro.isBlank()) {
            libros = filtrarLibrosPorTitulo(libros, buscarLibro);
        }

        if (libros.isEmpty()) {
            req.setAttribute("mensaje", "No se han encontrado libros con el título: " + buscarLibro);
            try {
                libros = libroService.findAll();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        getServletContext().setAttribute("books", libros);

        req.getRequestDispatcher("/index.jsp").forward(req, resp);

    }

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
