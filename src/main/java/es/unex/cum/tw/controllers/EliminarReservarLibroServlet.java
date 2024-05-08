package es.unex.cum.tw.controllers;

import es.unex.cum.tw.models.Libro;
import es.unex.cum.tw.models.User;
import es.unex.cum.tw.services.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;

/**
 * Servlet que gestiona la eliminación y reserva de libros.
 * @author Jose Luis Obiang Ela Nanguang
 * @version 1.0 06/05/2024
 * @see jakarta.servlet.http.HttpServlet
 * @see jakarta.servlet.http.HttpServletRequest
 * @see jakarta.servlet.http.HttpServletResponse
 * @see jakarta.servlet.annotation.WebServlet
 * @see java.io.IOException
 * @see java.sql.Connection
 * @see java.sql.SQLException
 * @see java.util.ArrayList
 * @see java.util.Enumeration
 * @see java.util.List
 * @see java.util.Optional
 * @see es.unex.cum.tw.models.Libro
 * @see es.unex.cum.tw.models.User
 * @see es.unex.cum.tw.services.LibroService
 * @see es.unex.cum.tw.services.LibroServiceJDBCImpl
 * @see es.unex.cum.tw.services.LoginService
 * @see es.unex.cum.tw.services.LoginServiceImpl
 * @see es.unex.cum.tw.services.ReservaService
 * @see es.unex.cum.tw.services.ReservaServiceJDBCImpl
 * @see es.unex.cum.tw.services.UserService
 * @see es.unex.cum.tw.services.UserServiceJDBCImpl
 * @see jakarta.servlet.ServletException
 *
 */
@WebServlet(name = "EliminarReservarLibroServlet", value = "/validarEliminarReservarLibro")
public class EliminarReservarLibroServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=UTF-8");

        Connection con = (Connection) request.getAttribute("con");

        boolean reservar = false; // Variable para saber si se ha reservado algún libro

        Enumeration<String> parameterNames = request.getParameterNames(); // Obtener los nombres de los parámetros
        List<String> idLibros = new ArrayList<>();
        List<String> puntuacionesValues = new ArrayList<>();

        // SI se ha puntuado algun libro. Obtener las puntuaciones y los id de los libros en caso de que se haya puntuado algún libro
        while (parameterNames.hasMoreElements()) { // Recorrer los parámetros
            String paramName = parameterNames.nextElement();
            if (paramName.contains("puntuaciones")) { // Si el parámetro contiene "puntuaciones" ya que solo nos interesa las puntuaciones para poder actualizar las valoraciones de un libro por un usuario
                String paramValue = request.getParameter(paramName);
                if(!paramValue.isBlank()){
                    idLibros.add(paramName.substring("puntuaciones".length())); // Añadir el id del libro. Ejemplo: puntuaciones1 -> 1
                    puntuacionesValues.add(paramValue); // Añadir la puntuación del libro
                }

            }
        }


        LoginService loginService = new LoginServiceImpl();
        Optional<User> user = loginService.authenticate(request);
        UserService userService = new UserServiceJDBCImpl(con);
        try {
            // Obtener el usuario de la base de datos ya que nos interesa el id del usuario
            user = userService.findByUsernameAndPassword(user.get().getUsername(), user.get().getPassword());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        LibroService libroService = new LibroServiceJDBCImpl(con);
        Libro libro = null;
        if (!idLibros.isEmpty() && !puntuacionesValues.isEmpty()){ // Si se ha puntuado algún libro
            for (int i = 0; i < idLibros.size(); i++) {
                try {
                    // Obtener el libro de la base de datos a traves de su id
                    libro = libroService.findById(Integer.parseInt(idLibros.get(i))).get();
                    libro.setPuntuacion(Float.parseFloat(puntuacionesValues.get(i))); // Actualizar la puntuación del libro
                    /*
                    * Si no existe una puntuación para un libro por parte de un usuario, se inserta un nuevo registro en la tabla de valoraciones
                    * Si ya existe una puntuación para un libro por parte de un usuario, se actualiza la puntuación del libro en la tabla de valoraciones
                     */
                    libroService.updatePuntuacionBookByUser(libro, user.get().getId());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            request.setAttribute("mensaje", "Puntuaciones actualizadas correctamente");
        }else{
            request.setAttribute("mensaje", "No se ha realizado ninguna acción");
        }

        // Si se ha eliminado o reservado algún libro
        if (request.getParameterValues("eliminarReservar") != null && request.getParameterValues("eliminarReservar").length != 0) {
            String[] librosAEliminarOReservar = request.getParameterValues("eliminarReservar");

            ReservaService reservaService = new ReservaServiceJDBCImpl(con);

            StringBuilder librosEliminados = new StringBuilder("[");
            StringBuilder librosReservados = new StringBuilder("[");
            for (int i=0; i < librosAEliminarOReservar.length; i++) {
                // Si el libro contiene un guion, se trata de una reserva en la vista de libros reservados
                if (librosAEliminarOReservar[i].contains("-")) {
                    librosAEliminarOReservar[i] = librosAEliminarOReservar[i].replace("-", "");
                    reservar = true;
                }

                if (librosAEliminarOReservar[i].charAt(0) == 'r') { // Si se trata de una reserva

                    try {
                        if (user.isPresent()) { // Si el usuario está autenticado
                            // Si se ha podido reservar el libro
                            if (reservaService.addLibroToReserva(user.get(), Integer.parseInt(librosAEliminarOReservar[i].substring(1)))) {
                                librosReservados.append(libroService.findById(Integer.parseInt(librosAEliminarOReservar[i].substring(1))).get().getTitulo() + ", ");
                            }
                        } else { // Si el usuario no está autenticado se redirige a la página de inicio de sesión
                            response.sendRedirect(request.getContextPath() + "/index.jsp");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (librosAEliminarOReservar[i].charAt(0) == 'e') { // Si se trata de una eliminación de un libro reservado
                    try {
                        if (reservaService.dropLibroFromReserva(Integer.parseInt(librosAEliminarOReservar[i].substring(1)))) {
                            librosEliminados.append(libroService.findById(Integer.parseInt(librosAEliminarOReservar[i].substring(1))).get().getTitulo() + ", ");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            librosEliminados.append("]");
            librosReservados.append("]");

            if(librosEliminados.length() > 2 || librosReservados.length() > 2) { // Si se ha eliminado o reservado algún libro
                request.setAttribute("mensaje", "Libros eliminados: " + librosEliminados + " Libros reservados: " + librosReservados);
            }else{ // Si no se ha eliminado o reservado ningún libro
                request.setAttribute("mensaje", "Error, no se ha eliminado o reservado ningún libro");
            }

        }

        if(reservar){ // Si estamos en la vista de libros reservados volvemos a la vista de libros reservados
            request.getRequestDispatcher("/verLibrosReservados").forward(request, response);
        }else{ // Si estamos en la vista de libros volvemos a la vista de libros
            request.getRequestDispatcher("/verLibros").forward(request, response);
        }

    }
}
