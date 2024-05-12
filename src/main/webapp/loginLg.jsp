<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<!-- Activar este modal siempre solo para dispositivos grandes -->
<div class="modal fade text-white" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
     aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <% if (session.getAttribute("isSignUp") == null) { %>
                <h5 class="modal-title fs-5" id="staticBackdropLabel">Inicio de sesión</h5>
                <% } else { %>
                <h5 class="modal-title fs-5" id="staticBackdropLabel">Registro</h5>
                <% } %>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body small">
                <% if (session.getAttribute("user") == null) { %>
                <form class="row g-3" id="formulario1" action="${pageContext.request.contextPath}/actionSignInUp" method="post">

                    <% if (session.getAttribute("isSignUp") != null && (Boolean) session.getAttribute("isSignUp")) {%>
                    <div class="col-md-12">
                        <label for="name1" class="form-label">Nombre</label>
                        <input type="text" class="form-control" id="name1" value="" name="name">
                        <div class="valid-feedback">
                            Genial!
                        </div>
                        <div id="nameHelp" class="form-text">Debe tener al menos 3 caracteres</div>
                    </div>

                    <div class="col-md-12">
                        <label for="lastname1" class="form-label">Apellidos</label>
                        <input type="text" class="form-control" id="lastname1" name="lastname">
                        <div class="valid-feedback">
                            Genial!
                        </div>
                        <div id="lastnameHelp" class="form-text">Debe tener al menos 3 caracteres</div>
                    </div>

                    <div class="col-md-12">
                        <label for="email1" class="form-label">Email</label>
                        <input type="email" class="form-control" id="email1" value="" name="email">
                        <div class="valid-feedback">
                            Genial!
                        </div>
                        <div id="emailHelp" class="form-text">Debe tener al menos 3 caracteres</div>
                    </div>
                    <%}%>

                    <div class="col-md-12" id="campo-username">
                        <label for="username1" class="form-label">Usuario</label>
                        <input type="text" class="form-control" id="username1" value="<%= session.getAttribute("username") == null ? "" : (String) session.getAttribute("username")%>"
                               name="username" required>
                        <div class="valid-feedback">
                            Genial!
                        </div>
                        <div id="usernameHelp" class="form-text">Debe tener al menos 3 caracteres</div>
                    </div>

                    <div class="col-md-12">
                        <label for="password1" class="form-label">Contraseña</label>
                        <input type="password" class="form-control" id="password1"
                               value="<%= session.getAttribute("password") == null ? "" : (String) session.getAttribute("password")%>" name="password" required>
                        <div class="valid-feedback">
                            Genial!
                        </div>
                    </div>
                    <div class="col-12">
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" value="" id="invalidCheck1">
                            <label class="form-check-label" for="invalidCheck1">
                                Acepta los términos y condiciones
                            </label>
                            <div class="invalid-feedback">
                                Debes aceptar antes de enviar.
                            </div>
                        </div>
                    </div>

                    <div class="modal-footer row">
                        <% if (session.getAttribute("isSignUp") == null) { %>
                        <div class="col-3">
                            <button class="btn btn-primary" type="submit" value="login" name="accion" id="login1">
                                Iniciar
                                sesión
                            </button>
                        </div>
                        <%}%>
                        <div class="col-3">
                            <button class="btn btn-primary" type="submit" value="signup" name="accion"
                                    id="registrarse1">
                                Registrarse
                            </button>
                        </div>
                    </div>

                </form>
                <%}%>
            </div>

        </div>
    </div>
</div>

<script>
    document.addEventListener('DOMContentLoaded', function () {
        const form = document.getElementById('formulario1');

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