package es.unex.cum.tw.repositories;

import es.unex.cum.tw.models.Libro;
import es.unex.cum.tw.models.Valoracion;

import java.sql.SQLException;
import java.util.List;

public interface LibroRepository extends Repository<Libro>{
    List<Libro> findByTitulo(String titulo) throws SQLException;
    List<Libro> getNewBooks() throws SQLException;
    List<Valoracion> getValoracionesByBookId(int id) throws SQLException;
    boolean updatePuntuacionBookByUser(Libro libro, int idUser) throws SQLException;
    boolean updatePuntuacion(Libro libro) throws SQLException;
}
