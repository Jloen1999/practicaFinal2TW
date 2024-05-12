<%@ page import="es.unex.cum.tw.models.Libro" %>
<%@ page import="java.util.List" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="es">
<%@ include file="./layouts/head.jsp" %>

<body>
<%@ include file="./layouts/header.jsp" %>

<main class="container-md">
    <form action="<% if (request.getAttribute("admin") != null && (Boolean) request.getAttribute("admin")) { // Si el usuario logueado es el administrador se podrá eliminar libros %>
        ${pageContext.request.contextPath}/eliminarLibros
    <% } else { // Si el usuario logueado no es el administrador, se podrá eliminar reservas y reservar libros %>
        ${pageContext.request.contextPath}/validarEliminarReservarLibro
    <% } %>"
          method="get" class="my-3">

        <div class="d-flex justify-content-around mb-3"><% if (session.getAttribute("user") != null) { %>
            <% if (request.getAttribute("admin") != null && (Boolean) request.getAttribute("admin")) { %>
            <button type="submit" class="btn btn-danger">Eliminar libros</button> <!-- Si el usuario logueado es el administrador, se mostrará el botón para eliminar libros -->
            <a type="button" class="btn btn-success" href="${pageContext.request.contextPath}/AnadirLibro.jsp">Añadir
                libros</a> <!-- Si el usuario logueado es el administrador, se mostrará el botón para añadir libros -->
            <% } else {%>
            <!-- Si el usuario logueado no es el administrador, se mostrarán los botones para eliminar reservas y reservar libros -->
            <button type="submit" class="btn btn-success">Eliminar y/o reservar</button>
            <button type="submit" class="btn btn-warning">Añadir Valoración</button>
            <% } %>
            <% } %>
        </div>

        <!-- Tabla que muestra todos los libros -->
        <table class="table table-hover table-dark table-striped" style="font-size: 12px;">
            <thead>
            <tr>
                <th scope="col">Título</th>
                <th scope="col" class="d-none d-lg-table-cell">Autor</th>
                <th scope="col" class="d-none d-lg-table-cell">Temática</th>
                <th scope="col">Descripción</th>
                <th scope="col">Puntuación</th>
                <th scope="col" class="d-none d-lg-table-cell"><i class="bi bi-card-image"></i></th>
                <th scope="col" class="d-none d-lg-table-cell">Novedad</th>
                <% if (session.getAttribute("user") != null) { %>
                <% if (request.getAttribute("admin") != null && (Boolean) request.getAttribute("admin")) { %>
                <th scope="col">Eliminar libro</th>
                <% } else { %>
                <th scope="col">Eliminar reserva</th>
                <th scope="col">Añadir reserva</th>
                <th scope="col">Añadir puntuacion</th>
                <% } %>
                <% } %>
            </tr>
            </thead>
            <tbody>
            <!-- Recorremos la lista de libros -->
            <% List<Libro> libros = (List<Libro>) session.getAttribute("libros"); %>
            <% for (Libro libro : libros) { %>
            <!-- Mostramos los datos de cada libro -->
            <tr>
                <td class="table-active"><%= libro.getTitulo() %>
                </td>
                <td class="d-none d-lg-table-cell"><%= libro.getAutor() %>
                </td>
                <td class="d-none d-lg-table-cell"><%= libro.getTematica() %>
                </td>
                <td><%= libro.getDescripcion() %>
                </td>
                <td><% out.print("<p>" + libro.getPuntuacion() + "</p>");
                    for (int i = 0; i < (int) (libro.getPuntuacion()); i++) {
                        out.print("<i class='bi bi-star-fill text-warning'></i>");
                    }
                    for (int i = 0; i < 5 - (int) libro.getPuntuacion(); i++) {
                        if (libro.getPuntuacion() % 1 == 0.5 && i ==  0) {
                            out.print("<i class='bi bi-star-half text-warning'></i>");
                        } else {
                            out.print("<i class='bi bi-star'></i>");
                        }
                    }%>
                </td>
                <td class="d-none d-lg-table-cell"><img src="<%=libro.getUrlImg()%>"
                                                        alt="Imagen de <%=libro.getTitulo()%>">
                </td>
                <td class="d-none d-lg-table-cell">
                    <% if (libro.isNovedad()) { %>
                    <i class="bi bi-check"></i>
                    <% } else { %>
                    <i class="bi bi-x"></i>
                    <% } %>
                </td>
                <% if (session.getAttribute("user") != null) { %>
                <% if (request.getAttribute("admin") != null && (Boolean) request.getAttribute("admin")) { %>
                <!-- Si el usuario logueado es el administrador, se mostrará el checkbox para eliminar libros -->
                <td>
                    <label for="delLibro" class="visually-hidden"></label>
                    <input type="checkbox" id="delLibro" name="librosAEliminar" value="<%=libro.getIdLibro()%>">
                </td>
                <% } else { %>
                <!-- Si el usuario logueado no es el administrador, se mostrarán los checkbox para eliminar reservas y reservar libros -->
                <td>
                    <label for="delReserva" class="visually-hidden"></label>
                    <input type="checkbox" id="delReserva" name="eliminarReservar" value="e<%=libro.getIdLibro()%>">
                </td>
                <td>
                    <label for="addReserva" class="visually-hidden"></label>
                    <input type="checkbox" id="addReserva" name="eliminarReservar" value="r<%=libro.getIdLibro()%>">
                </td>
                <td>
                    <label for="puntuacion" class="form-label">Puntuación</label>
                    <!-- concatenar el valor del atributo value con el id del libro -->
                    <input type="number" class="form-control" id="puntuacion" name="puntuaciones<%=libro.getIdLibro()%>"
                           min="0" max="5">
                </td>
                <% } %>
                <% } %>

            </tr>
            <% } %>
            </tbody>
        </table>

        <div class="d-flex justify-content-around mb-3 my-3"><% if (session.getAttribute("user") != null) { %>
            <% if (request.getAttribute("admin") != null && (Boolean) request.getAttribute("admin")) { %>
            <button type="submit" class="btn btn-danger">Eliminar libros</button>
            <a type="button" class="btn btn-success" href="${pageContext.request.contextPath}/AnadirLibro.jsp">Añadir
                libros</a>
            <% } else {%>
            <button type="submit" class="btn btn-success">Eliminar y/o reservar</button>
            <button type="submit" class="btn btn-warning">Añadir Valoración</button>
            <% } %>
            <% } %>
        </div>
    </form>
</main>

<%@ include file="./layouts/footer.jsp" %>
<%@ include file="js/scriptsJS.jsp" %>

</body>
</html>