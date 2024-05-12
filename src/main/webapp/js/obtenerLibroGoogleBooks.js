async function fetchBooks() {
    const titulo = document.getElementById("titulo");
    const searchTerm = titulo.value;
    console.log("Término de búsqueda:", searchTerm);

    const baseUrl = "https://www.googleapis.com/books/v1/volumes?q=";
    const url = `${baseUrl}${searchTerm}`;
    console.log("URL:", url);

    try {
        const response = await fetch(url);
        if (!response.ok) {
            throw new Error(`Error fetching books: ${response.status}`);
        }

        const data = await response.json();
        return data.items[0]; // Devolver el primer elemento de los resultados
    } catch (error) {
        console.error("Error:", error);
    }
}

async function submitForm(event) {
    event.preventDefault(); // Prevenir el envío estándar del formulario

    const form = document.getElementById("formulario");
    const isValid = form.checkValidity(); // Verificar la validez del formulario

    if (isValid) {
        const bookData = await fetchBooks(); // Obtener datos del libro

        if (bookData) {
            const inputLibro = document.getElementById("inputLibro");
            const urlImg = bookData.volumeInfo.imageLinks?.thumbnail;
            inputLibro.value = urlImg; // Establecer el valor del campo de imagen con la URL de la imagen del libro
        }

        form.submit(); // Enviar el formulario después de obtener y procesar los datos
    }

    return false; // Evitar el envío estándar del formulario
}