package com.example.jonatlop.recoveryourpet.Ventanas;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.jonatlop.recoveryourpet.R;

public class VentanaInformacion extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.ventana_informacion );
    }

    public void Siguiente ( View view ) {
        Intent siguiente = new Intent( this, VentanaIngreso.class );
        startActivity( siguiente );
        finish();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder= new AlertDialog.Builder( this );
        builder.setMessage( "Estás a punto de cerrar la aplicación ¿Deseas continuar?" );
        builder.setTitle( "Mensaje de confirmación" );
        builder.setPositiveButton( "Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick( DialogInterface dialog, int which ) {
                finish();
                System.exit( 0 );
            }
        });
        builder.setNegativeButton( "Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
