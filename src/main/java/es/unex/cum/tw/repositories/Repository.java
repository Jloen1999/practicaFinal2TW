package es.unex.cum.tw.repositories;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Interface Repository que define los métodos que debe implementar un repositorio
 * @param <T> Tipo de dato
 * @author Jose Luis Obiang Ela Nanguang
 * @version 1.0 12-05-2024, Sun, 12:33
 * @since 1.0
 * @see java.sql.SQLException
 * @see java.util.List
 * @see java.util.Optional
 */
public interface Repository<T> {
    Optional<T> findById(int id) throws SQLException;

    List<T> findAll() throws SQLException;

    boolean save(T t) throws SQLException;

    boolean update(T t) throws SQLException;

    boolean deleteById(int id) throws SQLException;

    boolean delete(T t) throws SQLException;
}
