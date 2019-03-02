package com.example.jonatlop.recoveryourpet;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import OpenHelper.AlmacenDatosUsuario;

public class VentanaRegistro extends AppCompatActivity {

    private AlmacenDatosUsuario helper = new AlmacenDatosUsuario( this, "BD_Usuarios", null, 1 );

    private EditText nombres_usuario, apellidos_usuario, telefono_usuario;
    private EditText correo_usuario, clave_usuario, confirmar_clave_usuario;
    private Spinner genero_usuario;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.ventana_registro );

        nombres_usuario = (EditText) findViewById( R.id.txt_nombres );
        apellidos_usuario = (EditText) findViewById( R.id.txt_apellidos );
        genero_usuario = (Spinner) findViewById( R.id.spinner_genero_usuario );
        telefono_usuario = (EditText) findViewById( R.id.txt_telefono );
        correo_usuario = (EditText) findViewById( R.id.txt_correo );
        clave_usuario = (EditText) findViewById( R.id.txt_clave );
        confirmar_clave_usuario = (EditText) findViewById( R.id.txt_confirmacion_clave );

        String[] opciones = { "Hombre", "Mujer", "Otro" };

        ArrayAdapter <String> adapter = new ArrayAdapter<String>( this, android.R.layout.simple_spinner_item, opciones );
        genero_usuario.setAdapter( adapter );

    }

    //Método del botón spinner
    public void Registrarse( View view ) {
        String nombres_u = nombres_usuario.getText().toString();
        String apellidos_u = apellidos_usuario.getText().toString();
        String genero_u = genero_usuario.getSelectedItem().toString();
        String telefono_u = telefono_usuario.getText().toString();
        String correo_u = correo_usuario.getText().toString();
        String clave_u = clave_usuario.getText().toString();
        String confirmar_clave_u = confirmar_clave_usuario.getText().toString();

        if ( nombres_u.isEmpty() || apellidos_u.isEmpty() ||
             telefono_u.isEmpty() || correo_u.isEmpty() ||
             clave_u.isEmpty() ) {

            Toast.makeText( getApplicationContext(), "Por favor, ingresa los datos solicitados en su totalidad.",
                            Toast.LENGTH_SHORT ).show();
        } else if ( !clave_u.equals( confirmar_clave_u ) ) {
            Toast.makeText( getApplicationContext(), "Asegúrate que las claves coincidan",
                            Toast.LENGTH_SHORT ).show();
        } else {
            helper.abrir();
            helper.insertarRegistro( nombres_u, apellidos_u, genero_u, telefono_u, correo_u, clave_u );
            helper.cerrar();

            nombres_usuario.setText( "" );
            apellidos_usuario.setText( "" );
            telefono_usuario.setText( "" );
            correo_usuario.setText( "" );
            clave_usuario.setText( "" );
            confirmar_clave_usuario.setText( "" );

            Toast.makeText( getApplicationContext(), "Registro completado con éxito.", Toast.LENGTH_LONG).show();

            Intent boton_registrarse = new Intent( this, VentanaCuenta.class );
            startActivity( boton_registrarse );
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder= new AlertDialog.Builder( this );
        builder.setMessage( "¿Deseas iniciar sesión?" );
        builder.setPositiveButton( "Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick( DialogInterface dialog, int which ) {
                Intent regresar_ingreso = new Intent( VentanaRegistro.this, VentanaIngreso.class );
                startActivity( regresar_ingreso );
                finish();
            }
        });
        builder.setNegativeButton( "Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick( DialogInterface dialog, int which ) {
                dialog.cancel();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
