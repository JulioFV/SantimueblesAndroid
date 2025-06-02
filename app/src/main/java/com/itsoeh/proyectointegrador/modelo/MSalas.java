package com.itsoeh.proyectointegrador.modelo;

import java.io.Serializable;

public class MSalas implements Serializable {
 private int id_sala;
 private String nombre;
 private Double precio;
 private int cantidad_disponible;

    public MSalas() {
    }

    public MSalas(int cantidad_disponible, Double precio, String nombre, int id_sala) {
        this.cantidad_disponible = cantidad_disponible;
        this.precio = precio;
        this.nombre = nombre;
        this.id_sala = id_sala;
    }

    public int getId_sala() {
        return id_sala;
    }

    public void setId_sala(int id_sala) {
        this.id_sala = id_sala;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public int getCantidad_disponible() {
        return cantidad_disponible;
    }

    public void setCantidad_disponible(int cantidad_disponible) {
        this.cantidad_disponible = cantidad_disponible;
    }

    @Override
    public String toString() {
        return "MSalas{" +
                "id_sala=" + id_sala +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", cantidad_disponible=" + cantidad_disponible +
                '}';
    }
}
