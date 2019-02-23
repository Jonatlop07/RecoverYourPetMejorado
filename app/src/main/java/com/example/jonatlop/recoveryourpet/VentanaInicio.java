package com.example.jonatlop.recoveryourpet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class VentanaInicio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ventana_inicio);
    }

    public void Comenzar (View view) {
        Intent comenzar = new Intent(this, VentanaInformacion.class);
        startActivity(comenzar);
        finish();
    }
}
