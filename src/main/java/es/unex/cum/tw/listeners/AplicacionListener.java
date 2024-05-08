package es.unex.cum.tw.listeners;

import es.unex.cum.tw.models.Libro;
import es.unex.cum.tw.models.User;
import es.unex.cum.tw.models.UserBuilder;
import es.unex.cum.tw.models.Valoracion;
import es.unex.cum.tw.services.LibroService;
import es.unex.cum.tw.services.LibroServiceJDBCImpl;
import es.unex.cum.tw.util.ConexionBD_DSPool;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebListener()
public class AplicacionListener implements ServletContextListener, ServletRequestListener, HttpSessionListener {

    private ServletContext servletContext;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        servletContext = sce.getServletContext();
        servletContext.log("Iniciando la aplicación");

        try (Connection conn = ConexionBD_DSPool.getConexionBD()) {
            LibroService libroService = new LibroServiceJDBCImpl(conn);
            List<Libro> books = libroService.findAll();
            List<Libro> newBooks = new ArrayList<>();
            List<Libro> booksWithPuntuacionMedia = new ArrayList<>();
            for (Libro libro : books) {
                List<Valoracion> valoraciones = libroService.getValoracionesByBookId(libro.getIdLibro());
                float puntuacion = 0;
                for (Valoracion valoracion : valoraciones) {
                    puntuacion += valoracion.getPuntuacion();
                }
                if (!valoraciones.isEmpty()) {
                    puntuacion /= valoraciones.size();
                }

                libro.setPuntuacion(puntuacion);

                // Actualiza puntuacion del libro en la base de datos
                libroService.updatePuntuacion(libro);

                booksWithPuntuacionMedia.add(libro);
                if (libro.isNovedad()) {
                    newBooks.add(libro);
                }

                servletContext.setAttribute("books", booksWithPuntuacionMedia);
                servletContext.setAttribute("newBooks", newBooks);
            }


        } catch (SQLException e) {
            servletContext.log("Error al cargar libros desde la base de datos: " + e.getMessage());
        }
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        sre.getServletContext().log("Petición recibida desde la IP: " + sre.getServletRequest().getRemoteAddr());
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        servletContext.log("Sesión creada: " + se.getSession().getId());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        sce.getServletContext().log("Destruyendo la aplicación!");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        sre.getServletContext().log("Petición finalizada");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        servletContext.log("Sesión destruida: " + se.getSession().getId());
    }
}
