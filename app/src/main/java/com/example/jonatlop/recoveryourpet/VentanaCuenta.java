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

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.overflow, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.item_micuenta) {
            return true;
        } else if (id == R.id.item_crear) {
            Intent creaPerfil = new Intent(VentanaCuenta.this, VentanaCrearPerfil.class);
            startActivity(creaPerfil);
            finish();
            return true;
        } else if (id == R.id.item_buscar) {
            Intent buscaPerfil = new Intent(VentanaCuenta.this, VentanaBuscarPerfil.class);
            startActivity(buscaPerfil);
            finish();
            return true;
        } else if (id == R.id.item_cerrar) {
            Intent cierraSesion = new Intent(VentanaCuenta.this, VentanaIngreso.class);
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
