package es.unex.cum.tw.services;

import es.unex.cum.tw.models.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> login(String username, String password);
    boolean save(User user) throws SQLException;
}
