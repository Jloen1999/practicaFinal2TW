package es.unex.cum.tw.repositories;

import es.unex.cum.tw.models.Libro;
import es.unex.cum.tw.models.LibroBuilder;
import es.unex.cum.tw.models.Reserva;
import es.unex.cum.tw.models.User;
import es.unex.cum.tw.services.ServiceJdbcException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementación de la interfaz ReservaRepository para las operaciones CRUD de la entidad Reserva
 *
 * @author Jose Luis Obiang Ela Nanguang
 * @version 1.0 12-05-2024, Sun, 12:37
 */
public class ReservaRepositoryJDBCImpl implements ReservaRepository {

    private final Connection conn;

    public ReservaRepositoryJDBCImpl(Connection conn) {
        this.conn = conn;
    }

    /**
     * Método que obtiene todas las reservas de un usuario
     *
     * @param user Usuario del que se quieren obtener las reservas
     * @return Lista de reservas del usuario
     */
    @Override
    public List<Reserva> getReservasByUser(User user) throws SQLException {
        List<Reserva> reservas = new ArrayList<>();

        String query = "SELECT * FROM reservas WHERE idUsuario = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, user.getId());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                // Obtener datos de la reserva del ResultSet
                int idLibro = rs.getInt("idLibro");
                Timestamp fechaReserva = rs.getTimestamp("fechaReserva");

                // Crear instancia de Reserva con los datos obtenidos
                Reserva reserva = new Reserva(user.getId(), idLibro, fechaReserva);
                reservas.add(reserva);
            }
        } catch (SQLException e) {
            // Capturar y relanzar excepciones con ServiceJdbcException
            throw new ServiceJdbcException("Error al obtener reservas del usuario", e);
        }

        return reservas;
    }

    /**
     * Método que obtiene un libro a partir de su id de reserva
     *
     * @param id Id de la reserva
     * @return Libro asociado a la reserva
     * <ul>
     *     <li>Si el libro existe, se devuelve un Optional con el libro</li>
     *     <li>Si el libro no existe, se devuelve un Optional vacío</li>
     * </ul>
     */
    @Override
    public Optional<Libro> getLibroByReservaId(int id) throws SQLException {
        Libro libro = null;

        String query = "SELECT * FROM libros WHERE idLibro = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int idLibro = rs.getInt("idLibro");
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");
                String tematica = rs.getString("tematica");
                String descripcion = rs.getString("descripcion");
                String urlImg = rs.getString("urlImg");
                boolean novedad = rs.getBoolean("novedad");

                // Crear objeto Libro
                libro = new LibroBuilder()
                        .setIdLibro(idLibro)
                        .setTitulo(titulo)
                        .setAutor(autor)
                        .setTematica(tematica)
                        .setDescripcion(descripcion)
                        .setUrlImg(urlImg)
                        .setNovedad(novedad)
                        .build();
            }
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }

        return Optional.ofNullable(libro);
    }

    /**
     * Método que elimina un libro de la tabla de reservas a partir de su id
     *
     * @param idLibro Id del libro a eliminar de la tabla de reservas
     * @return <ul>
     *     <li>Si el libro se elimina correctamente, se devuelve true</li>
     *     <li>Si el libro no se elimina correctamente, se devuelve false</li>
     *     <li>Si se produce una excepción, se lanza una excepción de tipo SQLException</li>
     * </ul>
     */
    @Override
    public boolean dropLibroFromReserva(int idLibro) throws SQLException {
        String query = "DELETE FROM reservas WHERE idLibro = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, idLibro);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    /**
     * Método que añade un libro a la tabla de reservas
     *
     * @param user    Usuario que realiza la reserva
     * @param idLibro Id del libro a reservar
     * @return <ul>
     *     <li>Si el libro se añade correctamente a la tabla de reservas, se devuelve true</li>
     *     <li>Si el libro ya ha sido reservado por el usuario, se devuelve false</li>
     *     <li>Si se produce una excepción, se lanza una excepción de tipo SQLException</li>
     * </ul>
     */
    @Override
    public boolean addLibroToReserva(User user, int idLibro) throws SQLException {
        // Primero comprobar si el libro ya ha sido reservado por el usuario
        boolean reservado = false, insertado = false;
        String queryCheck = "SELECT * FROM reservas WHERE idUsuario = ? AND idLibro = ?";
        try (PreparedStatement ps = conn.prepareStatement(queryCheck)) {
            ps.setInt(1, user.getId());
            ps.setInt(2, idLibro);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                reservado = true;
            }
        } catch (SQLException e) {
            throw new ServiceJdbcException("Error al comprobar si el libro ya ha sido reservado", e.getCause());
        }

        if (!reservado) {
            String query = "INSERT INTO reservas (idUsuario, idLibro, fechaReserva) VALUES (?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setInt(1, user.getId());
                ps.setInt(2, idLibro);
                ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));

                insertado = ps.executeUpdate() > 0;
            } catch (SQLException e) {
                throw new ServiceJdbcException("Este libro ya ha sido reservado por tí", e.getCause());
            }
        }

        return insertado;
    }

    /**
     * Método que obtiene la reserva de un libro por su id
     *
     * @param id Id de la reserva
     * @return <ul>
     *     <li>Si la reserva existe, se devuelve un Optional con la reserva</li>
     *     <li>Si la reserva no existe, se devuelve un Optional vacío</li>
     *     <li>Si se produce una excepción, se lanza una excepción de tipo SQLException</li>
     * </ul>
     */
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
