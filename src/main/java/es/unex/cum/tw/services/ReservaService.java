package es.unex.cum.tw.services;

import es.unex.cum.tw.models.Libro;
import es.unex.cum.tw.models.Reserva;
import es.unex.cum.tw.models.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ReservaService {
    List<Reserva> getReservasByUserId(User user) throws SQLException;
    Optional<Libro> getLibroByReservaId(int id) throws SQLException;
}
