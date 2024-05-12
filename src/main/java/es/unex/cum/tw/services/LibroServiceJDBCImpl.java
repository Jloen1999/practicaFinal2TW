package es.unex.cum.tw.services;

import es.unex.cum.tw.models.Libro;
import es.unex.cum.tw.models.Valoracion;
import es.unex.cum.tw.repositories.LibroRepository;
import es.unex.cum.tw.repositories.LibroRepositoryJDBCImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Servicio de la entidad Libro que implementa los métodos que se van a utilizar en la aplicación
 * @author Jose Luis Obiang Ela Nanguang
 * @version 1.0 12-05-2024, Sun, 12:48
 */
public class LibroServiceJDBCImpl implements LibroService{

    private LibroRepository libroRepository;

    public LibroServiceJDBCImpl(Connection connection){
        this.libroRepository = new LibroRepositoryJDBCImpl(connection);
    }


    @Override
    public List<Libro> findAll() throws SQLException {
        try {
            return libroRepository.findAll();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public Optional<Libro> findById(int id) throws SQLException {
        try {
            return libroRepository.findById(id);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public void deleteBookById(int id) throws SQLException {
        try {
            libroRepository.deleteById(id);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public boolean addBook(Libro libro) throws SQLException {
        try {
            return libroRepository.save(libro);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public List<Valoracion> getValoracionesByBookId(int id) throws SQLException {
        try {
            return libroRepository.getValoracionesByBookId(id);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public boolean updatePuntuacionBookByUser(Libro libro, int idUser) throws SQLException {
        try {
            return libroRepository.updatePuntuacionBookByUser(libro, idUser);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public boolean updatePuntuacion(Libro libro) throws SQLException {
        try {
            return libroRepository.updatePuntuacion(libro);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    @Override
    public List<Libro> findByTitulo(String titulo) throws SQLException {
        try {
            return libroRepository.findByTitulo(titulo);
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }


}
