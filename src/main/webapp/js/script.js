// Función para simular la carga de noticias
function simulacionNoticias(){
    // Array de ISBNs de libros
    var arrayISBN = ['9788496477957', '9781515194392', '9781507111178', '9780596552381', '9788426713087', '9788426714701', '9789874958259', '9781430204657'];
    // Cargar la API de Google Books
    google.books.load();

    // Función de inicialización
    function initialize() {
        // Obtener un ISBN aleatorio del array
        var randomISBN = arrayISBN[Math.floor(Math.random() * arrayISBN.length)];
        // Cargar el visor de Google Books con el ISBN aleatorio en el primer elemento
        var viewer1 = new google.books.DefaultViewer(document.getElementById('nuevo-libro1'));
        viewer1.load('ISBN:'+randomISBN);
        // Eliminar el elemento aleatorio del array
        arrayISBN = arrayISBN.filter(function(item) {
            return item !== randomISBN;
        });

        // Repetir el proceso para los siguientes elementos
        // Segundo elemento
        randomISBN = arrayISBN[Math.floor(Math.random() * arrayISBN.length)];
        var viewer2 = new google.books.DefaultViewer(document.getElementById('nuevo-libro2'));
        viewer2.load('ISBN:'+randomISBN);
        arrayISBN = arrayISBN.filter(function(item) {
            return item !== randomISBN;
        });

        // Tercer elemento
        randomISBN = arrayISBN[Math.floor(Math.random() * arrayISBN.length)];
        var viewer3 = new google.books.DefaultViewer(document.getElementById('nuevo-libro3'));
        viewer3.load('ISBN:'+randomISBN);
        arrayISBN = arrayISBN.filter(function(item) {
            return item !== randomISBN;
        });

        // Cuarto elemento
        randomISBN = arrayISBN[Math.floor(Math.random() * arrayISBN.length)];
        var viewer4 = new google.books.DefaultViewer(document.getElementById('nuevo-libro4'));
        viewer4.load('ISBN:'+randomISBN);
    }

    // Llamar a la función de inicialización cuando se carga la API de Google Books
    google.books.setOnLoadCallback(initialize);
}

// Llamar a la función para simular la carga de noticias
simulacionNoticias();

// Función para establecer la fecha actual en los campos de fecha
function getfechaActual() {
    let fechaInput = document.querySelectorAll('input[type="date"]');
    Array.from(fechaInput).forEach(function (fecha) {
        let fechaActual = new Date().toISOString().split('T')[0];
        fecha.value = fechaActual;
    });
}

// Llamar a la función para establecer la fecha actual en los campos de fecha
getfechaActual();

// Función para subrayar un elemento
function subrayarElemento(id) {
    let radioSeleccionado = document.getElementById(id);

    // Obtener todos los elementos de entrada de tipo radio dentro de la clase 'biblio'
    let elementos = document.querySelectorAll('.biblio input[type="radio"]');
    // Eliminar la clase 'subrayado' de todos los elementos adyacentes
    elementos.forEach(elemento => {
        elemento.nextElementSibling.classList.remove('subrayado');
    });

    // Agregar la clase 'subrayado' al elemento adyacente al radio seleccionado
    radioSeleccionado.nextElementSibling.classList.add('subrayado');
}

// Función para seleccionar una imagen
function selectImage(id) {
    let image = document.getElementById(id);

    // Obtener todas las imágenes de la clase 'imageCarousel'
    let list = document.querySelectorAll('.imageCarousel');
    // Eliminar la clase 'libro' de todas las imágenes
    list.forEach(element => {
        if (element.className.includes('libro')) {
            element.classList.remove('libro');
        }
    });

    // Agregar la clase 'libro' a la imagen seleccionada
    image.classList.add('libro');
}

// Función para mostrar el menú hamburguesa
function showMenuHamburguesa() {
    // Al hacer clic en el botón hamburguesa, llamar a la función windowLt767
    document.getElementById('boton-hamburguesa').onclick = function () {
        windowLt767();
    }
}

// Llamar a la función para mostrar el menú hamburguesa
showMenuHamburguesa();

// Función para gestionar la visualización del menú hamburguesa en ventanas con ancho menor a 768px
function windowLt767() {
    if (window.innerWidth < 768) {
        let menu_hamburguesa = document.querySelector('.menu-hamburguesa');
        let navLinks = document.querySelector('.nav-links');

        // Habilitar los estilos del menú hamburguesa y mostrar el botón hamburguesa
        if (navLinks.className.includes('estilosMenuHamburguesaDisabled')) {
            navLinks.classList.remove('estilosMenuHamburguesaDisabled');
        }

        let boton_hamburguesa = document.getElementById('boton-hamburguesa');
        if (boton_hamburguesa.style.display === 'none') {
            boton_hamburguesa.style.display = 'block';
        }

        // Cambiar la apariencia del botón hamburguesa y mostrar u ocultar los enlaces del menú
        if (boton_hamburguesa.src.includes('/images/menu-active.svg')) {
            boton_hamburguesa.src = '/images/menu-close.svg';
            navLinks.style.display = 'block';
        } else if (boton_hamburguesa.src.includes('/images/menu-close.svg')) {
            boton_hamburguesa.src = '/images/menu-active.svg';
            menu_hamburguesa.classList.remove('estilosMenuHamburguesaActive');
            navLinks.style.display = 'none';
        }

        // Añadir la clase 'estilosMenuHamburguesaActive' al menú hamburguesa
        if (!menu_hamburguesa.className.includes('estilosMenuHamburguesaActive')) {
            menu_hamburguesa.classList.add('estilosMenuHamburguesaActive');
        }
    }
}

// Evento de redimensionamiento de la ventana para gestionar la visualización del menú hamburguesa
window.addEventListener('resize', function () {
    let navLinks = document.querySelector('.nav-links');
    let boton_hamburguesa = document.getElementById('boton-hamburguesa');
    if (window.innerWidth > 767) { // Si el ancho de la ventana es mayor a 767px
        boton_hamburguesa.style.display = 'none'; // Ocultar el botón hamburguesa
        let menu_hamburguesa = document.querySelector('.menu-hamburguesa');
        if (menu_hamburguesa.className.includes('estilosMenuHamburguesaActive')) {
            menu_hamburguesa.classList.remove('estilosMenuHamburguesaActive');
        }
        navLinks.classList.add('estilosMenuHamburguesaDisabled'); // Deshabilitar los estilos del menú hamburguesa
        navLinks.style.removeProperty('background-color'); // Restablecer el color de fondo del menú hamburguesa
    } else { // Si el ancho de la ventana es menor o igual a 767px
        let navLinks = document.querySelector('.nav-links');
        let menu_hamburguesa = document.querySelector('.menu-hamburguesa');
        if (boton_hamburguesa.style.display === 'none') {
            boton_hamburguesa.style.display = 'block'; // Mostrar el botón hamburguesa
        }
        if (navLinks.className.includes('estilosMenuHamburguesaDisabled')) {
            navLinks.classList.remove('estilosMenuHamburguesaDisabled'); // Habilitar los estilos del menú hamburguesa
        }
        if (boton_hamburguesa.src.includes('/images/menu-close.svg')) {
            boton_hamburguesa.src = '/images/menu-active.svg';
            menu_hamburguesa.classList.remove('estilosMenuHamburguesaActive');
            navLinks.style.display = 'none'; // Ocultar los enlaces del menú
        }
    }
});
