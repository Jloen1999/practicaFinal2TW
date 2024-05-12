package es.unex.cum.tw.repositories;

import es.unex.cum.tw.models.User;

import java.sql.SQLException;
import java.util.Optional;

/**
 * Interface UserRepository que extiende de Repository y que se encarga de gestionar la persistencia de los usuarios.
 * @author Jose Luis Obiang Ela Nanguang
 * @version 1.0 12-05-2024, Sun, 12:42
 */
public interface UserRepository extends Repository<User>{
    Optional<User> findByUsernameAndPassword(String username, String password) throws SQLException;
}
