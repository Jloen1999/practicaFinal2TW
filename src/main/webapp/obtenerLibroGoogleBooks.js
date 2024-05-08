async function fetchBooks() {
    const inputLibro = document.getElementById("inputLibro");
    const searchTerm = inputLibro.value;

    const baseUrl = "https://www.googleapis.com/books/v1/volumes?q=";
    const url = `${baseUrl}${searchTerm}`;

    try {
        const response = await fetch(url);
        if (!response.ok) {
            throw new Error(`Error fetching books: ${response.status}`);
        }

        const data = await response.json();

        console.log(inputLibro.value);
        handleBooks(data.items[0]); // Call the function to handle the data
    } catch (error) {
        console.error("Error:", error);
    }
}

function handleBooks(item) {
    const inputLibro = document.getElementById("inputLibro");

    const urlImg = item.volumeInfo.imageLinks?.thumbnail;

    // Crear una lista para almacenar inputLibro.value en cada elemento
    const lista = document.getElementById("lista");
    const li = document.createElement("li");
    li.appendChild(document.createTextNode(urlImg));
    lista.appendChild(li);
    inputLibro.value = "";

}

async function submitForm(event) {
    event.preventDefault();
    await fetchBooks();

    return false;
}