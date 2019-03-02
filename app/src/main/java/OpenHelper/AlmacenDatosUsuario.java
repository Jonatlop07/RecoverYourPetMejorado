package OpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.jonatlop.recoveryourpet.utilidades.Utilidades;

public class AlmacenDatosUsuario extends SQLiteOpenHelper {
    public AlmacenDatosUsuario( Context context, String name, SQLiteDatabase.CursorFactory factory, int version ) {
        super( context, name, factory, version );
    }

    @Override
    public void onCreate( SQLiteDatabase db ) {
        db.execSQL( Utilidades.CREAR_TABLA_USUARIO );
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

    //Método que permite validar si el usuario existe
    public Cursor ConsultarCorreoClave ( String correo_usu, String clave_usu ) throws SQLException {

        Cursor mcursor = null;
        final String VALIDACION_DATOS_USUARIO = ""+Utilidades.CAMPO_CORREO_USUARIO+" like '"+correo_usu+"' and " +
                                                ""+Utilidades.CAMPO_CLAVE_USUARIO+" like '"+clave_usu+"' ";

        mcursor = this.getReadableDatabase().query( Utilidades.TABLA_USUARIO, Utilidades.CAMPOS_USUARIO,
                                                    VALIDACION_DATOS_USUARIO, null, null, null, null);

        return mcursor;
    }
}
