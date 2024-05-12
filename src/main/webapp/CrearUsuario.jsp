<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="es">
<%@ include file="./layouts/head.jsp"%>

<body class="h-100">
<%@ include file="./layouts/header.jsp"%>

<main class="h-100">
    <div class="container-md my-5">
        <form class="row g-2 bg-dark text-white" id="formulario"
              action="${pageContext.request.contextPath}/crearUsuario"
              method="post">

            <!-- Campos del formulario para crear un usuario -->
            <div class="col-md-6 col-lg-4">
                <label for="name" class="form-label">Nombre</label>
                <input type="text" class="form-control" id="name" name="name" aria-describedby="nameHelp">
                <div class="valid-feedback">
                    Genial!
                </div>
                <div id="nameHelp" class="form-text text-secondary text-secondary">Debe tener al menos 3 caracteres</div>
            </div>

            <div class="col-md-6 col-lg-4">
                <label for="lastname1" class="form-label">Apellidos</label>
                <input type="text" class="form-control" id="lastname1" name="lastname" aria-describedby="lastnameHelp1">
                <div class="valid-feedback">
                    Genial!
                </div>
                <div id="lastnameHelp1" class="form-text text-secondary text-secondary">Debe tener al menos 3 caracteres</div>
            </div>

            <div class="col-md-6 col-lg-4">
                <label for="email" class="form-label">Email</label>
                <input type="email" class="form-control" id="email" name="email" aria-describedby="emailHelp1">
                <div class="valid-feedback">
                    Genial!
                </div>
                <div id="emailHelp1" class="form-text text-secondary">Debe ser un correo electrónico válido</div>
            </div>

            <div class="col-md-6 col-lg-4" id="campo-username">
                <label for="username" class="form-label">Usuario</label>
                <input type="text" class="form-control" id="username"
                       name="username" required aria-describedby="usernameHelp1">
                <div class="valid-feedback">
                    Genial!
                </div>
                <div id="usernameHelp1" class="form-text text-secondary">Debe tener al menos 3 caracteres</div>
            </div>

            <div class="col-md-6 col-lg-4">
                <label for="password" class="form-label">Contraseña</label>
                <input type="password" class="form-control" id="password"
                       name="password"
                       required aria-describedby="passwordHelp1">
                <div class="valid-feedback">
                    Genial!
                </div>
                <div id="passwordHelp1" class="form-text text-secondary">Debe tener al menos 6 caracteres</div>
            </div>
            <div class="col-md-6 col-lg-4">
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

            <!-- Botón para crear usuarios -->
            <div class="col-6 d-flex justify-content-start">
                <button class="btn btn-primary" type="submit">Crear Usuario</button>
            </div>

            <!-- Botón para eliminar usuarios -->
            <div class="col-6 d-flex justify-content-end">
                <a class="btn btn-primary" type="button" href="${pageContext.request.contextPath}/admin">Eliminar
                    Usuarios</a>
            </div>
        </form>
    </div>
</main>

<%@ include file="./layouts/footer.jsp"%>
<%@ include file="js/scriptsJS.jsp"%>
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
</body>
</html>



