<%@ page import="java.util.List" %>
<%@ page import="es.unex.cum.tw.models.Libro" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<li class="nav-item dropdown">
    <a class="nav-link dropdown-toggle" type="button" href="#" role="button"
       data-bs-toggle="dropdown" aria-expanded="false">
        Libros reservados
    </a>
    <ul class="dropdown-menu dropdown-menu-dark">
        <% if (session.getAttribute("user") != null) {
            if (session.getAttribute("librosReservados") != null) {
                List<Libro> libros = (List<Libro>) session.getAttribute("librosReservados");
                for (Libro book : libros) {%>
        <li><a href="${pageContext.request.contextPath}/verLibrosReservados?reservar=true&id=<%=book.getIdLibro()%>"
               class="dropdown-item"><%=book.getTitulo()%></a></li>
                <%}%>
            <%} else {%>
                    <li><a class="dropdown-item" href="#">
                        No hay libros reservados
                    </a></li>
            <%}%>
        <%} else {%>
                <li><a class="dropdown-item" href="#">
                    Debes iniciar sesión para ver tus libros reservados
                </a></li>
        <%}%>


    </ul>

</li>