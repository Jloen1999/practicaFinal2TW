package es.unex.cum.tw.services;

import es.unex.cum.tw.models.Libro;
import es.unex.cum.tw.models.Reserva;
import es.unex.cum.tw.models.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Servicio de Reserva que define los métodos que se pueden realizar sobre las reservas de libros.
 * @author Jose Luis Obiang Ela Nanguang
 * @version 1.0 12-05-2024, Sun, 12:49
 */
public interface ReservaService {
    List<Reserva> getReservasByUser(User user) throws SQLException;
    Optional<Libro> getLibroByReservaId(int id) throws SQLException;
    boolean dropLibroFromReserva(int idLibro) throws SQLException;
    boolean addLibroToReserva(User user, int idLibro) throws SQLException;
}
