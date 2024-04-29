package es.unex.cum.tw.models;

public class Valoracion {
    private int idValoracion;
    private int idUsuario;
    private int idLibro;
    private int puntuacion;
    private String comentario;

    // Constructor vacío
    public Valoracion() {
        idValoracion = 0;
        idUsuario = 0;
        idLibro = 0;
        puntuacion = 0;
        comentario = "";
    }

    // Constructor con todos los atributos
    public Valoracion(int idValoracion, int idUsuario, int idLibro, int puntuacion, String comentario) {
        this.idValoracion = idValoracion;
        this.idUsuario = idUsuario;
        this.idLibro = idLibro;
        this.puntuacion = puntuacion;
        this.comentario = comentario;
    }

    // Métodos getter y setter para cada atributo

    public int getIdValoracion() {
        return idValoracion;
    }

    public void setIdValoracion(int idValoracion) {
        this.idValoracion = idValoracion;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    // Método toString para imprimir los atributos de la valoración

    @Override
    public String toString() {
        return "Valoracion{" +
                "idValoracion=" + idValoracion +
                ", idUsuario=" + idUsuario +
                ", idLibro=" + idLibro +
                ", puntuacion=" + puntuacion +
                ", comentario='" + comentario + '\'' +
                '}';
    }
}

