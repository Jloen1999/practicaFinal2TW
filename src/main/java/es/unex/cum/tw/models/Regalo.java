package es.unex.cum.tw.models;

import java.util.Objects;

public class Regalo {
    private int idRegalo;
    private String nombre;
    private int idCarta;

    public Regalo() {
        idRegalo = 0;
        nombre = "";
        idCarta = 0;
    }

    public Regalo(int idRegalo, String nombre, int idCarta) {
        this.idRegalo = idRegalo;
        this.nombre = nombre;
        this.idCarta = idCarta;
    }

    public Regalo(String nombre, int idCarta) {
        this.nombre = nombre;
        this.idCarta = idCarta;
    }



    public int getIdRegalo() {
        return idRegalo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getIdCarta() {
        return idCarta;
    }

    public void setIdRegalo(int idRegalo) {
        this.idRegalo = idRegalo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setIdCarta(int idCarta) {
        this.idCarta = idCarta;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Regalo{");
        sb.append("idRegalo=").append(idRegalo);
        sb.append(", nombre='").append(nombre).append('\'');
        sb.append(", idCarta=").append(idCarta);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Regalo regalo = (Regalo) o;
        return Objects.equals(idRegalo, regalo.idRegalo) && Objects.equals(nombre, regalo.nombre) && Objects.equals(idCarta, regalo.idCarta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRegalo, nombre, idCarta);
    }
}
