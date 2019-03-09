package com.example.jonatlop.recoveryourpet.utilidades;

public class Utilidades {

    public static final String TABLA_USUARIO = "datos_usuarios";
    public static final String CAMPO_ID_USUARIO = "_ID";
    public static final String CAMPO_NOMBRES_USUARIO = "nombres";
    public static final String CAMPO_APELLIDOS_USUARIO = "apellidos";
    public static final String CAMPO_GENERO_USUARIO = "genero";
    public static final String CAMPO_TELEFONO_USUARIO = "telefono";
    public static final String CAMPO_CORREO_USUARIO = "correo";
    public static final String CAMPO_CLAVE_USUARIO = "clave";
    public static final String CAMPO_NPERFILES_USUARIO = "nperfiles";

    public static final String[] CAMPOS_USUARIO = { Utilidades.CAMPO_ID_USUARIO, Utilidades.CAMPO_NOMBRES_USUARIO,
                                                    Utilidades.CAMPO_APELLIDOS_USUARIO, Utilidades.CAMPO_GENERO_USUARIO,
                                                    Utilidades.CAMPO_TELEFONO_USUARIO, Utilidades.CAMPO_CORREO_USUARIO,
                                                    Utilidades.CAMPO_CLAVE_USUARIO, Utilidades.CAMPO_NPERFILES_USUARIO };

    public static final String TABLA_MASCOTA = "perfiles_mascotas";
    public static final String CAMPO_ID_MASCOTA = "_ID";
    public static final String CAMPO_NOMBRE_MASCOTA = "nombre";
    public static final String CAMPO_ESPECIE_MASCOTA = "especie";
    public static final String CAMPO_GENERO_MASCOTA = "genero";
    public static final String CAMPO_RAZA_MASCOTA = "raza";
    public static final String CAMPO_TAMANIO_MASCOTA = "tamanio";
    public static final String CAMPO_EDAD_MASCOTA = "edad";
    public static final String CAMPO_FOTO_MASCOTA = "foto";
    public static final String CAMPO_CARACT_ESP_MASCOTA = "caractEspeciales";
    public static final String CAMPO_DUENIO_MASCOTA = "dueño";

    public static final String[] CAMPOS_MASCOTA = { CAMPO_NOMBRE_MASCOTA, CAMPO_ESPECIE_MASCOTA, CAMPO_GENERO_MASCOTA,
                                                    CAMPO_RAZA_MASCOTA, CAMPO_TAMANIO_MASCOTA, CAMPO_EDAD_MASCOTA,
                                                    CAMPO_FOTO_MASCOTA, CAMPO_CARACT_ESP_MASCOTA, CAMPO_DUENIO_MASCOTA };

    public static final String CREAR_TABLA_USUARIO = "CREATE TABLE "+TABLA_USUARIO+" ( "+CAMPO_ID_USUARIO+" INTEGER " +
                                                     "PRIMARY KEY AUTOINCREMENT, "+CAMPO_NOMBRES_USUARIO+" TEXT, " +
                                                     ""+CAMPO_APELLIDOS_USUARIO+" TEXT, "+CAMPO_GENERO_USUARIO+" " +
                                                     "TEXT, "+CAMPO_TELEFONO_USUARIO+" TEXT, "+CAMPO_CORREO_USUARIO+" " +
                                                     "TEXT, "+CAMPO_CLAVE_USUARIO+" TEXT, "+CAMPO_NPERFILES_USUARIO+" INTEGER)";

    //, FOREIGN KEY("+CAMPO_CORREO_USUARIO+") REFERENCES " + "" +
    //                                                     ""+TABLA_MASCOTA+"("+CAMPO_DUENIO_MASCOTA+")

    public static final String CREAR_TABLA_MASCOTA = "CREATE TABLE "+TABLA_MASCOTA+" ("+CAMPO_ID_MASCOTA+" INTEGER " +
                                                     "PRIMARY KEY AUTOINCREMENT, "+CAMPO_NOMBRE_MASCOTA+" TEXT, "+
                                                     ""+CAMPO_ESPECIE_MASCOTA+" TEXT, "+CAMPO_GENERO_MASCOTA+" " +
                                                     "TEXT,"+CAMPO_RAZA_MASCOTA+" TEXT, "+CAMPO_TAMANIO_MASCOTA+" " +
                                                     "TEXT, "+CAMPO_EDAD_MASCOTA+" TEXT, "+CAMPO_FOTO_MASCOTA+" " +
                                                     "BLOB, "+CAMPO_CARACT_ESP_MASCOTA+" TEXT, "+CAMPO_DUENIO_MASCOTA+" TEXT)";

    /*, "+CAMPO_DUENIO_MASCOTA+" " +
                                                     "TEXT, FOREIGN KEY("+CAMPO_DUENIO_MASCOTA+") REFERENCES " +
                                                     ""+TABLA_USUARIO+"("+CAMPO_CORREO_USUARIO+") */




}
