/*
package es.unex.cum.tw.repositories;

import es.unex.cum.tw.models.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CartaRepository extends Repository<Carta>{
    Optional<Carta> findCartaByUser(User user) throws SQLException;
    List<Regalo> findRegalosByCarta(Carta carta) throws SQLException;
    boolean addRegaloToCarta(Carta carta, Regalo regalo) throws SQLException;
    boolean existsCartaByUser(User user) throws SQLException;
    void deleteRegalosFromCarta(List<String> idRegalos) throws SQLException;
    boolean addCantidadToRegalo(int idRegalo, int cantidad) throws SQLException;
}
*/
