package es.unex.cum.tw.repositories;

import es.unex.cum.tw.models.Libro;
import es.unex.cum.tw.models.LibroBuilder;
import es.unex.cum.tw.models.User;
import es.unex.cum.tw.models.Valoracion;
import es.unex.cum.tw.services.ServiceJdbcException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LibroRepositoryJDBCImpl implements LibroRepository {

    private Connection conn;

    public LibroRepositoryJDBCImpl(Connection conn) {
        this.conn = conn;
    }


    @Override
    public Optional<Libro> findById(int id) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM libros WHERE idLibro = " + id);
            if (rs.next()) {
                return Optional.of(saveLibroBDToList(rs));
            }
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
        return Optional.empty();
    }

    @Override
    public List<Libro> findAll() {
        List<Libro> libros = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM libros");
            while (rs.next()) {
                libros.add(saveLibroBDToList(rs));
            }
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
        return libros;
    }

    @Override
    public boolean save(Libro libro) {
        String query = "INSERT INTO Libros (isbn, titulo, autor, descripcion, tematica, novedad, urlImg, puntuacion) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, libro.getIsbn());
            ps.setString(2, libro.getTitulo());
            ps.setString(3, libro.getAutor());
            ps.setString(4, libro.getDescripcion());
            ps.setString(5, libro.getTematica());
            ps.setBoolean(6, libro.isNovedad());
            ps.setString(7, libro.getUrlImg());
            ps.setFloat(8, libro.getPuntuacion());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public boolean update(Libro libro) {
        return false;
    }

    @Override
    public boolean deleteById(int idLibro) throws SQLException {
        // Eliminar reservas relacionadas con el libro
        eliminarReservas(idLibro);

        // Eliminar valoraciones relacionadas con el libro
        eliminarValoraciones(idLibro);

        // Eliminar el libro
        String query = "DELETE FROM Libros WHERE idLibro = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, idLibro);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        }
    }

    private void eliminarReservas(int idLibro) throws SQLException {
        String query = "DELETE FROM Reservas WHERE idLibro = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, idLibro);
            ps.executeUpdate();
        }
    }

    private void eliminarValoraciones(int idLibro) throws SQLException {
        String query = "DELETE FROM Valoraciones WHERE idLibro = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, idLibro);
            ps.executeUpdate();
        }
    }

    @Override
    public boolean delete(Libro libro) {
        return false;
    }

    @Override
    public List<Libro> findByTitulo(String titulo) throws SQLException {
        List<Libro> libros = new ArrayList<>();
        String query = "SELECT * FROM Libros WHERE titulo LIKE ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, "%" + titulo + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                libros.add(saveLibroBDToList(rs));
            }
        }
        return libros;
    }

    @Override
    public List<Libro> getNewBooks() {
        List<Libro> libros = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM libros WHERE novedad = true");
            while (rs.next()) {
                libros.add(saveLibroBDToList(rs));
            }
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
        return libros;
    }

    @Override
    public List<Valoracion> getValoracionesByBookId(int id) throws SQLException {
        List<Valoracion> valoraciones = new ArrayList<>();
        String query = "SELECT * FROM Valoraciones WHERE idLibro = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                valoraciones.add(new Valoracion(
                        rs.getInt("idValoracion"),
                        rs.getInt("idLibro"),
                        rs.getInt("idUsuario"),
                        rs.getInt("puntuacion"),
                        rs.getString("comentario")
                ));
            }
        }
        return valoraciones;
    }

    @Override
    public boolean updatePuntuacionBookByUser(Libro libro, int idUser) throws SQLException {
        // Primero comprobar si ya existe una valoración del usuario para el libro
        String querySelect = "SELECT * FROM Valoraciones WHERE idLibro = ? AND idUsuario = ?";
        try (PreparedStatement ps = conn.prepareStatement(querySelect)) {
            ps.setInt(1, libro.getIdLibro());
            ps.setInt(2, idUser);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return updatePuntuacionValoracion(libro, idUser);
            } else {
                return savePuntuacionValoracion(libro, idUser);
            }
        }

    }

    private boolean savePuntuacionValoracion(Libro libro, int idUser) {
        String query = "INSERT INTO Valoraciones (idLibro, idUsuario, puntuacion, comentario) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, libro.getIdLibro());
            ps.setInt(2, idUser);
            ps.setFloat(3, libro.getPuntuacion());
            ps.setString(4, "");
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    private boolean updatePuntuacionValoracion(Libro libro, int idUser) {
        String query = "UPDATE Valoraciones SET puntuacion = ? WHERE idLibro = ? AND idUsuario = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setFloat(1, libro.getPuntuacion());
            ps.setInt(2, libro.getIdLibro());
            ps.setInt(3, idUser);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public boolean updatePuntuacion(Libro libro) throws SQLException {
        String query = "UPDATE Libros SET puntuacion = ? WHERE idLibro = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setFloat(1, libro.getPuntuacion());
            ps.setInt(2, libro.getIdLibro());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        }catch (SQLException e) {
            throw new ServiceJdbcException(e.getMessage(), e.getCause());
        }
    }

    private static Libro saveLibroBDToList(ResultSet rs) throws SQLException {
        return new LibroBuilder()
                .setIdLibro(rs.getInt("idLibro"))
                .setIsbn(rs.getString("isbn"))
                .setTitulo(rs.getString("titulo"))
                .setAutor(rs.getString("autor"))
                .setDescripcion(rs.getString("descripcion"))
                .setTematica(rs.getString("tematica"))
                .setUrlImg(rs.getString("urlImg"))
                .setNovedad(rs.getBoolean("novedad"))
                .setPuntuacion(rs.getFloat("puntuacion"))
                .build();
    }
}
