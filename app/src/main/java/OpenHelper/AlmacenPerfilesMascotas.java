package OpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import com.example.jonatlop.recoveryourpet.R;
import com.example.jonatlop.recoveryourpet.utilidades.Utilidades;

public class AlmacenPerfilesMascotas extends SQLiteOpenHelper {

    public AlmacenPerfilesMascotas( Context context, String name, SQLiteDatabase.CursorFactory factory, int version ) {
        super( context, name, factory, version );
    }

    @Override
    public void onCreate( SQLiteDatabase db ) {
        db.execSQL( Utilidades.CREAR_TABLA_MASCOTA );
    }

    @Override
    public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion ) {

    }

    public void abrirBD() { this.getWritableDatabase(); }

    public void cerrarBD() { this.close(); }

    public void insertarPerfil( String nomb_m, String esp_m, String gen_m,
                               String raza_mascota, String tamanio_m, String ed_m,
                               String c_esp, byte[] foto_m ) {

        ContentValues datos = new ContentValues();
        datos.put( Utilidades.CAMPO_NOMBRE_MASCOTA, nomb_m );
        datos.put( Utilidades.CAMPO_ESPECIE_MASCOTA, esp_m );
        datos.put( Utilidades.CAMPO_GENERO_MASCOTA, gen_m );
        datos.put( Utilidades.CAMPO_RAZA_MASCOTA, raza_mascota );
        datos.put( Utilidades.CAMPO_TAMANIO_MASCOTA, tamanio_m );
        datos.put( Utilidades.CAMPO_EDAD_MASCOTA, ed_m );
        datos.put( Utilidades.CAMPO_FOTO_MASCOTA, foto_m );
        datos.put( Utilidades.CAMPO_CARACT_ESP_MASCOTA, c_esp );
        this.getWritableDatabase().insert( Utilidades.TABLA_MASCOTA, null, datos );
    }

    public Cursor ConsultarPerfiles ( String esp_m, String gen_m, String raza_mascota,
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
}
