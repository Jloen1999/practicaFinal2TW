<%@ page import="java.util.List" %>
<%@ page import="es.unex.cum.tw.models.User" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="es">
<%@ include file="head.jsp" %>

<body>
<%@ include file="header.jsp" %>
<!-- Mostrar todos los libros reservados del usuario en una tabla -->
<form action="${pageContext.request.contextPath}/eliminarUsuario" method="get">
    <a href="${pageContext.request.contextPath}/CrearUsuario.jsp" class="btn btn-primary d-flex justify-content-center align-items-center mt-3">Añadir Usuarios</a>

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
        <% List<User> users = (List<User>) session.getAttribute("users"); %>
        <% for (User user : users) { %>
        <tr>
            <td class="table-active"><%= user.getNombre() %>
            </td>
            <td><%= user.getApellidos() %>
            </td>
            <td><%= user.getEmail() %>
            </td>
            <td><%= user.getUsername() %>
            </td>
            <td>
                <label for="delUser" class="visually-hidden"></label>
                <input type="checkbox" id="delUser" name="eliminarUsuarios" value="<%=user.getId()%>">
            </td>

        </tr>
        <% } %>
        </tbody>
    </table>

    <button type="submit" class="btn btn-primary">Eliminar Usuarios</button>

</form>

<%@ include file="footer.jsp" %>
<%@ include file="scriptsJS.jsp" %>
</body>
</html>
