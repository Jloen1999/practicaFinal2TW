<%@ page import="es.unex.cum.tw.models.Libro" %>
<%@ page import="java.util.List" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<%@ include file="head.jsp" %>
<%@ include file="header.jsp" %>
<!-- Mostrar todos los libros reservados del usuario en una tabla -->
<form action="${pageContext.request.contextPath}/validarEliminarReservarLibro?reservar=true" method="get"
      class="container-lg mt-3">

    <div class="d-flex justify-content-center mb-3">
        <button type="submit" class="btn btn-success">Eliminar reservas</button>
    </div>
    <table class="table table-hover table-dark table-striped" style="font-size: 12px;">
        <thead>
        <tr>
            <th scope="col">Título</th>
            <th scope="col">Autor</th>
            <th scope="col" class="d-none d-lg-table-cell">Temática</th>
            <th scope="col">Descripción</th>
            <th scope="col">Puntuación</th>
            <th scope="col" class="d-none d-lg-block"><i class="bi bi-card-image"></i></th>
            <th scope="col" class="d-none d-lg-block">Novedad</th>
            <th scope="col">Eliminar reserva</th>
        </tr>
        </thead>
        <tbody>
        <% List<Libro> libros = (List<Libro>) session.getAttribute("librosReservados"); %>
        <% for (Libro libro : libros) { %>
        <tr class="<%=request.getAttribute("idLibro").equals(libro.getIdLibro()) ? "table-warning" : ""%>">
            <td class="table-active"><%= libro.getTitulo() %>
            </td>
            <td><%= libro.getAutor() %>
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
            <td class="d-none d-lg-block"><img src="<%=libro.getUrlImg()%>" alt="Imagen de <%=libro.getTitulo()%>"></td>
            <td class="d-none d-lg-block">
                <% if (libro.isNovedad()) { %>
                <i class="bi bi-check"></i>
                <% } else { %>
                <i class="bi bi-x"></i>
                <% } %>
            </td>
            <td>
                <label for="delReserva" class="visually-hidden"></label>
                <input type="checkbox" id="delReserva" name="eliminarReservar" value="-e<%=libro.getIdLibro()%>">
            </td>

        </tr>
        <% } %>
        </tbody>
    </table>

    <div class="d-flex justify-content-center mb-3">
        <button type="submit" class="btn btn-success">Eliminar reservas</button>
    </div>
</form>

<%@ include file="footer.jsp" %>
<%@ include file="scriptsJS.jsp" %>
