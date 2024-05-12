package es.unex.cum.tw.services;

/**
 * Excepción personalizada para los servicios JDBC.
 * @author Jose Luis Obiang Ela Nanguang
 * @version 1.0 12-05-2024, Sun, 12:50
 */
public class ServiceJdbcException extends RuntimeException{
    public ServiceJdbcException(String message) {
        super(message);
    }

    public ServiceJdbcException(String message, Throwable cause) {
        super(message, cause);
    }
}
