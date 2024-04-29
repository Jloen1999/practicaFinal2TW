package es.unex.cum.tw.repositories;

import es.unex.cum.tw.models.Libro;
import es.unex.cum.tw.models.Reserva;
import es.unex.cum.tw.models.User;
import es.unex.cum.tw.services.ServiceJdbcException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LibroRepositoryJDBCImpl implements LibroRepository {

    private Connection conn;

    public LibroRepositoryJDBCImpl(Connection conn) {
        this.conn = conn;
    }




    @Override
    public Optional<Reserva> findById(int id) throws SQLException {
        return Optional.empty();
    }

    @Override
    public List<Reserva> findAll() throws SQLException {
        return List.of();
    }

    @Override
    public boolean save(Reserva reserva) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Reserva reserva) throws SQLException {
        return false;
    }

    @Override
    public boolean deleteById(int id) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(Reserva reserva) throws SQLException {
        return false;
    }
}
