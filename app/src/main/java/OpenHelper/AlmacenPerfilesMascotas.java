package OpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import com.example.jonatlop.recoveryourpet.R;

public class AlmacenPerfilesMascotas extends SQLiteOpenHelper {

    public AlmacenPerfilesMascotas(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query ="CREATE TABLE perfiles_mascotas (_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT, especie TEXT, generoM TEXT, raza TEXT, tamanio TEXT, edad TEXT, " +
                "foto BLOB, caractEspeciales TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void abrirBD() { this.getWritableDatabase(); }

    public void cerrarBD() { this.close(); }

    public void insertarPerfil(String nomb_m, String esp_m, String gen_m,
                               String raza_mascota, String tamanio_m, String ed_m,
                               String c_esp) {

        ContentValues datos = new ContentValues();
        datos.put("nombre", nomb_m);
        datos.put("especie", esp_m);
        datos.put("generoM", gen_m);
        datos.put("raza", raza_mascota);
        datos.put("tamanio", tamanio_m);
        datos.put("edad", ed_m);
        datos.put("caractEspeciales", c_esp);
        this.getWritableDatabase().insert("perfiles_mascotas", null, datos);
    }

    public Cursor ConsultarPerfiles (String esp_m, String gen_m, String raza_mascota,
                                     String tamanio_m, String ed_m) throws SQLException {

        Cursor mcursor = null;

        String[] campos = new String[] {"nombre", "raza", "tamanio", "edad"};

        if (tamanio_m.equals("No responde") && ed_m.equals("No responde") && raza_mascota.equals("No aplica")) {

            mcursor = this.getReadableDatabase().query("perfiles_mascotas", campos,
                    "especie like '"+esp_m+"' and generoM like '"+gen_m+"' ", null, null, null, null);

        } else if (tamanio_m.equals("No responde") && ed_m.equals("No responde") && !raza_mascota.equals("No aplica")) {

            mcursor = this.getReadableDatabase().query("perfiles_mascotas", campos,
                    "especie like '"+esp_m+"' and generoM like '"+gen_m+"' and raza like '"+raza_mascota+"' ",
                    null, null, null, null);

        } else if (tamanio_m.equals("No responde") && !ed_m.equals("No responde") && raza_mascota.equals("No aplica")) {

            mcursor = this.getReadableDatabase().query("perfiles_mascotas", campos,
                    "especie like '"+esp_m+"' and generoM like '"+gen_m+"' and edad like '"+ed_m+"' ",
                    null, null, null, null);

        } else if (tamanio_m.equals("No responde") && !ed_m.equals("No responde") && !raza_mascota.equals("No aplica")) {

            mcursor = this.getReadableDatabase().query("perfiles_mascotas", campos,
                    "especie like '"+esp_m+"' and generoM like '"+gen_m+"' and raza like '"+raza_mascota+"' " +
                    "and edad like '"+ed_m+"' ", null, null, null, null);

        } else if (!tamanio_m.equals("No responde") && ed_m.equals("No responde") && raza_mascota.equals("No aplica")) {

            mcursor = this.getReadableDatabase().query("perfiles_mascotas", campos,
                    "especie like '"+esp_m+"' and generoM like '"+gen_m+"' and tamanio like '"+tamanio_m+"' ",
                    null, null, null, null);

        } else if (!tamanio_m.equals("No responde") && ed_m.equals("No responde") && !raza_mascota.equals("No aplica")) {

            mcursor = this.getReadableDatabase().query("perfiles_mascotas", campos,
                    "especie like '"+esp_m+"' and generoM like '"+gen_m+"' and raza like '"+raza_mascota+"' " +
                    "and tamanio like '"+tamanio_m+"' ", null, null, null, null);

        } else if (!tamanio_m.equals("No responde") && !ed_m.equals("No responde") && raza_mascota.equals("No aplica")) {

            mcursor = this.getReadableDatabase().query("perfiles_mascotas", campos,
                    "especie like '"+esp_m+"' and generoM like '"+gen_m+"' and tamanio like '"+tamanio_m+"' " +
                    "and edad like '"+ed_m+"' ", null, null, null, null);

        } else {

            mcursor = this.getReadableDatabase().query("perfiles_mascotas", campos,
                    "especie like '"+esp_m+"' and generoM like '"+gen_m+"' and raza like '"+raza_mascota+"' " +
                    "and edad like '"+ed_m+"' and tamanio like '"+tamanio_m+"' ", null, null, null, null);

        }


        return mcursor;
    }
}
