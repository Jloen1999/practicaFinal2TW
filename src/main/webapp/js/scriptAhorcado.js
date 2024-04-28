// Lista de palabras para el juego
let palabras = [];

// Palabra a adivinar
let palabraSecreta;

// Arreglo para almacenar las letras adivinadas
let letrasAdivinadas = [];

// Número máximo de intentos
const maxIntentos = 6;

// Número de intentos actual
let intentos = 0;

// Elementos del DOM
const palabraContainer = document.getElementById('palabra-container');
const intentosContainer = document.getElementById('intentos-container');
const tecladoContainer = document.getElementById('teclado-container');
const reiniciarJuegoBtn = document.getElementById('reiniciar-juego');

// Función para cargar palabras de forma asíncrona desde la API
async function cargarDiccionario() {
    try {
        // Intenta cargar palabras desde la API
        const response = await fetch('https://api.datamuse.com/words?ml=ringing+in+the+ears'); // Esta API contiene palabras relacionadas con "ringing in the ears" en inglés
        if (!response.ok) {
            throw new Error('Error al cargar las palabras desde la API');
        }
        const data = await response.json();
        palabras = data.map(item => item.word.toUpperCase()); // Actualiza el array de palabras con los datos recibidos y convierte a mayúsculas
        console.log('Palabras cargadas desde la API: ', palabras);

    } catch (error) {
        // En caso de error al cargar desde la API, carga desde un archivo JSON de respaldo
        try {
            console.error('Error al cargar las palabras desde la API', error.message);
            const response = await fetch('/palabrasAhorcado.json');
            if (!response.ok) {
                throw new Error('Error al cargar las palabras desde el archivo JSON de respaldo');
            }
            const data = await response.json();
            palabras = data; // Actualiza el array de palabras con los datos recibidos y convierte a mayúsculas
            palabras = Array.from(palabras).map(palabra => palabra.toUpperCase());
            console.log('Palabras cargadas desde el archivo JSON de respaldo: ', palabras);
        } catch (error) {
            // En caso de error al cargar desde el archivo JSON de respaldo, utiliza un conjunto de palabras predeterminadas
            console.error('Error al cargar las palabras desde el archivo JSON de respaldo', error.message);
            palabras = ['AREVALO','LUIS','JOSE', 'MURCIELAGO', 'CASA', 'PROGRAMACION', 'COMPUTADORA', 'TELEVISION', 'CELULAR', 'COCINA', 'COCODRILO', 'ELEFANTE', 'GATO', 'PERRO', 'CONEJO', 'CABALLO', 'CANGURO', 'LEON', 'TIGRE', 'JIRAFA', 'CAMELLO', 'ELEFANTE', 'RINOCERONTE', 'HIPOPOTAMO', 'COCODRILO', 'SERPIENTE', 'TORTUGA', 'LAGARTIJA', 'IGUANA', 'COCODRILO', 'CANGURO', 'LEON', 'TIGRE', 'JIRAFA', 'CAMELLO', 'ELEFANTE', 'RINOCERONTE', 'HIPOPOTAMO', 'COCODRILO', 'SERPIENTE', 'TORTUGA', 'LAGARTIJA', 'IGUANA', 'COCODRILO', 'CANGURO', 'LEON', 'TIGRE', 'JIRAFA', 'CAMELLO', 'ELEFANTE', 'RINOCERONTE', 'HIPOPOTAMO', 'COCODRILO', 'SERPIENTE', 'TORTUGA', 'LAGARTIJA', 'IGUANA', 'COCODRILO', 'CANGURO', 'LEON', 'TIGRE', 'JIRAFA', 'CAMELLO', 'ELEFANTE', 'RINOCERONTE', 'HIPOPOTAMO', 'COCODRILO', 'SERPIENTE', 'TORTUGA', 'LAGARTIJA', 'IGUANA', 'COCODRILO', 'CANGURO', 'LEON', 'TIGRE'];
        }

    }
}

// Función para obtener una palabra aleatoria del array de palabras
function obtenerPalabraAleatoria() {
    return palabras[Math.floor(Math.random() * palabras.length)];
}

// Función para iniciar el juego
async function iniciarJuego() {
    await cargarDiccionario(); // Carga las palabras desde la API

    // Seleccionar una palabra aleatoria
    palabraSecreta = obtenerPalabraAleatoria();


    // Limpiar letras adivinadas
    letrasAdivinadas = [];

    // Mostrar la palabra oculta
    mostrarPalabraOculta();

    // Reiniciar intentos
    intentos = 0;

    // Mostrar los intentos
    mostrarIntentos();

    // Limpiar el teclado virtual
    tecladoContainer.innerHTML = '';

    // Generar el teclado virtual
    generarTeclado();
}

// Función para mostrar la palabra oculta
function mostrarPalabraOculta() {
    palabraContainer.innerHTML = '';

    // Mostrar un guion por cada letra no adivinada
    for (let letra of palabraSecreta) {
        if (letrasAdivinadas.includes(letra)) {
            palabraContainer.innerHTML += `<span>${letra}</span>`;
        } else {
            palabraContainer.innerHTML += '<span>_</span>';
        }
    }
}

// Función para mostrar los intentos restantes
function mostrarIntentos() {
    intentosContainer.textContent = `Intentos restantes: ${maxIntentos - intentos}`;
}

// Función para verificar si se ha completado la palabra
function verificarVictoria() {
    for (let letra of palabraSecreta) {
        if (!letrasAdivinadas.includes(letra)) {
            return false;
        }
    }
    return true;
}

// Función para generar el teclado virtual
function generarTeclado() {
    const letras = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';

    // Crear un botón para cada letra del teclado virtual
    for (let letra of letras) {
        const boton = document.createElement('button');
        
        boton.textContent = letra;
        boton.addEventListener('click', () => {
            // Añadir estilos al botón presionado
            boton.classList.add('tecla-activa');
            manejarIntento(letra);
        });
        tecladoContainer.appendChild(boton);
    }
}

// Función para manejar un intento del jugador
function manejarIntento(letra) {
    if (!letrasAdivinadas.includes(letra)) {
        letrasAdivinadas.push(letra);

        if (!palabraSecreta.includes(letra)) {
            intentos++;
        }

        mostrarPalabraOculta();
        mostrarIntentos();

        if (intentos >= maxIntentos) {
            terminarJuego(false);
        } else if (verificarVictoria()) {
            terminarJuego(true);
        }
    }
}

// Función para terminar el juego
function terminarJuego(victoria) {
    if (victoria) {
        alert('¡Felicidades! Has adivinado la palabra.');
    } else {
        alert('Lo siento, has agotado todos los intentos. La palabra era: ' + palabraSecreta);
    }
    
    reiniciarJuego();
}

// Función para reiniciar el juego
function reiniciarJuego() {
    iniciarJuego();
}

// Asignar evento de clic al botón de reiniciar juego
reiniciarJuegoBtn.addEventListener('click', reiniciarJuego);

// Iniciar el juego al cargar la página
window.onload = iniciarJuego;
