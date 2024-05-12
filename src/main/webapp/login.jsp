<%@ page import="es.unex.cum.tw.models.User" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<li class="nav-item">
    <a class="nav-link d-lg-none d-block" type="button" data-bs-toggle="offcanvas"
       data-bs-target="#offcanvasRight" aria-controls="offcanvasRight"> <!-- Botón para abrir el offcanvas / ventana lateral -->
        <% if (session.getAttribute("user") == null && session.getAttribute("isSignUp") == null) { %> <!-- Si el usuario no está logueado y no está en el formulario de registro -->
        <i class="bi bi-person text-white fs-5">Iniciar sesión</i> <!-- Mostrar el icono de iniciar sesión -->
        <% } else if (session.getAttribute("user") == null && session.getAttribute("isSignUp") != null) { %> <!-- Si el usuario no está logueado y está en el formulario de registro -->
        <i class="bi bi-person text-white fs-4">Registrarse</i> <!-- Mostrar el icono de registrarse -->
        <% } %>

    </a>

    <!-- Para dispositivos grandes -->
    <% if (session.getAttribute("user") == null) { %> <!-- Si el usuario no está logueado -->
    <a class="nav-link d-none d-lg-block" type="button" data-bs-toggle="modal"
       data-bs-target="#staticBackdrop"> <!-- Mostrar el modal / ventana central -->

        <% if (session.getAttribute("user") == null && session.getAttribute("isSignUp") == null) { %> <!-- Si el usuario no está logueado y no está en el formulario de registro -->
        <i class="bi bi-person text-white fs-4">Iniciar sesión</i> <!-- Mostrar el icono de iniciar sesión -->
        <% } else if (session.getAttribute("user") == null && session.getAttribute("isSignUp") != null) { %> <!-- Si el usuario no está logueado y está en el formulario de registro -->
        <i class="bi bi-person text-white fs-4">Registrarse</i> <!-- Mostrar el icono de registrarse -->
        <% } %>
    </a>
    <% } else { %>

    <div class="d-flex">
        <span class="text-success fs-6 text-green"><%= ((User) session.getAttribute("user")).getUsername() %></span> <!-- Mostrar el nombre de usuario -->
        <a class="btn btn-danger" href="${pageContext.request.contextPath}/logout">Cerrar sesión</a>
    </div>
    <% }%>


    <%@ include file="loginLg.jsp" %>

    <div class="offcanvas offcanvas-end d-lg-none" tabindex="-1" id="offcanvasRight"
         aria-labelledby="offcanvasRightLabel">

        <% if (session.getAttribute("user") == null) { %> <!-- Si el usuario no está logueado -->
        <div class="offcanvas-header">
            <% if (session.getAttribute("isSignUp") == null) { %> <!-- Si el usuario no está logueado y no está en el formulario de registro -->
            <h5 class="offcanvas-title" id="offcanvasRightLabel">Inicio de sesión</h5> <!-- Mostrar el título de inicio de sesión -->
            <% } else { %>
            <h5 class="offcanvas-title" id="offcanvasRightLabel">Registro</h5> <!-- Mostrar el título de registro -->
            <% } %>
            <button type="button" class="btn-close d-lg-none" data-bs-dismiss="offcanvas"
                    aria-label="Close"></button>
        </div>

        <div class="offcanvas-body small">
            <form class="row g-3"

                  id="formulario" action="${pageContext.request.contextPath}/actionSignInUp"
                  method="post">

                <% if (session.getAttribute("isSignUp") != null && (Boolean) session.getAttribute("isSignUp")) { %> <!-- Si el usuario no está logueado y está en el formulario de registro -->
                <!-- Mostrar los campos de nombre, apellidos y correo electrónico ya que estamos registrando el usuario-->
                <div class="col-md-12">
                    <label for="name" class="form-label">Nombre</label>
                    <input type="text" class="form-control" id="name" value="" name="name" aria-describedby="nameHelp">
                    <div class="valid-feedback">
                        Genial!
                    </div>
                    <div id="nameHelp" class="form-text">Debe tener al menos 3 caracteres</div>
                </div>

                <div class="col-md-12">
                    <label for="lastname" class="form-label">Apellidos</label>
                    <input type="text" class="form-control" id="lastname" name="lastname" aria-describedby="lastnameHelp">
                    <div class="valid-feedback">
                        Genial!
                    </div>
                    <div id="lastnameHelp" class="form-text">Debe tener al menos 3 caracteres</div>
                </div>

                <div class="col-md-12">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" class="form-control" id="email" value="" name="email" aria-describedby="emailHelp">
                    <div class="valid-feedback">
                        Genial!
                    </div>
                    <div id="emailHelp" class="form-text">Debe tener al menos 3 caracteres</div>
                </div>
                <%}%>

                <!-- Mostrar el campo de nombre de usuario y contraseña tanto en inicio de sesión como en registro -->
                <div class="col-md-12" id="campo-username">
                    <label for="username" class="form-label">Usuario</label>
                    <input type="text" class="form-control" id="username"
                           value="<%= session.getAttribute("username") == null ? "" : (String) session.getAttribute("username")%>"
                           name="username" required aria-describedby="usernameHelp">
                    <div class="valid-feedback">
                        Genial!
                    </div>
                    <div id="usernameHelp" class="form-text">Debe tener al menos 3 caracteres</div>
                </div>

                <div class="col-md-12">
                    <label for="password" class="form-label">Contraseña</label>
                    <input type="password" class="form-control" id="password"
                           value="<%= session.getAttribute("password") == null ? "" : (String) session.getAttribute("password")%>"
                           name="password"
                           required aria-describedby="passwordHelp">
                    <div class="valid-feedback">
                        Genial!
                    </div>
                    <div id="passwordHelp" class="form-text">Debe tener al menos 6 caracteres</div>
                </div>
                <div class="col-12">
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" value="" id="invalidCheck">
                        <label class="form-check-label" for="invalidCheck">
                            Acepta los términos y condiciones
                        </label>
                        <div class="invalid-feedback">
                            Debes aceptar antes de enviar.
                        </div>
                    </div>
                </div>

                <% if (session.getAttribute("isSignUp") == null) { %> <!-- Si no estamos en el formulario de registro -->
                <div class="col-6">
                    <button class="btn btn-primary" type="submit" value="login" name="accion" id="login">Iniciar
                        sesión
                    </button>
                </div>
                <%}%>

                <!-- Mostrar el botón de registrarse tanto si estamos en el formulario de registro como si no -->
                <div class="col-6">
                    <button class="btn btn-primary" type="submit" value="signup" name="accion" id="registrarse">
                        Registrarse
                    </button>
                </div>

            </form>
        </div>
        <%}%>
    </div>
