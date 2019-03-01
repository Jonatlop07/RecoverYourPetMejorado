package com.example.jonatlop.recoveryourpet;

import java.io.Serializable;

public class PerfilMascota implements Serializable {
    private String nombre;
    private String especie;
    private String genero;
    private String raza;
    private String tamanio;
    private String edad;
    private String caract_esp;
    private byte[] foto_masc;

    public PerfilMascota( String nombre, String especie, String genero, String raza,
                         String tamanio, String edad, byte[] foto_masc, String caract_esp ) {
        this.nombre = nombre;
        this.especie = especie;
        this.genero = genero;
        this.raza = raza;
        this.tamanio = tamanio;
        this.edad = edad;
        this.foto_masc = foto_masc;
        this.caract_esp = caract_esp;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public String getGenero() {
        return genero;
    }

    public String getRaza() {
        return raza;
    }

    public String getTamanio() {
        return tamanio;
    }

    public String getEdad() {
        return edad;
    }

    public String getCaract_esp() {
        return caract_esp;
    }

    public byte[] getFoto() {
        return foto_masc;
    }
}
