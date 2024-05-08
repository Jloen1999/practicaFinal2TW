package es.unex.cum.tw.models;

public class Libro {
    private int idLibro;
    private String isbn;
    private String titulo;
    private String autor;
    private String tematica;
    private String descripcion;
    private String urlImg;
    private boolean novedad;
    private float puntuacion;

    // Constructor vacío
    public Libro() {
        idLibro = 0;
        isbn = "";
        titulo = "";
        autor = "";
        tematica = "";
        descripcion = "";
        urlImg = "http://books.google.com/books/content?id=3FPv6iZL980C&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api";
        novedad = false;
        puntuacion = 0f;
    }

    // Constructor con todos los atributos
    public Libro(String titulo, String autor, String tematica, String urlImg, boolean novedad, String descripcion) {
        this.isbn = generateRandomIsbn();
        this.titulo = titulo;
        this.autor = autor;
        this.tematica = tematica;
        this.descripcion = descripcion;
        this.novedad = novedad;
        this.urlImg = urlImg;
    }

    public Libro(int idLibro, String isbn, String titulo, String autor, String tematica, String urlImg, boolean novedad, String descripcion, float puntuacion) {
        this.idLibro = idLibro;
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.tematica = tematica;
        this.descripcion = descripcion;
        this.novedad = novedad;
        this.urlImg = urlImg;
        this.puntuacion = puntuacion;
    }

    public String generateRandomIsbn() {
        StringBuilder isbn = new StringBuilder("978");
        for (int i = 0; i < 10; i++) {
            isbn.append((int) (Math.random() * 10));
        }
        return isbn.toString();
    }


    public void setPuntuacion(float puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public float getPuntuacion() {
        return puntuacion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getUrlImg() {
        return urlImg;
    }


    public int getIdLibro() {
        return idLibro;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getTematica() {
        return tematica;
    }

    public boolean isNovedad() {
        return novedad;
    }

    // Método toString para imprimir los atributos del libro
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Libro{");
        sb.append("idLibro=").append(idLibro);
        sb.append(", isbn='").append(isbn).append('\'');
        sb.append(", titulo='").append(titulo).append('\'');
        sb.append(", autor='").append(autor).append('\'');
        sb.append(", tematica='").append(tematica).append('\'');
        sb.append(", descripcion='").append(descripcion).append('\'');
        sb.append(", urlImg='").append(urlImg).append('\'');
        sb.append(", novedad=").append(novedad);
        sb.append('}');
        return sb.toString();
    }
}
