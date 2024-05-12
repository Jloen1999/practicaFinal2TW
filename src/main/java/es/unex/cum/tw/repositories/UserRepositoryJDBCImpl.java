package es.unex.cum.tw.repositories;

import es.unex.cum.tw.models.User;
import es.unex.cum.tw.models.UserBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementación de la interfaz UserRepository para el CRUD de usuarios en una base de datos
 * @author Jose Luis Obiang Ela Nanguang
 * @version 1.0 12-05-2024, Sun, 12:42
 */
public class UserRepositoryJDBCImpl implements UserRepository {

    private Connection conn;

    public UserRepositoryJDBCImpl(Connection conn) {
        this.conn = conn;
    }


    /**
     * Busca un usuario por su nombre de usuario y contraseña
     * @param username nombre de usuario
     * @param password contraseña
     * @return
     * <ul>
     *     <li>Optional.empty() si no se encuentra el usuario</li>
     *     <li>Optional.of(User) si se encuentra el usuario</li>
     * </ul>
     */
    @Override
    public Optional<User> findByUsernameAndPassword(String username, String password) throws SQLException {
        User user = null;
        try (PreparedStatement statement = conn.prepareStatement("SELECT * FROM usuarios WHERE username = ? AND password = ?")) {
            statement.setString(1, username);
            statement.setString(2, password);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    user = new UserBuilder().setId(rs.getInt("idUsuario"))
                            .setNombre(rs.getString("nombre"))
                            .setApellidos(rs.getString("apellidos"))
                            .setEmail(rs.getString("email"))
                            .setUsername(rs.getString("username"))
                            .setPassword(rs.getString("password"))
                            .build();

                }
            }
        }
        return Optional.ofNullable(user);
    }

    /**
     * Busca un usuario por su nombre de usuario
     * @param idUsuario id del usuario
     * @return
     * <ul>
     *     <li>Optional.empty() si no se encuentra el usuario</li>
     *     <li>Optional.of(User) si se encuentra el usuario</li>
     * </ul>
     */
    @Override
    public Optional<User> findById(int idUsuario) throws SQLException {
        User user = null;
        try (PreparedStatement statement = conn.prepareStatement("SELECT * FROM usuarios WHERE idUsuario = ?")) {
            statement.setLong(1, idUsuario);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    user = new UserBuilder().setId(rs.getInt("idUsuario"))
                            .setNombre(rs.getString("nombre"))
                            .setApellidos(rs.getString("apellidos"))
                            .setEmail(rs.getString("email"))
                            .setUsername(rs.getString("username"))
                            .setPassword(rs.getString("password"))
                            .build();

                }
            }
        }
        return Optional.ofNullable(user);
    }

    /**
     * Devuelve una lista con todos los usuarios
     * @return List<User> lista de usuarios
     */
    @Override
    public List<User> findAll() throws SQLException {
        List<User> users = new ArrayList<>();
        try (PreparedStatement statement = conn.prepareStatement("SELECT * FROM usuarios")) {
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    User user = new UserBuilder().setId(rs.getInt("idUsuario"))
                            .setNombre(rs.getString("nombre"))
                            .setApellidos(rs.getString("apellidos"))
                            .setEmail(rs.getString("email"))
                            .setUsername(rs.getString("username"))
                            .setPassword(rs.getString("password"))
                            .build();
                    users.add(user);
                }
            }
        }
        return users;
    }

    /**
     * Guarda un usuario en la base de datos
     * @param user usuario a guardar
     * @return
     * <ul>
     *     <li>true si se ha guardado correctamente</li>
     *     <li>false si ha habido algún error</li>
     * </ul>
     */
    @Override
    public boolean save(User user) throws SQLException {
        try (PreparedStatement statement = conn.prepareStatement(
                "INSERT INTO usuarios (nombre, apellidos, email, username, password) VALUES (?, ?, ?, ?, ?)")) {
            statement.setString(1, user.getNombre());
            statement.setString(2, user.getApellidos());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getUsername());
            statement.setString(5, user.getPassword());
            return statement.executeUpdate() > 0;
        }
    }

    /**
     * Actualiza un usuario en la base de datos
     * @param user usuario a actualizar
     * @return
     * <ul>
     *     <li>true si se ha actualizado correctamente</li>
     *     <li>false si ha habido algún error</li>
     * </ul>
     */
    @Override
    public boolean update(User user) throws SQLException {
        try (PreparedStatement statement = conn.prepareStatement(
                "UPDATE usuarios SET nombre = ?, apellidos = ?, email = ?, username = ?, password = ? WHERE idUsuario = ?")) {
            statement.setString(1, user.getNombre());
            statement.setString(2, user.getApellidos());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getUsername());
            statement.setString(5, user.getPassword());
            statement.setLong(6, user.getId());
            return statement.executeUpdate() > 0;
        }
    }

    /**
     * Elimina un usuario de la base de datos
     * @param idUsuario id del usuario a eliminar
     * @return
     * <ul>
     *     <li>true si se ha eliminado correctamente</li>
     *     <li>false si ha habido algún error</li>
     * </ul>
     */
    @Override
    public boolean deleteById(int idUsuario) throws SQLException {
        // Primero eliminar el usuario en las tablas en las que tiene relacion
        eliminarReservasUsuario(idUsuario);
        eliminarValoracionesUsuario(idUsuario);
        try (PreparedStatement statement = conn.prepareStatement("DELETE FROM usuarios WHERE idUsuario = ?")) {
            statement.setLong(1, idUsuario);
            return statement.executeUpdate() > 0;
        }

    }

    /**
     * Elimina las valoraciones de un usuario
     * @param idUsuario id del usuario
     */
    private void eliminarValoracionesUsuario(int idUsuario) {
        try (PreparedStatement statement = conn.prepareStatement("DELETE FROM valoraciones WHERE idUsuario = ?")) {
            statement.setLong(1, idUsuario);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Elimina las reservas de un usuario
     * @param idUsuario id del usuario
     */
    private void eliminarReservasUsuario(int idUsuario) {
        try (PreparedStatement statement = conn.prepareStatement("DELETE FROM reservas WHERE idUsuario = ?")) {
            statement.setLong(1, idUsuario);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Elimina un usuario de la base de datos
     * @param user usuario a eliminar
     * @return
     * <ul>
     *     <li>true si se ha eliminado correctamente</li>
     *     <li>false si ha habido algún error</li>
     * </ul>
     */
    @Override
    public boolean delete(User user) throws SQLException {
        return deleteById(user.getId());
    }
}
