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
