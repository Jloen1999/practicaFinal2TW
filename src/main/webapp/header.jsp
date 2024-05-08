<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<header>
    <nav class="navbar navbar-expand-lg bg-body-tertiary border-body" data-bs-theme="dark">
        <div class="container-fluid">
            <div class="d-flex flex-column">
                <img src="./images/logoUnex.svg" alt="Logo de la página">
                <a class="navbar-brand" href="${pageContext.request.contextPath}">BiblioCum</a>
            </div>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown"
                    aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavDropdown">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/verLibros">Libros</a>
                    </li>
                    <% if(session.getAttribute("admin") != null && (Boolean)session.getAttribute("admin")) { %>
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/admin">Admin</a>
                    </li>
                    <% } %>

                    <%@ include file="DropdownLibrosReservados.jsp" %>

                    <%@ include file="login.jsp" %>
                </ul>
            </div>
        </div>
        <% if(request.getAttribute("mensaje") != null) { %>
        <% if(((String) request.getAttribute("mensaje")).toLowerCase().contains("no") || ((String) request.getAttribute("mensaje")).toLowerCase().contains("error")) { %>
        <div class="alert alert-danger alert-dismissible fade show" role="alert">
            <strong>Error:</strong> <%= request.getAttribute("mensaje") %>
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
        <% }else{%>
        <div class="alert alert-success alert-dismissible fade show" role="alert">
            <strong>Éxito:</strong> <%= request.getAttribute("mensaje") %>
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
        <% } %>
        <% } %>

        <form class="d-flex w-50" role="search" action="${pageContext.request.contextPath}/filtroLibros" method="get">
            <input class="form-control me-2" type="search" placeholder="Buscar libro" aria-label="Search" name="buscarLibro">
            <button class="btn btn-outline-success" type="submit">Buscar</button>
        </form>


    </nav>
</header>