package com.example.jonatlop.recoveryourpet;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import OpenHelper.AlmacenPerfilesMascotas;

public class VentanaListaPerfiles extends AppCompatActivity {

    private AlmacenPerfilesMascotas helper = new AlmacenPerfilesMascotas(this, "BD_Mascotas", null, 1);

    private String conjunto_datos[] = new String[5];
    private ListView lv;
    //private ArrayList<String> lista_resultados;
    private ArrayList<PerfilMascota> lista_resultados;
    private Adaptador adaptador;
    private PerfilMascota perfil_mascota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ventana_lista_perfiles);

        lv = (ListView) findViewById(R.id.lista);

        conjunto_datos = getIntent().getExtras().getStringArray("parametros_busqueda");
        Cursor cursor = helper.ConsultarPerfiles(conjunto_datos[0], conjunto_datos[1],
                                                    conjunto_datos[2], conjunto_datos[3], conjunto_datos[4]);

        final ArrayList<PerfilMascota> lista_resultados = new ArrayList<>();
        //ArrayList<String> lista_resultados = new ArrayList<>();

        if (cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    perfil_mascota = new PerfilMascota(cursor.getString(cursor.getColumnIndex("nombre")),
                            cursor.getString(cursor.getColumnIndex("especie")),
                            cursor.getString(cursor.getColumnIndex("generoM")),
                            cursor.getString(cursor.getColumnIndex("raza")),
                            cursor.getString(cursor.getColumnIndex("tamanio")),
                            cursor.getString(cursor.getColumnIndex("edad")),
                            cursor.getString(cursor.getColumnIndex("caractEspeciales")));

                    lista_resultados.add(perfil_mascota);

                    /*lista_resultados.add("NOMBRE: " + cursor.getString(cursor.getColumnIndex("nombre")) +
                            "\nRAZA: " + cursor.getString(cursor.getColumnIndex("raza")) +
                            "\nTAMAÑO: " + cursor.getString(cursor.getColumnIndex("tamanio")) +
                            "\nEDAD: " + cursor.getString(cursor.getColumnIndex("edad"))); */

                } while(cursor.moveToNext());
            }
        } else {
            Toast.makeText(getApplicationContext(), "No se encontraron resultados para su búsqueda",
                    Toast.LENGTH_LONG).show();
        }


        //adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1,lista_resultados);
        adaptador = new Adaptador(this, lista_resultados);
        lv.setAdapter(adaptador);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent ver_perfil = new Intent(VentanaListaPerfiles.this, VentanaVerPerfil.class);
                ver_perfil.putExtra("objetoPerfil", lista_resultados.get(position));
                startActivity(ver_perfil);
            }
        });

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

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setMessage("¿Deseas realizar la búsqueda de nuevo?");
        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent regresar_busqueda = new Intent(VentanaListaPerfiles.this, VentanaBuscarPerfil.class);
                startActivity(regresar_busqueda);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
