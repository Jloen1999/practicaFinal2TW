<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="es">

    <!-- Incluir head -->
    <%@ include file="head.jsp" %>

<body>
    <!-- Cabecera de la página -->
    <%@ include file="header.jsp" %>

    <!-- Contenido principal -->
    <main>
        <!-- Contenedor de novedades -->
        <%@ include file="noticiasCarousel.jsp" %>

        <!-- Contenedor de libros -->
        <%@ include file="librosCard.jsp" %>
    </main>

    <!-- Pie de página -->
    <%@ include file="footer.jsp" %>

    <%@ include file="scriptsJS.jsp" %>
</body>

</html>