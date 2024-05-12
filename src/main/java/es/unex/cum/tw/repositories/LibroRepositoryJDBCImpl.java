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

/**
 * Implementación de la interfaz LibroRepository para el acceso a la base de datos mediante JDBC.
 * @author Jose Luis Obiang Ela Nanguang
 * @version 1.0 12-05-2024, Sun, 12:19
 */
public class LibroRepositoryJDBCImpl implements LibroRepository {

    private Connection conn;

    public LibroRepositoryJDBCImpl(Connection conn) {
        this.conn = conn;
    }

    /**
     * Método que devuelve un libro a partir de su id.
     * @param id id del libro
     * @return Optional<Libro> libro
     * <ul>
     *     <li>Optional.empty(): si no se ha encontrado el libro</li>
     *     <li>Optional.of(libro): si se ha encontrado el libro</li>
     *     <li>throw SQLException: si se produce un error</li>
     * </ul>
     */
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

    /**
     * Método que devuelve todos los libros de la base de datos.
     * @return List<Libro> libros
     */
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

    /**
     * Método que guarda un libro en la base de datos.
     * @param libro libro a guardar
     * @return boolean
     * <ul>
     *     <li>true: si se ha guardado correctamente</li>
     *     <li>false: si no se ha guardado correctamente</li>
     * </ul>
     */
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

    /**
     * Método que elimina un libro de la base de datos a partir de su id.
     * @param idLibro id del libro
     * @return boolean
     * <ul>
     *     <li>true: si se ha eliminado correctamente</li>
     *     <li>false: si no se ha eliminado correctamente</li>
     *     <li>throw SQLException: si se produce un error</li>
     * </ul>
     */
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

    /**
     * Método que elimina las reservas de un libro a partir de su id.
     * @param idLibro id del libro
     * @throws SQLException si se produce un error
     */
    private void eliminarReservas(int idLibro) throws SQLException {
        String query = "DELETE FROM Reservas WHERE idLibro = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, idLibro);
            ps.executeUpdate();
        }
    }

    /**
     * Método que elimina las valoraciones de un libro a partir de su id.
     * @param idLibro id del libro
     * @throws SQLException si se produce un error
     */
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

    /**
     * Método que devuelve una lista de libros a partir de su título.
     * @param titulo título del libro
     * @return List<Libro> libros
     * @throws SQLException si se produce un error
     */
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

    /**
     * Método que devuelve todos los libros de la base de datos que son novedades(campo novedad a true).
     * @return List<Libro> libros
     */
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

    /**
     * Método que devuelve una lista de valoraciones de un libro a partir de su id.
     * @param id id del libro
     * @return List<Valoracion> valoraciones
     */
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

    /**
     * Método que actualiza la puntuación de un libro de un usuario en concreto.
     * @param libro libro cuya puntuación se va a actualizar en valoraciones
     * @param idUser id del usuario
     * @return boolean
     * <ul>
     *     <li>true: si se ha actualizado correctamente</li>
     *     <li>false: si no se ha actualizado correctamente</li>
     *     <li>throw SQLException: si se produce un error</li>
     * </ul>
     */
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

    /**
     * Método que guarda una puntuación de un libro de un usuario en la base de datos.
     * @param libro libro cuya puntuación se va a guardar
     * @param idUser id del usuario
     * @return boolean
     * <ul>
     *     <li>true: si se ha guardado correctamente</li>
     *     <li>false: si no se ha guardado correctamente</li>
     *     <li>throw SQLException: si se produce un error</li>
     * </ul>
     */
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

    /**
     * Método que actualiza la puntuación de un libro de un usuario en la base de datos.
     * @param libro libro cuya puntuación se va a actualizar
     * @param idUser id del usuario
     * @return
     */
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

    /**
     * Método que actualiza la puntuación de un libro en la base de datos.
     * @param libro libro cuya puntuación se va a actualizar
     * @return boolean
     * <ul>
     *     <li>true: si se ha actualizado correctamente</li>
     *     <li>false: si no se ha actualizado correctamente</li>
     *     <li>throw SQLException: si se produce un error</li>
     * </ul>
     */
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

    /**
     * Método que guarda un libro en la base de datos.
     * @param rs ResultSet
     * @return Libro libro
     */
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
