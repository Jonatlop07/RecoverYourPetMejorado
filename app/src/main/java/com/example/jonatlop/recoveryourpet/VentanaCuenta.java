package com.example.jonatlop.recoveryourpet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class VentanaCuenta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ventana_cuenta);
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
