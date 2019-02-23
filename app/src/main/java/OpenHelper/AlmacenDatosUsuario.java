package OpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AlmacenDatosUsuario extends SQLiteOpenHelper {
    public AlmacenDatosUsuario(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query ="CREATE TABLE datos_usuarios (" +
                "_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombres TEXT, apellidos TEXT, genero TEXT, " +
                "telefono TEXT, correo TEXT, clave TEXT, nperfiles INTEGER)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

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
    public void insertarRegistro(String nombres_us, String apellidos_us,
                                 String genero_us, String telefono_us,
                                 String correo_us, String clave_us) {

        ContentValues valores = new ContentValues();
        int nPerfiles_us = 0;
        valores.put("nombres", nombres_us);
        valores.put("apellidos", apellidos_us);
        valores.put("genero", genero_us);
        valores.put("telefono", telefono_us);
        valores.put("correo", correo_us);
        valores.put("clave", clave_us);
        valores.put("nperfiles", nPerfiles_us);
        this.getWritableDatabase().insert("datos_usuarios", null, valores);
    }

    //Método que permite validar si el usuario existe
    public Cursor ConsultarCorreoClave (String correo_usu, String clave_usu) throws SQLException {
        Cursor mcursor = null;
        mcursor = this.getReadableDatabase().query("datos_usuarios",
                new String[]{"_ID", "nombres", "apellidos", "genero", "telefono", "correo", "clave", "nperfiles"},
                "correo like '"+correo_usu+"' and clave like '"+clave_usu+"' ", null, null, null, null);
        return mcursor;
    }
}
