package es.unex.cum.tw.services;

import es.unex.cum.tw.models.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> login(String username, String password);
    boolean save(User user) throws SQLException;
    List<User> findAll() throws SQLException;
    boolean deleteUserById(int id) throws SQLException;
    Optional<User> findByUsernameAndPassword(String username, String password) throws SQLException;
    Optional<User> findUserById(int idUsuario) throws SQLException;
}
