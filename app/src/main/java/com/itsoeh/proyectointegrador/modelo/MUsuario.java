package com.itsoeh.proyectointegrador.modelo;

import java.io.Serializable;

public class MUsuario implements Serializable {
    private int id_trabajador;
    private String nombre;
    private String numTrabajador;
    private String correo;
    private String contrasenia;

    public MUsuario() {

    }

    public MUsuario(int id_trabajador, String nombre, String numTrabajador, String correo, String contrasenia) {
        this.id_trabajador = id_trabajador;
        this.nombre = nombre;
        this.numTrabajador = numTrabajador;
        this.correo = correo;
        this.contrasenia = contrasenia;
    }

    public int getId_trabajador() {
        return id_trabajador;
    }

    public void setId_trabajador(int id_trabajador) {
        this.id_trabajador = id_trabajador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumTrabajador() {
        return numTrabajador;
    }

    public void setNumTrabajador(String numTrabajador) {
        this.numTrabajador = numTrabajador;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    @Override
    public String toString() {
        return "MUsuario{" +
                "id_trabajador=" + id_trabajador +
                ", nombre='" + nombre + '\'' +
                ", numTrabajador='" + numTrabajador + '\'' +
                ", correo='" + correo + '\'' +
                ", contrasenia='" + contrasenia + '\'' +
                '}';
    }
}