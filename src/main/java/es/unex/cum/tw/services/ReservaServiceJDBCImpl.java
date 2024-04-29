package es.unex.cum.tw.services;

import es.unex.cum.tw.models.Libro;
import es.unex.cum.tw.models.Reserva;
import es.unex.cum.tw.models.User;
import es.unex.cum.tw.repositories.ReservaRepository;
import es.unex.cum.tw.repositories.ReservaRepositoryJDBCImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ReservaServiceJDBCImpl implements ReservaService{

    private ReservaRepository reservaRepository;

    public ReservaServiceJDBCImpl(Connection connection){
        this.reservaRepository = new ReservaRepositoryJDBCImpl(connection);
    }


    @Override
    public List<Reserva> getReservasByUserId(User user) throws SQLException {
        try {
            return reservaRepository.getReservasByUserId(user);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Libro> getLibroByReservaId(int id) throws SQLException {
        try {
            return reservaRepository.getLibroByReservaId(id);
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }
}
