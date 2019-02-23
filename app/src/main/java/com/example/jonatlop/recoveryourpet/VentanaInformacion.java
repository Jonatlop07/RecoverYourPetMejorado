package com.example.jonatlop.recoveryourpet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class VentanaInformacion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ventana_informacion);
    }

    public void Siguiente (View view) {
        Intent siguiente = new Intent(this, VentanaIngreso.class);
        startActivity(siguiente);
        finish();
    }
}
