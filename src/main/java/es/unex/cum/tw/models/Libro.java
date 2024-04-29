package es.unex.cum.tw.models;

public class Libro {
    private int idLibro;
    private String titulo;
    private String autor;
    private String tematica;
    private boolean novedad;

    // Constructor vacío
    public Libro() {
        idLibro = 0;
        titulo = "";
        autor = "";
        tematica = "";
        novedad = false;
    }

    // Constructor con todos los atributos
    public Libro(int idLibro, String titulo, String autor, String tematica, boolean novedad) {
        this.idLibro = idLibro;
        this.titulo = titulo;
        this.autor = autor;
        this.tematica = tematica;
        this.novedad = novedad;
    }

    // Métodos getter y setter para cada atributo

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    public boolean isNovedad() {
        return novedad;
    }

    public void setNovedad(boolean novedad) {
        this.novedad = novedad;
    }

    // Método toString para imprimir los atributos del libro

    @Override
    public String toString() {
        return "Libro{" +
                "idLibro=" + idLibro +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", tematica='" + tematica + '\'' +
                ", novedad=" + novedad +
                '}';
    }
}
