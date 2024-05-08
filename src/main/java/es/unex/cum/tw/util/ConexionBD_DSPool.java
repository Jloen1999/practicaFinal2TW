package es.unex.cum.tw.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConexionBD_DSPool {

    public static Connection getConexionBD() throws SQLException {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/MysqlDB");
            Connection conn = ds.getConnection();
            if (conn == null) {
                throw new SQLException("No se pudo obtener la conexión");
            }
            return conn;
        } catch (NamingException | SQLException e) {
            // Manejo de excepciones
            throw new SQLException("Error al obtener la conexión: " + e.getMessage(), e);
        }
    }


}
