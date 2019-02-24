package com.example.jonatlop.recoveryourpet;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import OpenHelper.AlmacenPerfilesMascotas;

public class VentanaListaPerfiles extends AppCompatActivity {

    private AlmacenPerfilesMascotas helper = new AlmacenPerfilesMascotas(this, "BD_Mascotas", null, 1);

    String conjunto_datos[] = new String[5];
    private ListView lv;
    ArrayList<String> lista_resultados;
    ArrayAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ventana_lista_perfiles);

        lv = (ListView) findViewById(R.id.lista);

        conjunto_datos = getIntent().getExtras().getStringArray("parametros_busqueda");
        Cursor cursor = helper.ConsultarPerfiles(conjunto_datos[0], conjunto_datos[1],
                                                    conjunto_datos[2], conjunto_datos[3], conjunto_datos[4]);

        ArrayList<String> lista_resultados = new ArrayList<>();
        if (cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    lista_resultados.add("NOMBRE: " + cursor.getString(cursor.getColumnIndex("nombre")) +
                            "\nRAZA: " + cursor.getString(cursor.getColumnIndex("raza")) +
                            "\nTAMAÑO: " + cursor.getString(cursor.getColumnIndex("tamanio")) +
                            "\nEDAD: " + cursor.getString(cursor.getColumnIndex("edad")));

                } while(cursor.moveToNext());
            }
        } else {
            Toast.makeText(getApplicationContext(), "No se encontraron resultados para su búsqueda",
                    Toast.LENGTH_LONG).show();
        }


        adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1,lista_resultados);
        lv.setAdapter(adaptador);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.overflow, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.item_micuenta) {
            Intent verCuenta = new Intent(VentanaListaPerfiles.this, VentanaCuenta.class);
            startActivity(verCuenta);
            finish();
            return true;
        } else if (id == R.id.item_crear) {
            Intent creaPerfil = new Intent(VentanaListaPerfiles.this, VentanaCrearPerfil.class);
            startActivity(creaPerfil);
            finish();
            return true;
        } else if (id == R.id.item_buscar) {
            Intent buscaPerfil = new Intent(VentanaListaPerfiles.this, VentanaBuscarPerfil.class);
            startActivity(buscaPerfil);
            finish();
            return true;
        } else if (id == R.id.item_cerrar) {
            Intent cierraSesion = new Intent(VentanaListaPerfiles.this, VentanaIngreso.class);
            startActivity(cierraSesion);
            finish();
            return true;
        } else if (id == R.id.item_salir) {
            finish();
            System.exit(0);
        }
        return super.onOptionsItemSelected(item);
    }
}
