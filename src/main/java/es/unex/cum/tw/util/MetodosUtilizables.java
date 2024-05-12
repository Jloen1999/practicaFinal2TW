package es.unex.cum.tw.util;

import es.unex.cum.tw.models.*;
import es.unex.cum.tw.services.LibroService;
import es.unex.cum.tw.services.LibroServiceJDBCImpl;
import es.unex.cum.tw.services.ReservaService;
import es.unex.cum.tw.services.ReservaServiceJDBCImpl;
import jakarta.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz que contiene métodos útiles para la aplicación.
 * @author Jose Luis Obiang Ela Nanguang
 * @version 1.0 12-05-2024, Sun, 12:53
 */
public interface MetodosUtilizables {
    static void obtenerLibrosUsuario(Connection conn, Optional<User> userOptional, HttpSession session) {
        ReservaService serviceReserva = null;
        LibroService libroService = null;

        try {
            serviceReserva = new ReservaServiceJDBCImpl(conn);
            libroService = new LibroServiceJDBCImpl(conn);

            List<Libro> libros = new ArrayList<>();

            List<Reserva> reservas = serviceReserva.getReservasByUser(userOptional.get());
            // Obtener el libro de cada reserva
            if(reservas.isEmpty()){
                libros.add(new LibroBuilder().setTitulo("No hay libros reservados!!").build());
            }else{
                for (Reserva reserva : reservas) {
                    Optional<Libro> libroOptional = serviceReserva.getLibroByReservaId(reserva.getIdLibro());
                    libroOptional.ifPresent(libros::add);
                }
            }

            List<Libro> booksWithPuntuacionMedia = new ArrayList<>();
            for (Libro libro : libros) {
                List<Valoracion> valoraciones = libroService.getValoracionesByBookId(libro.getIdLibro());
                float puntuacion = 0;
                for (Valoracion valoracion : valoraciones) {
                    puntuacion += valoracion.getPuntuacion();
                }
                if (!valoraciones.isEmpty()) {
                    puntuacion /= valoraciones.size();
                }

                libro.setPuntuacion(puntuacion);
                booksWithPuntuacionMedia.add(libro);

                // Actualiza puntuacion del libro en la base de datos
                libroService.updatePuntuacion(libro);

            }
            session.setAttribute("librosReservados", booksWithPuntuacionMedia);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
