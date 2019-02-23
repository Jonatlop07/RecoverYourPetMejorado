package com.example.jonatlop.recoveryourpet;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import OpenHelper.AlmacenPerfilesMascotas;

public class VentanaListaPerfiles extends AppCompatActivity {

    private AlmacenPerfilesMascotas helper = new AlmacenPerfilesMascotas(this, "BD_Mascotas", null, 1);
    //private TextView itemNombre, itemEspecie, itemGenero, itemRaza;
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
        if (cursor.moveToFirst()) {
            do {
                lista_resultados.add("NOMBRE: " + cursor.getString(cursor.getColumnIndex("nombre")) +
                        "\nRAZA: " + cursor.getString(cursor.getColumnIndex("raza")) +
                        "\nTAMAÑO: " + cursor.getString(cursor.getColumnIndex("tamanio")) +
                        "\nEDAD: " + cursor.getString(cursor.getColumnIndex("edad")));

            } while(cursor.moveToNext());
        }

        adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1,lista_resultados);
        lv.setAdapter(adaptador);

    }

    public void CreaPerfil(View view) {
        Intent boton_creaPerfil = new Intent(this, VentanaCrearPerfil.class);
        startActivity(boton_creaPerfil);
    }

    public void BuscaPerfil(View view) {
        Intent boton_buscaPerfil = new Intent(this, VentanaBuscarPerfil.class);
        startActivity(boton_buscaPerfil);
    }
}
