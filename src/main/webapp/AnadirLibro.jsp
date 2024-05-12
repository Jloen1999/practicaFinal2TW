<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<%@ include file="./layouts/head.jsp" %>
<body>
<%@ include file="./layouts/header.jsp" %>

<!-- Formulario para añadir un libro -->
<main class="h-100">
    <div class="container-md my-5">
        <form class="row g-2 bg-dark text-white" id="formulario"
              action="${pageContext.request.contextPath}/anadirLibro"
              method="post" onsubmit="return submitForm(event)">

            <!-- Input para introducir el título del libro y en base a dicho título se obtendrá la URL de la imagen mediante la API de google Books -->
            <div class="col-md-6 col-lg-4">
                <label for="titulo" class="form-label">Título</label>
                <input type="text" class="form-control" id="titulo" name="titulo" aria-describedby="tituloHelp">
                <div class="valid-feedback">
                    Genial!
                </div>
                <div id="tituloHelp" class="form-text text-secondary text-secondary">Debe tener al menos 1 carácter
                </div>
            </div>

            <!-- Input para introducir el autor del libro -->
            <div class="col-md-6 col-lg-4">
                <label for="autor" class="form-label">Autor</label>
                <input type="text" class="form-control" id="autor" name="autor" aria-describedby="autorHelp">
                <div class="valid-feedback">
                    Genial!
                </div>
                <div id="autorHelp" class="form-text text-secondary">Debe tener al menos 2 caracteres</div>
            </div>

            <!-- Input para introducir la temática del libro -->
            <div class="col-md-6 col-lg-4">
                <label class="form-label">Temática del libro</label>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="tematica" id="terror" value="terror" aria-describedby="tematicaHelp">
                    <label class="form-check-label" for="terror">
                        Terror
                    </label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="tematica" id="romance" value="romance" aria-describedby="tematicaHelp">
                    <label class="form-check-label" for="romance">
                        Romance
                    </label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="tematica" id="aventura" value="aventura" aria-describedby="tematicaHelp">
                    <label class="form-check-label" for="aventura">
                        Aventura
                    </label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="tematica" id="cienciaFiccion"
                           value="ciencia ficcion" aria-describedby="tematicaHelp">
                    <label class="form-check-label" for="cienciaFiccion">
                        Ciencia Ficcion
                    </label>
                </div>

                <div class="form-check">
                    <input class="form-check-input" type="radio" name="tematica" id="desarrolloPersonal"
                           value="Desarrollo Personal" aria-describedby="tematicaHelp">
                    <label class="form-check-label" for="desarrolloPersonal">
                        Desarrollo Personal
                    </label>
                </div>

                <div class="valid-feedback">
                    Genial!
                </div>
                <div id="tematicaHelp" class="form-text text-secondary">Elige una temática</div>
            </div>

            <!-- Textarea para introducir la descripción del libro -->
            <div class="col-md-6 col-lg-4">
                <label for="descripcion" class="form-label">Descripción</label>
                <textarea class="form-control" id="descripcion" name="descripcion" rows="3"></textarea>
            </div>

            <!-- Input para introducir el título del libro para obtener la url de la imagen mediante la API de google books -->
            <div class="col-md-6 col-lg-4 visually-hidden">
                <label for="inputLibro" class="form-label">Imagen</label>
                <input type="text" class="form-control" id="inputLibro" name="imagenLibro" aria-describedby="imagenHelp">
                <div class="valid-feedback">
                    Genial!
                </div>
                <div id="imagenHelp" class="form-text text-secondary">Debe tener al menos 1 carácter
                </div>
            </div>

            <div class="col-md-6 col-lg-4 d-flex gx-4">
                <input class="form-check" type="checkbox" name="novedad" id="novedad" value="true">
                <label for="novedad" class="form-label">Novedad</label>
            </div>

            <!-- Botón para enviar el formulario -->
            <div class="col-md-12 d-flex justify-content-start">
                <button class="btn btn-primary" type="submit">Anadir libro</button>
            </div>


        </form>
    </div>
</main>

<%@ include file="./layouts/footer.jsp" %>
<%@ include file="./js/scriptsJS.jsp" %>

<script>
    document.addEventListener('DOMContentLoaded', function () {
        const form = document.getElementById('formulario');

        // Agregar un evento de escucha para el envío del formulario
        form.addEventListener('input', function (event) {
            const inputs = form.querySelectorAll('input'); // Obtener todos los elementos input dentro del formulario

            // Iterar sobre cada input para validarlos
            inputs.forEach(function (input) {
                // Si el input es del campo del nombre de usuario, validar que tenga al menos 3 caracteres
                if (input.name === 'titulo') {
                    if (input.value.length < 1) {
                        input.setCustomValidity('El campo debe tener al menos 1 carácter');
                        input.classList.remove('is-valid');
                        input.classList.add('is-invalid');
                    } else {
                        input.setCustomValidity('');
                        input.classList.remove('is-invalid');
                        input.classList.add('is-valid');
                    }
                } else if (input.name === 'autor') {
                    if (input.value.length < 2) {
                        input.setCustomValidity('El campo debe tener al menos 2 caracteres');
                        input.classList.remove('is-valid');
                        input.classList.add('is-invalid');
                    } else {
                        input.setCustomValidity('');
                        input.classList.remove('is-invalid');
                        input.classList.add('is-valid');
                    }

                } else if (input.name === 'tematica') {
                    if (input.value.toLowerCase() === 'terror' || input.value.toLowerCase() === 'romance' || input.value.toLowerCase() === 'aventura' || input.value.toLowerCase() === 'ciencia ficcion' || input.value.toLowerCase() === 'desarrollo personal') {
                        input.setCustomValidity('');
                        input.classList.remove('is-invalid');
                        input.classList.add('is-valid');
                    } else {
                        input.setCustomValidity('El campo debe ser terror, romance, aventura, ciencia ficcion o desarrollo personal');
                        input.classList.remove('is-valid');
                        input.classList.add('is-invalid');
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




