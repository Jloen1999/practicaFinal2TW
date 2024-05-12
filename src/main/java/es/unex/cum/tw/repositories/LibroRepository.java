package es.unex.cum.tw.repositories;

import es.unex.cum.tw.models.Libro;
import es.unex.cum.tw.models.Valoracion;

import java.sql.SQLException;
import java.util.List;

/**
 * Interfaz que define los métodos que debe implementar un repositorio de libros.
 * Extiende de la interfaz Repository.
 * @author Jose Luis Obiang Ela Nanguang
 * @version 1.0 12-05-2024, Sun, 12:18
 */
public interface LibroRepository extends Repository<Libro>{
    List<Libro> findByTitulo(String titulo) throws SQLException;
    List<Libro> getNewBooks() throws SQLException;
    List<Valoracion> getValoracionesByBookId(int id) throws SQLException;
    boolean updatePuntuacionBookByUser(Libro libro, int idUser) throws SQLException;
    boolean updatePuntuacion(Libro libro) throws SQLException;
}
