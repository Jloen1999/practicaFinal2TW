<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<%
    String showOffCanva = "";
    if (session.getAttribute("isSignUp") != null) {
        Boolean isSignUp = (Boolean) session.getAttribute("isSignUp");
        if (isSignUp) {
            showOffCanva = "show";
        }
    }
%>

<li class="nav-item">
    <a class="nav-link d-lg-none d-sm-block" type="button" data-bs-toggle="offcanvas"
       data-bs-target="#offcanvasRight" aria-controls="offcanvasRight"><i
            class="bi bi-person text-white fs-4"></i>
        <% if (session.getAttribute("user") == null) { %>
            <% if (session.getAttribute("isSignUp") == null) { %>
                Iniciar sesión
            <% } else { %>
                Registrarse
            <% } %>
        <% } else { %>
            <!-- Mostrar el usuario en un elemento p con el nombre de usuario-->
            <span class="text-success fs-6"><%= ((User) session.getAttribute("user")).getUsername() %></span>
        <% } %>

    </a>

    <% if (session.getAttribute("isSignUp") == null) { %>
    <a class="nav-link d-sm-none d-lg-inline" href="#"><i
            class="bi bi-person text-white fs-4"></i>Iniciar sesión</a>
    <% } else { %>
    <a class="nav-link d-sm-none d-lg-inline" href="#"><i
            class="bi bi-person text-white fs-4"></i>Registrarse</a>
    <% } %>
    <div class="offcanvas offcanvas-end <%= showOffCanva %> d-lg-none" tabindex="-1" id="offcanvasRight"
         aria-labelledby="offcanvasRightLabel">

        <% if (session.getAttribute("user") == null) { %>
        <div class="offcanvas-header">
            <% if (session.getAttribute("isSignUp") == null) { %>
            <h5 class="offcanvas-title" id="offcanvasRightLabel">Inicio de sesión</h5>
            <% } else { %>
            <h5 class="offcanvas-title" id="offcanvasRightLabel">Registro</h5>
            <% } %>
            <button type="button" class="btn-close d-lg-none" data-bs-dismiss="offcanvas"
                    aria-label="Close"></button>
        </div>

        <div class="offcanvas-body small">
            <form class="row g-3"

                  id="formulario" action="${pageContext.request.contextPath}/action"
                  method="post">

                <% if (session.getAttribute("isSignUp") != null && (Boolean) session.getAttribute("isSignUp")) { %>
                <div class="col-md-12">
                    <label for="name" class="form-label">Nombre</label>
                    <input type="text" class="form-control" id="name" value="" name="name">
                    <div class="valid-feedback">
                        Genial!
                    </div>
                </div>

                <div class="col-md-12">
                    <label for="lastname" class="form-label">Apellidos</label>
                    <input type="text" class="form-control" id="lastname" name="lastname">
                    <div class="valid-feedback">
                        Genial!
                    </div>
                </div>

                <div class="col-md-12">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" class="form-control" id="email" value="" name="email">
                    <div class="valid-feedback">
                        Genial!
                    </div>
                </div>
                <%}%>

                <div class="col-md-12" id="campo-username">
                    <label for="username" class="form-label">Usuario</label>
                    <input type="text" class="form-control" id="username" value="
<%if (session.getAttribute("username") != null) {%>
<%= (String) session.getAttribute("username") %>
<%}%>
"
                           name="username" required>
                    <div class="valid-feedback">
                        Genial!
                    </div>
                </div>

                <div class="col-md-12">
                    <label for="password" class="form-label">Contraseña</label>
                    <input type="password" class="form-control" id="password" value="<%if (session.getAttribute("password") != null) {%>
<%= (String) session.getAttribute("password") %><%}%>" name="password" required>
                    <div class="valid-feedback">
                        Genial!
                    </div>
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

                <% if (session.getAttribute("isSignUp") == null) { %>
                <div class="col-6">
                    <button class="btn btn-primary" type="submit" value="login" name="accion" id="login">Iniciar
                        sesión
                    </button>
                </div>
                <%}%>
                <div class="col-6">
                    <button class="btn btn-primary" type="submit" value="signup" name="accion" id="registrarse">
                        Registrarse
                    </button>
                </div>

            </form>
        </div>
        <%} else {%>
        <div class="col-12 text-center w-25 h-25">
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/logout">Cerrar
                sesión</a>
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
                if (input.id === 'name' || input.id === 'lastname' || input.id === 'username') {
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
                if (input.id === 'email') {
                    if (!input.value.includes('@')) {
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
                if (input.id === 'password') {
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