package es.unex.cum.tw.services;

import es.unex.cum.tw.models.Libro;
import es.unex.cum.tw.models.Valoracion;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Servicio de la entidad Libro que define los métodos que se van a utilizar en la aplicación.
 * @author Jose Luis Obiang Ela Nanguang
 * @version 1.0 12-05-2024, Sun, 12:46
 */
public interface LibroService {
    List<Libro> findAll() throws SQLException;
    Optional<Libro> findById(int id) throws SQLException;
    void deleteBookById(int id) throws SQLException;
    boolean addBook(Libro libro) throws SQLException;
    List<Libro> findByTitulo(String titulo) throws SQLException;
    List<Valoracion> getValoracionesByBookId(int id) throws SQLException;
    boolean updatePuntuacionBookByUser(Libro libro, int idUser) throws SQLException;
    boolean updatePuntuacion(Libro libro) throws SQLException;
}
