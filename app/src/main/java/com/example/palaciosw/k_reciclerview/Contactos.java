package com.example.palaciosw.k_reciclerview;

/**
 * Created by palaciosw on 02/06/2017.
 */

public class Contactos {

    private String nombre,apellido,correo;
    private  Long telefono;

    public Contactos() {
    }

    public Contactos(String nombre, String apellido, String correo, Long telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Long getTelefono() {
        return telefono;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }
}
