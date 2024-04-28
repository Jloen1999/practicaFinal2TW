// Función para validar el nombre
function validarNombre(inputName){
    const nombre = inputName.value.trim();
    // Comprobar que el campo nombre no empiece con un número y tenga al menos 3 caracteres
    if (!isNaN(nombre.charAt(0)) || nombre.length < 3) {
        alert('El nombre debe tener al menos 3 caracteres y no puede empezar con un número.');
        inputName.style.borderColor = 'red';
        return false;
    }else{
        inputName.style.borderColor = 'green';
        return true;
    }
}

// Función para validar el email
function validarEmail(inputEmail){
    const email = inputEmail.value.trim();
    // Expresión regular para validar el formato del email
    const emailRegex = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    if (!emailRegex.test(email)) {
        alert('Por favor, introduzca una dirección de correo electrónico válida.');
        inputEmail.style.borderColor = 'red';
        return false;
    }else{
        inputEmail.style.borderColor = 'green';
        return true;
    }
}

// Función para validar el teléfono
function validarTelefono(inputTelefono){
    const telefono = inputTelefono.value.trim();
    // Expresión regular para validar el formato del teléfono
    const telefonoRegex = /^\d{3}-\d{3}-\d{3}$/;
    if (!telefonoRegex.test(telefono)) {
        alert('Por favor, introduzca un número de teléfono válido en el formato xxx-xxx-xxx.');
        inputTelefono.style.borderColor = 'red';
        return false;
    }else{
        inputTelefono.style.borderColor = 'green';
        return true;
    }
}

// Función para validar la fecha de reserva
function validarFechaReserva(inputFechaReserva){
    const fechaReserva = inputFechaReserva.value.trim();
    const fechaActual = new Date().toISOString().split('T')[0];
    if (new Date(fechaReserva) < new Date(fechaActual)) {
        alert('La fecha de reserva no puede ser anterior a la fecha actual.');
        inputFechaReserva.style.borderColor = 'red';
        return false;
    }else{
        inputFechaReserva.style.borderColor = 'green';
        return true;
    }
}

// Función para validar la fecha de devolución
function validarFechaDevolucion(inputFechaDevolucion){
    const fechaDevolucion = inputFechaDevolucion.value.trim();
    const fechaReserva = document.getElementById('fechaReserva').value;
    if (new Date(fechaReserva) > new Date(fechaDevolucion)) {
        alert('La fecha de reserva no puede ser posterior a la fecha de devolución.');
        inputFechaDevolucion.style.borderColor = 'red';
        return false;
    }else{
        inputFechaDevolucion.style.borderColor = 'green';
        return true;
    }
}

// Función para validar la selección de biblioteca
function validarBiblioteca(){
    const biblioteca = document.querySelector('input[name="education"]:checked');
    if (!biblioteca) {
        alert('Por favor, seleccione una biblioteca.');
        return false;
    }else{
        return true;
    }
}

// Función para validar la selección de libro
function validarLibro(inputLibro){
    const libro = inputLibro.value.trim();
    if (libro === '') {
        alert('Por favor, seleccione un libro.');
        inputLibro.style.borderColor = 'red';
        return false;
    }else{
        return true;
    }
}

// Función principal para validar el formulario
function validarFormulario() {
    // Obtener los valores de los campos del formulario
    const nombre = document.getElementById('username');
    const email = document.getElementById('email')
    const telefono = document.getElementById('telefono');
    const fechaReserva = document.getElementById('fechaReserva');
    const fechaDevolucion = document.getElementById('fechaDevolucion');
    const biblioteca = document.querySelector('input[name="education"]:checked');
    const libro = document.getElementById('libro-select');
    const informacionAdicional = document.getElementById('more');

    // Validar todos los campos
    if(validarNombre(nombre) && validarEmail(email) && validarTelefono(telefono) && validarFechaReserva(fechaReserva) && validarFechaDevolucion(fechaDevolucion) && validarBiblioteca() && validarLibro(libro)){
        alert('Formulario enviado correctamente.');
        return true;
    }else{
        alert('Por favor, complete todos los campos correctamente.');
        return false;
    }
}
