package com.example.jonatlop.recoveryourpet.Entidades;

import java.io.Serializable;

public class CuentaUsuario implements Serializable {
    private String nombres;
    private String apellidos;
    private String genero;
    private String telefono;
    private String correo;
    private String clave;
    private String nperfiles;

    public CuentaUsuario( String nombres, String apellidos, String genero, String telefono,
                          String correo, String clave, String nperfiles ) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.genero = genero;
        this.telefono = telefono;
        this.correo = correo;
        this.clave = clave;
        this.nperfiles = nperfiles;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getGenero() {
        return genero;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public String getClave() {
        return clave;
    }

    public String getNperfiles() {
        return nperfiles;
    }
}
