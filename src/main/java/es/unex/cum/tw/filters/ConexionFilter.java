package es.unex.cum.tw.filters;

import es.unex.cum.tw.services.ServiceJdbcException;
import es.unex.cum.tw.util.ConexionBD_DSPool;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Filtro que se encarga de gestionar la conexión a la base de datos.
 * <ul>
 *     <li>Si la conexión está en modo autocommit, se desactiva para poder hacer transacciones</li>
 *     <li>Se pasa la conexión a los servlets.</li>
 *     <li>Se hace commit de la transacción.</li>
 *     <li>Si hay un error, se hace rollback.</li>
 * </ul>
 * @author Jose Luis Obiang Ela Nanguang
 * @version 1.0 12-05-2024, Sun, 12:13
 */
@WebFilter("/*")
public class ConexionFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        try (Connection con = ConexionBD_DSPool.getConexionBD()) {
            if (con.getAutoCommit()) { // Si la conexión está en modo autocommit, lo desactivamos para poder hacer transacciones.
                con.setAutoCommit(false);
            }

            try {
                request.setAttribute("con", con); // Pasamos la conexión a los servlets
                filterChain.doFilter(request, response); // Continuamos con la petición
                con.commit(); // Hacemos commit de la transacción
            } catch (SQLException | ServiceJdbcException e) {
                con.rollback(); // Si hay un error, hacemos rollback
                ((HttpServletResponse) response).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                        "Error en la base de datos: " + e.getMessage());
                e.printStackTrace();
            }
        } catch (SQLException e) {
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "Error al obtener conexión de base de datos: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
