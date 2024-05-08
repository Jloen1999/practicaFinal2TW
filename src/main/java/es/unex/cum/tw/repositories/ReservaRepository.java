package es.unex.cum.tw.repositories;

import es.unex.cum.tw.models.Libro;
import es.unex.cum.tw.models.Reserva;
import es.unex.cum.tw.models.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ReservaRepository extends Repository<Reserva>{
    List<Reserva> getReservasByUser(User user) throws SQLException;
    Optional<Libro> getLibroByReservaId(int id) throws SQLException;
    boolean dropLibroFromReserva(int idLibro) throws SQLException;
    boolean addLibroToReserva(User user, int idLibro) throws SQLException;

}
