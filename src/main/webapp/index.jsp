<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="es">

    <!-- Incluir head -->
    <%@ include file="./layouts/head.jsp" %>

<body>
    <!-- Cabecera de la página -->
    <%@ include file="./layouts/header.jsp" %>

    <!-- Contenido principal -->
    <main>
        <!-- Contenedor de novedades -->
        <%@ include file="./layouts/LibrosNovedadCarousel.jsp" %>

        <!-- Contenedor de libros -->
        <%@ include file="./layouts/librosCard.jsp" %>
    </main>

    <!-- Pie de página -->
    <%@ include file="./layouts/footer.jsp" %>

    <!-- Incluir scripts JS -->
    <%@ include file="js/scriptsJS.jsp" %>
</body>

</html>