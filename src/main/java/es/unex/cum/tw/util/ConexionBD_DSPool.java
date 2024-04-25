package es.unex.cum.tw.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConexionBD_DSPool {

    // Instancia única de la conexión
    private static ConexionBD_DSPool instance = null;

    // Método estático para obtener la instancia única
    public static synchronized ConexionBD_DSPool getInstance() {
        if (instance == null) {
            instance = new ConexionBD_DSPool();
        }
        return instance;
    }

    // constructor privado para evitar instanciación externa
    private ConexionBD_DSPool() {
    }

    public Connection getConexionBD() throws SQLException, NamingException {
        Context initContext = new InitialContext();
        Context envContext = (Context) initContext.lookup("java:/comp/env");
        DataSource ds = (DataSource) envContext.lookup("jdbc/MysqlDB");
        return ds.getConnection();
    }
}
