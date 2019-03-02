package com.example.jonatlop.recoveryourpet;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;

public class VentanaVerPerfil extends AppCompatActivity {

    private PerfilMascota perfil;
    private TextView nombre, especie, genero, raza, tamanio, edad, c_especiales;
    private ImageView imgFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ventana_ver_perfil);

        perfil = (PerfilMascota) getIntent().getSerializableExtra("objetoPerfil");

        nombre =  (TextView) findViewById(R.id.n_m);
        especie =  (TextView) findViewById(R.id.e_m);
        genero =  (TextView) findViewById(R.id.g_m);
        raza =  (TextView) findViewById(R.id.r_m);
        tamanio =  (TextView) findViewById(R.id.t_m);
        edad =  (TextView) findViewById(R.id.ed_m);
        c_especiales =  (TextView) findViewById(R.id.c_m);
        imgFoto = (ImageView) findViewById(R.id.f_m);

        nombre.setText(perfil.getNombre());
        especie.setText(perfil.getEspecie());
        genero.setText(perfil.getGenero());
        raza.setText("Raza: " + perfil.getRaza());
        tamanio.setText("Tamaño: " + perfil.getTamanio());
        edad.setText("Edad: " + perfil.getEdad());
        c_especiales.setText(perfil.getCaract_esp());

        if (perfil.getFoto() != null) {
            Bitmap bitmap = null;
            ByteArrayInputStream bais = new ByteArrayInputStream(perfil.getFoto());
            bitmap = BitmapFactory.decodeStream(bais);
            imgFoto.setImageBitmap(bitmap);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
