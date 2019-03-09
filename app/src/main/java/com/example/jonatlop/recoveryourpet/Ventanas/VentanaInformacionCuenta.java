package com.example.jonatlop.recoveryourpet.Ventanas;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.jonatlop.recoveryourpet.R;
import com.example.jonatlop.recoveryourpet.utilidades.Utilidades;

import OpenHelper.BaseDatosSQLite;

public class VentanaInformacionCuenta extends AppCompatActivity {
    private BaseDatosSQLite helper = new BaseDatosSQLite( this, "BD_Usuarios", null, 1 );
    private String correo;
    private TextView cuenta_nombres, cuenta_apellidos, cuenta_genero, cuenta_telefono, cuenta_correo;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.ventana_informacion_cuenta);

        correo = getIntent().getExtras().getString( "correo" );

        cuenta_nombres = (TextView) findViewById(R.id.cuenta_nombres);
        cuenta_apellidos = (TextView) findViewById(R.id.cuenta_apellidos);
        cuenta_genero = (TextView) findViewById(R.id.cuenta_genero);
        cuenta_telefono = (TextView) findViewById(R.id.cuenta_telefono);
        cuenta_correo = (TextView) findViewById(R.id.cuenta_correo);

        Cursor cursor = helper.ConsultarInformacionCuenta( correo );

        if ( cursor.moveToFirst() ) {
            cuenta_nombres.setText("Nombres: " + cursor.getString(cursor.getColumnIndex(Utilidades.CAMPO_NOMBRES_USUARIO)));
            cuenta_apellidos.setText("Apellidos: " + cursor.getString( cursor.getColumnIndex(Utilidades.CAMPO_APELLIDOS_USUARIO)));
            cuenta_genero.setText("Género: " + cursor.getString( cursor.getColumnIndex(Utilidades.CAMPO_GENERO_USUARIO)));
            cuenta_telefono.setText("Teléfono: " + cursor.getString( cursor.getColumnIndex(Utilidades.CAMPO_TELEFONO_USUARIO)));
            cuenta_correo.setText("Correo: " + cursor.getString( cursor.getColumnIndex(Utilidades.CAMPO_CORREO_USUARIO)));
        }
    }
}
