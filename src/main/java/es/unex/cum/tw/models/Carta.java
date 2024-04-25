package es.unex.cum.tw.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Carta {
    private int idCarta;
    private List<String> regalos;
    private int idUsuario;
    private String titulo;

    public Carta() {
        idCarta = 0;
        regalos = new ArrayList<>();
        idUsuario = 0;
        titulo = "";
    }

    public Carta(int idCarta, int idUsuario, String titulo) {
        this.idCarta = idCarta;
        this.regalos = new ArrayList<>();
        this.idUsuario = idUsuario;
        this.titulo = titulo;
    }

    public int getIdCarta() {
        return idCarta;
    }

    public List<String> getRegalos() {
        return regalos;
    }

    public boolean addRegalo(String regalo) {
        if(regalos.contains(regalo)) {
            return false;
        }

        return regalos.add(regalo);
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setIdCarta(int idCarta) {
        this.idCarta = idCarta;
    }


    public int getIdUsuario() {
        return idUsuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carta carta = (Carta) o;
        return Objects.equals(idCarta, carta.idCarta) && Objects.equals(regalos, carta.regalos) && Objects.equals(idUsuario, carta.idUsuario);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Carta{");
        sb.append("idCarta=").append(idCarta);
        sb.append(", regalo='").append(regalos).append('\'');
        sb.append(", idUsuario=").append(idUsuario);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCarta, regalos, idUsuario);
    }
}
