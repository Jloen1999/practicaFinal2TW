package es.unex.cum.tw.repositories;

import es.unex.cum.tw.models.Carta;
import es.unex.cum.tw.models.Regalo;
import es.unex.cum.tw.models.User;
import es.unex.cum.tw.models.UserBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CartaRepositoryJDBCImpl implements CartaRepository {

    private Connection conn;

    public CartaRepositoryJDBCImpl(Connection conn) {
        this.conn = conn;
    }


    @Override
    public Optional<Carta> findCartaByUser(User user) throws SQLException {
        String query = "SELECT * FROM cartas WHERE id_usuario = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setLong(1, user.getId());
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return Optional.of(new Carta(rs.getInt("id"), rs.getInt("id_usuario"), rs.getString("titulo_carta")));
        }
        return Optional.empty();
    }

    @Override
    public List<Regalo> findRegalosByCarta(Carta carta) throws SQLException {
        String query = "SELECT * FROM regalos WHERE id_carta = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setLong(1, carta.getIdCarta());
        ResultSet rs = ps.executeQuery();
        List<Regalo> regalos = new ArrayList<>();
        while (rs.next()) {
            regalos.add(new Regalo(rs.getInt("id"), rs.getString("nombre_regalo"), rs.getInt("id_carta")));
        }
        return regalos;
    }

    @Override
    public boolean addRegaloToCarta(Carta carta, Regalo regalo) throws SQLException {
        String query = "INSERT INTO regalos (nombre_regalo, id_carta) VALUES (?, ?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, regalo.getNombre());
        ps.setLong(2, carta.getIdCarta());
        return ps.executeUpdate() > 0;
    }

    @Override
    public boolean existsCartaByUser(User user) throws SQLException {
        String query = "SELECT * FROM cartas WHERE id_usuario = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setLong(1, user.getId());
        ResultSet rs = ps.executeQuery();
        return rs.next();
    }

    @Override
    public boolean existsRegaloByCarta(Carta carta) throws SQLException {
        String query = "SELECT * FROM regalos WHERE id_carta = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setLong(1, carta.getIdCarta());
        ResultSet rs = ps.executeQuery();
        return rs.next();
    }

    @Override
    public Optional<Carta> findById(int id) throws SQLException {
        return Optional.empty();
    }

    @Override
    public List<Carta> findAll() throws SQLException {
        return List.of();
    }

    @Override
    public boolean save(Carta carta) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Carta carta) throws SQLException {
        return false;
    }

    @Override
    public boolean deleteById(int id) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(Carta carta) throws SQLException {
        return false;
    }
}
