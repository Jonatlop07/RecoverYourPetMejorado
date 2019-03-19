package OpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.CursorAdapter;

import com.example.jonatlop.recoveryourpet.utilidades.Utilidades;

public class BaseDatosSQLite extends SQLiteOpenHelper {
    public BaseDatosSQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version ) {
        super( context, name, factory, version );
    }

    @Override
    public void onCreate( SQLiteDatabase db ) {
        db.execSQL( Utilidades.CREAR_TABLA_USUARIO );
        db.execSQL( Utilidades.CREAR_TABLA_MASCOTA );
    }

    @Override
    public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion ) {

    }

    //Método que permite abrir la BD
    public void abrir() {
        this.getWritableDatabase();
    }
    //Método que permite cerrar la BD
    public void cerrar() {
        this.close();
    }

    //Método que permite insertar registros en la tabla datos_usuarios
    public void insertarRegistro( String nombres_us, String apellidos_us,
                                 String genero_us, String telefono_us,
                                 String correo_us, String clave_us ) {

        ContentValues valores = new ContentValues();
        int nPerfiles_us = 0;
        valores.put(Utilidades.CAMPO_NOMBRES_USUARIO, nombres_us);
        valores.put(Utilidades.CAMPO_APELLIDOS_USUARIO, apellidos_us);
        valores.put(Utilidades.CAMPO_GENERO_USUARIO, genero_us);
        valores.put(Utilidades.CAMPO_TELEFONO_USUARIO, telefono_us);
        valores.put(Utilidades.CAMPO_CORREO_USUARIO, correo_us);
        valores.put(Utilidades.CAMPO_CLAVE_USUARIO, clave_us);
        valores.put(Utilidades.CAMPO_NPERFILES_USUARIO, nPerfiles_us);
        this.getWritableDatabase().insert(Utilidades.TABLA_USUARIO, null, valores);
    }

    public void insertarPerfil( String nomb_m, String esp_m, String gen_m,
                                String raza_mascota, String tamanio_m, String ed_m,
                                String c_esp, byte[] foto_m, String duenio_m ) {

        ContentValues datos = new ContentValues();
        datos.put( Utilidades.CAMPO_NOMBRE_MASCOTA, nomb_m );
        datos.put( Utilidades.CAMPO_ESPECIE_MASCOTA, esp_m );
        datos.put( Utilidades.CAMPO_GENERO_MASCOTA, gen_m );
        datos.put( Utilidades.CAMPO_RAZA_MASCOTA, raza_mascota );
        datos.put( Utilidades.CAMPO_TAMANIO_MASCOTA, tamanio_m );
        datos.put( Utilidades.CAMPO_EDAD_MASCOTA, ed_m );
        datos.put( Utilidades.CAMPO_FOTO_MASCOTA, foto_m );
        datos.put( Utilidades.CAMPO_CARACT_ESP_MASCOTA, c_esp );
        datos.put( Utilidades.CAMPO_DUENIO_MASCOTA, duenio_m );

        this.getWritableDatabase().insert( Utilidades.TABLA_MASCOTA, null, datos );
    }

    //Método que permite validar si el usuario existe
    public Cursor ConsultarCorreoClave ( String correo_usu, String clave_usu ) throws SQLException {

        Cursor mcursor = null;
        final String VALIDACION_DATOS_USUARIO = ""+Utilidades.CAMPO_CORREO_USUARIO+" like '"+correo_usu+"' and " +
                                                ""+Utilidades.CAMPO_CLAVE_USUARIO+" like '"+clave_usu+"' ";

        mcursor = this.getReadableDatabase().query( Utilidades.TABLA_USUARIO, Utilidades.CAMPOS_USUARIO,
                                                    VALIDACION_DATOS_USUARIO, null, null, null, null);

        return mcursor;
    }

    public Cursor ConsultarInformacionCuenta ( String correo_usu ) throws SQLException {

        Cursor mcursor = null;
        final String CAMPOS_REQUERIDOS[] = { Utilidades.CAMPO_NOMBRES_USUARIO, Utilidades.CAMPO_APELLIDOS_USUARIO,
                                           Utilidades.CAMPO_GENERO_USUARIO, Utilidades.CAMPO_TELEFONO_USUARIO,
                                           Utilidades.CAMPO_CORREO_USUARIO };

        final String CONSULTA_INFO = ""+Utilidades.CAMPO_CORREO_USUARIO+" like '"+correo_usu+"'";

        mcursor = this.getReadableDatabase().query( Utilidades.TABLA_USUARIO, CAMPOS_REQUERIDOS,
                CONSULTA_INFO, null, null, null, null );
        return mcursor;
    }

    public Cursor ConsultarPerfilesUsuario ( String correo_usu ) throws SQLException {

        Cursor mcursor = null;
        final String VALIDACION_DATOS_USUARIO = "FROM "+Utilidades.TABLA_USUARIO+" WHERE " +
                                                ""+Utilidades.CAMPO_CORREO_USUARIO+"='"+correo_usu+"' ";

        mcursor = this.getReadableDatabase().query( Utilidades.TABLA_USUARIO, Utilidades.CAMPOS_USUARIO,
                VALIDACION_DATOS_USUARIO, null, null, null, null);

        return mcursor;
    }

    public Cursor ConsultarPerfilesMascotas ( String esp_m, String gen_m, String raza_mascota,
                                      String tamanio_m, String ed_m ) throws SQLException {

        Cursor mcursor = null;

        final String BUSQUEDA_BASICA = ""+Utilidades.CAMPO_ESPECIE_MASCOTA+" like '"+esp_m+"' and " +
                ""+Utilidades.CAMPO_GENERO_MASCOTA+" like '"+gen_m+"' ";

        final String BUSQUEDA_RAZA = ""+Utilidades.CAMPO_ESPECIE_MASCOTA+" like '"+esp_m+"' and " +
                ""+Utilidades.CAMPO_GENERO_MASCOTA+" like '"+gen_m+"' and " +
                ""+Utilidades.CAMPO_RAZA_MASCOTA+" like '"+raza_mascota+"' ";

        final String BUSQUEDA_EDAD = ""+Utilidades.CAMPO_ESPECIE_MASCOTA+" like '"+esp_m+"' and " +
                ""+Utilidades.CAMPO_GENERO_MASCOTA+" like '"+gen_m+"' and " +
                ""+Utilidades.CAMPO_EDAD_MASCOTA+" like '"+ed_m+"' ";

        final String BUSQUEDA_EDAD_RAZA = ""+Utilidades.CAMPO_ESPECIE_MASCOTA+" like '"+esp_m+"' and " +
                ""+Utilidades.CAMPO_GENERO_MASCOTA+" like '"+gen_m+"' and " +
                ""+Utilidades.CAMPO_RAZA_MASCOTA+" like '"+raza_mascota+"' and " +
                ""+Utilidades.CAMPO_EDAD_MASCOTA+" like '"+ed_m+"' ";

        final String BUSQUEDA_TAMANIO = ""+Utilidades.CAMPO_ESPECIE_MASCOTA+" like '"+esp_m+"' and " +
                ""+Utilidades.CAMPO_GENERO_MASCOTA+" like '"+gen_m+"' and " +
                ""+Utilidades.CAMPO_TAMANIO_MASCOTA+" like '"+tamanio_m+"' ";

        final String BUSQUEDA_TAMANIO_RAZA = ""+Utilidades.CAMPO_ESPECIE_MASCOTA+" like '"+esp_m+"' and " +
                ""+Utilidades.CAMPO_GENERO_MASCOTA+" like '"+gen_m+"' and " +
                ""+Utilidades.CAMPO_RAZA_MASCOTA+" like '"+raza_mascota+"' and " +
                ""+Utilidades.CAMPO_TAMANIO_MASCOTA+" like '"+tamanio_m+"' ";

        final String BUSQUEDA_TAMANIO_EDAD = ""+Utilidades.CAMPO_ESPECIE_MASCOTA+" like '"+esp_m+"' and " +
                ""+Utilidades.CAMPO_GENERO_MASCOTA+" like '"+gen_m+"' and " +
                ""+Utilidades.CAMPO_TAMANIO_MASCOTA+" like '"+tamanio_m+"' and " +
                ""+Utilidades.CAMPO_EDAD_MASCOTA+" like '"+ed_m+"' ";

        final String BUSQUEDA_COMPLETA = ""+Utilidades.CAMPO_ESPECIE_MASCOTA+" like '"+esp_m+"' and " +
                ""+Utilidades.CAMPO_GENERO_MASCOTA+" like '"+gen_m+"' and " +
                ""+Utilidades.CAMPO_RAZA_MASCOTA+" like '"+raza_mascota+"' and " +
                ""+Utilidades.CAMPO_EDAD_MASCOTA+" like '"+ed_m+"' and " +
                ""+Utilidades.CAMPO_TAMANIO_MASCOTA+" like '"+tamanio_m+"' ";

        if ( tamanio_m.equals( "No responde" ) && ed_m.equals( "No responde" ) && raza_mascota.equals( "No aplica" ) ) {

            mcursor = this.getReadableDatabase().query( Utilidades.TABLA_MASCOTA, Utilidades.CAMPOS_MASCOTA, BUSQUEDA_BASICA,
                    null, null, null, null );

        } else if ( tamanio_m.equals( "No responde" ) && ed_m.equals( "No responde" ) && !raza_mascota.equals( "No aplica" ) ) {

            mcursor = this.getReadableDatabase().query( Utilidades.TABLA_MASCOTA, Utilidades.CAMPOS_MASCOTA, BUSQUEDA_RAZA,
                    null, null, null, null );

        } else if ( tamanio_m.equals( "No responde" ) && !ed_m.equals( "No responde" ) && raza_mascota.equals( "No aplica" ) ) {

            mcursor = this.getReadableDatabase().query( Utilidades.TABLA_MASCOTA, Utilidades.CAMPOS_MASCOTA, BUSQUEDA_EDAD,
                    null, null, null, null );

        } else if ( tamanio_m.equals( "No responde" ) && !ed_m.equals( "No responde" ) && !raza_mascota.equals( "No aplica" ) ) {

            mcursor = this.getReadableDatabase().query( Utilidades.TABLA_MASCOTA, Utilidades.CAMPOS_MASCOTA, BUSQUEDA_EDAD_RAZA,
                    null, null, null, null );

        } else if ( !tamanio_m.equals( "No responde" ) && ed_m.equals( "No responde" ) && raza_mascota.equals( "No aplica" ) ) {

            mcursor = this.getReadableDatabase().query( Utilidades.TABLA_MASCOTA, Utilidades.CAMPOS_MASCOTA, BUSQUEDA_TAMANIO,
                    null, null, null, null );

        } else if ( !tamanio_m.equals( "No responde" ) && ed_m.equals( "No responde" ) && !raza_mascota.equals( "No aplica" ) ) {

            mcursor = this.getReadableDatabase().query( Utilidades.TABLA_MASCOTA, Utilidades.CAMPOS_MASCOTA, BUSQUEDA_TAMANIO_RAZA,
                    null, null, null, null );

        } else if ( !tamanio_m.equals( "No responde" ) && !ed_m.equals( "No responde" ) && raza_mascota.equals( "No aplica" ) ) {

            mcursor = this.getReadableDatabase().query( Utilidades.TABLA_MASCOTA, Utilidades.CAMPOS_MASCOTA, BUSQUEDA_TAMANIO_EDAD,
                    null, null, null, null );
        } else {
            mcursor = this.getReadableDatabase().query( Utilidades.TABLA_MASCOTA, Utilidades.CAMPOS_MASCOTA,
                    BUSQUEDA_COMPLETA, null, null, null, null );
        }

        return mcursor;
    }

    public Cursor ConsultarMisPerfilesCreados (String correo_usuario ) throws SQLException {
        Cursor mcursor = null;

        final String BUSQUEDA_MIS_PERFILES = ""+Utilidades.CAMPO_DUENIO_MASCOTA+" like '"+correo_usuario+"' ";

        mcursor = this.getReadableDatabase().query( Utilidades.TABLA_MASCOTA, Utilidades.CAMPOS_MASCOTA,
                BUSQUEDA_MIS_PERFILES, null, null, null, null );

        return mcursor;
    }

}
