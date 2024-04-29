<%@ page import="es.unex.cum.tw.models.User" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<header>
    <nav class="navbar navbar-expand-lg bg-body-tertiary border-body" data-bs-theme="dark">
        <div class="container-fluid">
            <div class="d-flex flex-column">
                <img src="./images/logoUnex.svg" alt="Logo de la página">
                <a class="navbar-brand" href="index.html">BiblioCum</a>
            </div>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown"
                    aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavDropdown">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="novedades.html#novedades-container">Novedades</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="reservas.html#reservas-container">Reservas</a>
                    </li>

                    <%@ include file="librosReservados.jsp" %>

                    <%@ include file="login.jsp" %>
                </ul>
            </div>
        </div>
        <%if (request.getAttribute("mensaje") != null) {%>
        <div class="toast fade show position-fixed top-0 start-50 p-3" role="alert" aria-live="assertive"
             aria-atomic="true">
            <div class="toast-header">
                <svg class="bd-placeholder-img rounded me-2" width="20" height="20" xmlns="http://www.w3.org/2000/svg"
                     aria-hidden="true" preserveAspectRatio="xMidYMid slice" focusable="false">
                    <rect width="100%" height="100%" fill="#007aff"></rect>
                </svg>
                <strong class="me-auto">Estado</strong>
                <small>0 seg</small>
                <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
            </div>
            <div class="toast-body text-danger">
                <% if (((String)request.getAttribute("mensaje")).contains("no")) {%>
                   <p class="text-danger"><%=request.getAttribute("mensaje")%></p>
                <%} else {%>
                    <p class="text-success"><%=request.getAttribute("mensaje")%></p>
                <%}%>
            </div>
        </div>
        <%}%>
        <form class="d-flex" role="search">
            <input class="form-control me-2" type="search" placeholder="Buscar libro" aria-label="Search">
            <button class="btn btn-outline-success" type="submit">Buscar</button>
        </form>
    </nav>
</header>