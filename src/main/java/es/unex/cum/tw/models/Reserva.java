package es.unex.cum.tw.models;

import java.sql.Timestamp;

public class Reserva {
    private int idUsuario;
    private int idLibro;
    private Timestamp fechaReserva;

    // Constructor vacío
    public Reserva() {
        idUsuario = 0;
        idLibro = 0;
        fechaReserva = null;
    }

    // Constructor con todos los atributos
    public Reserva(int idUsuario, int idLibro, Timestamp fechaReserva) {
        this.idUsuario = idUsuario;
        this.idLibro = idLibro;
        this.fechaReserva = fechaReserva;
    }

    // Métodos getter y setter para cada atributo

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

    public Timestamp getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(Timestamp fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    // Método toString para imprimir los atributos de la reserva

    @Override
    public String toString() {
        return "Reserva{" +
                "idUsuario=" + idUsuario +
                ", idLibro=" + idLibro +
                ", fechaReserva=" + fechaReserva +
                '}';
    }
}
