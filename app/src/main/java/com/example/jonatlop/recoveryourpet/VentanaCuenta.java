package com.example.jonatlop.recoveryourpet;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class VentanaCuenta extends AppCompatActivity {
    private String correo_usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ventana_cuenta);

        correo_usuario = getIntent().getExtras().getString("correo");
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

    public void verInfoCuenta (View view) {

    }

    public void verPerfilesCreados (View view) {

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setMessage("Estás a punto de cerrar sesión ¿Deseas continuar?");
        builder.setTitle("Mensaje de confirmación");
        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent regresar_ingreso = new Intent(VentanaCuenta.this, VentanaIngreso.class);
                startActivity(regresar_ingreso);
                finish();
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
