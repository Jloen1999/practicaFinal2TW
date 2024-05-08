<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="es.unex.cum.tw.models.Libro" %>

<section id="carouselExampleDark" class="carousel carousel-dark slide">
    <div class="carousel-indicators">
        <%
            List<Libro> libros = (List<Libro>) application.getAttribute("newBooks");
            if (libros != null && !libros.isEmpty()) {
                for (int i = 0; i < libros.size(); i++) {%>
        <button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="<%=i%>" class="<%=i == 0 ? "active" : ""%>" aria-current="<%=i == 0 ? "true" : ""%>" aria-label="Slide <%=(i+1)%>"></button>
        <%}%>
        <%} else {%>
        <button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
        <%}%>
    </div>

    <div class="carousel-inner">
        <%
            if (libros != null && !libros.isEmpty()) {
                for (int i = 0; i < libros.size(); i++) {%>
        <div class="carousel-item <%= i == 0 ? "active" : ""%>" data-bs-interval="2000">
            <svg class="bd-placeholder-img bd-placeholder-img-lg d-block w-100" width="800" height="400"
                 xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: First slide"
                 preserveAspectRatio="xMidYMid slice" focusable="false"><title><%=libros.get(i).getTitulo()%></title>
                <rect width="100%" height="100%" fill="#f5f5f5"></rect>
                <text x="50%" y="16%" fill="#aaa" dy=".3em"><%=libros.get(i).getTitulo()%>
                </text>
            </svg>
            <div class="carousel-caption">
                <img src="<%=libros.get(i).getUrlImg()%>" alt="Imagen del libro de <%=libros.get(i).getTitulo()%>">
                <p><%=libros.get(i).getDescripcion()%>
                </p>
            </div>
        </div>

        <%}%>
        <%} else {%>
        <div class="carousel-item active" data-bs-interval="2000">
            <svg class="bd-placeholder-img bd-placeholder-img-lg d-block w-100" width="800" height="400"
                 xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: First slide"
                 preserveAspectRatio="xMidYMid slice" focusable="false"><title>Vacío</title>
                <rect width="100%" height="100%" fill="#f5f5f5"></rect>
                <text x="50%" y="50%" fill="#aaa" dy=".3em">No hay libros disponibles
                </text>
            </svg>
            <div class="carousel-caption d-none d-md-block">
                <p>No hay libros disponibles</p>
            </div>
        </div>
        <%}%>
    </div>
    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleDark" data-bs-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Anterior</span>
    </button>
    <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleDark" data-bs-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Siguiente</span>
    </button>
</section>