</li>

<script>
    document.addEventListener('DOMContentLoaded', function () {
        const form = document.getElementById('formulario');

        // Agregar un evento de escucha para el envío del formulario
        form.addEventListener('input', function (event) {
            const inputs = form.querySelectorAll('input'); // Obtener todos los elementos input dentro del formulario

            // Iterar sobre cada input para validarlos
            inputs.forEach(function (input) {
                // Si el input es del campo del nombre de usuario, validar que tenga al menos 3 caracteres
                if (input.name === 'name' || input.name === 'lastname' || input.name === 'username') {
                    if (input.value.length < 3) {
                        // Si el campo no tiene al menos 3 caracteres, establecer el mensaje de error
                        input.setCustomValidity('El campo debe tener al menos 3 caracteres');
                        input.classList.remove('is-valid');
                        input.classList.add('is-invalid');
                    } else {
                        // Si el campo tiene al menos 3 caracteres, establecer el mensaje de éxito
                        input.setCustomValidity('');
                        input.classList.remove('is-invalid');
                        input.classList.add('is-valid');
                    }
                }

                // Si el input es del campo del correo electrónico, validar que sea un correo electrónico válido
                if (input.name === 'email') {
                    const emailRegex = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
                    const matched = input.value.match(emailRegex);
                    if (matched === null) {
                        // Si el campo no es un correo electrónico válido, establecer el mensaje de error
                        input.setCustomValidity('El correo electrónico debe ser válido');
                        input.classList.remove('is-valid');
                        input.classList.add('is-invalid');
                    } else {
                        // Si el campo es un correo electrónico válido, establecer el mensaje de éxito
                        input.setCustomValidity('');
                        input.classList.remove('is-invalid');
                        input.classList.add('is-valid');
                    }
                }


                // Si el input es del campo de la contraseña, validar que tenga al menos 6 caracteres
                if (input.name === 'password') {
                    if (input.value.length < 6) {
                        // Si el campo no tiene al menos 6 caracteres, establecer el mensaje de error
                        input.setCustomValidity('La contraseña debe tener al menos 6 caracteres');
                        input.classList.remove('is-valid');
                        input.classList.add('is-invalid');
                    } else {
                        // Si el campo tiene al menos 6 caracteres, establecer el mensaje de éxito
                        input.setCustomValidity('');
                        input.classList.remove('is-invalid');
                        input.classList.add('is-valid');
                    }
                }


            });

            // Evitar que se envíe el formulario si hay campos inválidos
            if (!form.checkValidity()) {
                event.preventDefault();
                event.stopPropagation();
            }

            form.classList.add('was-validated');
        });

        // Agregar eventos de escucha para los cambios en los inputs
        const inputs = form.querySelectorAll('input');
        inputs.forEach(function (input) {
            input.addEventListener('input', function () {
                if (input.checkValidity()) {
                    input.classList.remove('is-invalid');
                    input.classList.add('is-valid');
                } else {
                    input.classList.remove('is-valid');
                    input.classList.add('is-invalid');
                }
            });
        });
    });

</script>