package es.unex.cum.tw.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionBD {

    private static final String URL = "jdbc:mysql://localhost:3306/cartas?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static Properties props = new Properties();
    private static String USERNAME;
    private static String PASSWORD;

    public static Connection getConexionBD() throws SQLException {
        try {
            props.load(ConexionBD.class.getClassLoader().getResourceAsStream("config.properties"));
            USERNAME = props.getProperty("USERNAMEDB");
            PASSWORD = props.getProperty("PASSWORDDB");
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
