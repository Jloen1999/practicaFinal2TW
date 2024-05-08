package es.unex.cum.tw.models;

public class LibroBuilder {
    private int idLibro;
    private String isbn;
    private String titulo;
    private String autor;
    private String tematica;
    private String descripcion;
    private String urlImg;
    private boolean novedad;
    private float puntuacion;

    public LibroBuilder setDescripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public LibroBuilder setPuntuacion(float puntuacion) {
        this.puntuacion = puntuacion;
        return this;
    }

    public LibroBuilder setIdLibro(int idLibro) {
        this.idLibro = idLibro;
        return this;
    }

    public LibroBuilder setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }


    public LibroBuilder setTitulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    public LibroBuilder setAutor(String autor) {
        this.autor = autor;
        return this;
    }

    public LibroBuilder setTematica(String tematica) {
        this.tematica = tematica;
        return this;
    }

    public LibroBuilder setUrlImg(String urlImg) {
        this.urlImg = urlImg;
        return this;
    }

    public LibroBuilder setNovedad(boolean novedad) {
        this.novedad = novedad;
        return this;
    }

    public Libro build() {
        return new Libro(idLibro, isbn, titulo, autor, tematica, urlImg, novedad, descripcion, puntuacion);
    }
}
