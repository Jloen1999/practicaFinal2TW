package es.unex.cum.tw.repositories;

import es.unex.cum.tw.models.Libro;
import es.unex.cum.tw.models.Reserva;
import es.unex.cum.tw.models.User;
import es.unex.cum.tw.services.ServiceJdbcException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReservaRepositoryJDBCImpl implements ReservaRepository {

    private Connection conn;

    public ReservaRepositoryJDBCImpl(Connection conn) {
        this.conn = conn;
    }


    @Override
    public List<Reserva> getReservasByUserId(User user) throws SQLException {
        List<Reserva> reservas = new ArrayList<>();

        String query = "SELECT * FROM Reservas WHERE idUsuario = ?";
        try(PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, user.getId());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int idLibro = rs.getInt("idLibro");
                Timestamp fechaReserva = rs.getTimestamp("fechaReserva");

                // Crear objeto Reserva y agregarlo a la lista
                Reserva reserva = new Reserva(user.getId(), idLibro, fechaReserva);
                reservas.add(reserva);
            }
        }catch(SQLException e){
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }

        return reservas;
    }

    @Override
    public Optional<Libro> getLibroByReservaId(int id) throws SQLException {
        Libro libro = null;

        String query = "SELECT * FROM libros WHERE idLibro = ?";
        try(PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int idLibro = rs.getInt("idLibro");
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");
                String tematica = rs.getString("tematica");
                boolean novedad = rs.getBoolean("novedad");

                // Crear objeto Libro
                libro = new Libro(idLibro, titulo, autor, tematica, novedad);
            }
        }catch(SQLException e){
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }

        return Optional.ofNullable(libro);
    }

    @Override
    public Optional<Reserva> findById(int id) throws SQLException {
        return Optional.empty();
    }

    @Override
    public List<Reserva> findAll() throws SQLException {
        return List.of();
    }

    @Override
    public boolean save(Reserva reserva) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Reserva reserva) throws SQLException {
        return false;
    }

    @Override
    public boolean deleteById(int id) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(Reserva reserva) throws SQLException {
        return false;
    }
}
