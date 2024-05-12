<%@ page import="java.util.List" %>
<%@ page import="es.unex.cum.tw.models.User" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="es">
<%@ include file="./layouts/head.jsp" %>

<body>
<%@ include file="./layouts/header.jsp" %>
<form action="${pageContext.request.contextPath}/eliminarUsuario" method="get">
    <a href="${pageContext.request.contextPath}/CrearUsuario.jsp" class="btn btn-primary d-flex justify-content-center align-items-center mt-3">Añadir Usuarios</a>

    <!-- Tabla de usuarios -->
    <table class="table table-hover table-dark table-striped my-3">
        <thead>
        <tr>
            <th scope="col">Nombre</th>
            <th scope="col">Apellidos</th>
            <th scope="col">Email</th>
            <th scope="col">Username</th>
            <th scope="col">Eliminar Usuario</th>
        </tr>
        </thead>
        <tbody>
        <% List<User> users = (List<User>) session.getAttribute("users"); %> <!-- Recuperamos la lista de usuarios -->
        <% for (User user : users) { %> <!-- Iteramos sobre la lista de usuarios -->
        <tr> <!-- Mostramos los datos de cada usuario -->
            <td class="table-active"><%= user.getNombre() %>
            </td>
            <td><%= user.getApellidos() %>
            </td>
            <td><%= user.getEmail() %>
            </td>
            <td><%= user.getUsername() %>
            </td>
            <td>
                <label for="delUser" class="visually-hidden"></label> <!-- Creamos un checkbox para eliminar usuarios -->
                <input type="checkbox" id="delUser" name="eliminarUsuarios" value="<%=user.getId()%>"> <!-- Pasamos el id del usuario a eliminar -->
            </td>

        </tr>
        <% } %>
        </tbody>
    </table>

    <button type="submit" class="btn btn-primary">Eliminar Usuarios</button>

</form>

<%@ include file="./layouts/footer.jsp" %>
<%@ include file="js/scriptsJS.jsp" %>
</body>
</html>
