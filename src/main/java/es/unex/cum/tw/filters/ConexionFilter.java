package es.unex.cum.tw.filters;

import es.unex.cum.tw.services.ServiceJdbcException;
import es.unex.cum.tw.util.ConexionBD_DSPool;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebFilter("/*")
public class ConexionFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        try (Connection con = ConexionBD_DSPool.getConexionBD()) {
            if (con.getAutoCommit()) {
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
