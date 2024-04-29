<%@ page import="es.unex.cum.tw.models.Reserva" %>
<%@ page import="java.util.List" %>
<%@ page import="es.unex.cum.tw.models.Libro" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<li class="nav-item dropdown">
    <a class="nav-link dropdown-toggle" href="libros.html#libro-container" role="button"
       data-bs-toggle="dropdown" aria-expanded="false">
        Libros reservados
    </a>
    <ul class="dropdown-menu dropend">
        <%if (session.getAttribute("user") != null) {%>
            <%if (session.getAttribute("libros") != null) {%>
                <%
                    List<Libro> libros = (List<Libro>) session.getAttribute("libros");
                    for (Libro libro : libros) {
                %>
                        <li><a class="dropdown-item" href="#"><%=libro.getTitulo()%></a></li>
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
