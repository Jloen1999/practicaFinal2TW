<%@ page import="es.unex.cum.tw.models.Libro" %>
<%@ page import="java.util.List" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="es">
<%@ include file="head.jsp" %>

<body>
<%@ include file="header.jsp" %>

<main class="container-md">
    <form action="<% if (request.getAttribute("admin") != null && (Boolean) request.getAttribute("admin")) { %> ${pageContext.request.contextPath}/eliminarLibros <% } else { %>
    ${pageContext.request.contextPath}/validarEliminarReservarLibro
    <% } %>"
          method="get" class="my-3">

        <div class="d-flex justify-content-around mb-3"><% if (session.getAttribute("user") != null) { %>
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
            <% List<Libro> libros = (List<Libro>) session.getAttribute("libros"); %>
            <% for (Libro libro : libros) { %>
            <tr>
                <td class="table-active"><%= libro.getTitulo() %>
                </td>
                <td class="d-none d-lg-table-cell"><%= libro.getAutor() %>
                </td>
                <td class="d-none d-lg-table-cell"><%= libro.getTematica() %>
                </td>
                <td><%= libro.getDescripcion() %>
                </td>
                <td><% for (int i = 0; i < (int)libro.getPuntuacion(); i++) {
                    out.print("<i class='bi bi-star-fill text-warning'></i>");
                }
                    for (int i = 0; i < 5 - (int) libro.getPuntuacion(); i++) {
                        out.print("<i class='bi bi-star'></i>");
                    }%>
                </td>
                <td class="d-none d-lg-table-cell"><img src="<%=libro.getUrlImg()%>" alt="Imagen de <%=libro.getTitulo()%>">
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
                <td>
                    <label for="delLibro" class="visually-hidden"></label>
                    <input type="checkbox" id="delLibro" name="librosAEliminar" value="<%=libro.getIdLibro()%>">
                </td>
                <% } else { %>
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
                    <input type="number" class="form-control" id="puntuacion" name="puntuaciones<%=libro.getIdLibro()%>" min="0" max="5">
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

<%@ include file="footer.jsp" %>
<%@ include file="scriptsJS.jsp" %>

</body>
</html>