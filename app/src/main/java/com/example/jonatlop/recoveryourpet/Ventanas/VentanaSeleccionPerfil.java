package com.example.jonatlop.recoveryourpet.Ventanas;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jonatlop.recoveryourpet.Entidades.PerfilMascota;
import com.example.jonatlop.recoveryourpet.R;
import com.example.jonatlop.recoveryourpet.utilidades.Utilidades;

import java.io.ByteArrayInputStream;

import OpenHelper.BaseDatosSQLite;

public class VentanaSeleccionPerfil extends AppCompatActivity {
    private PerfilMascota perfil;
    private TextView nombre, especie, genero, raza, tamanio, edad, c_especiales;
    private ImageView imgFoto;
    private String correo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ventana_seleccion_perfil);

        correo = getIntent().getExtras().getString( "correo" );
        perfil = (PerfilMascota) getIntent().getExtras().getSerializable( "objetoPerfil" );

        nombre =  (TextView) findViewById( R.id.n_m );
        especie =  (TextView) findViewById( R.id.e_m );
        genero =  (TextView) findViewById( R.id.g_m );
        raza =  (TextView) findViewById( R.id.r_m );
        tamanio =  (TextView) findViewById( R.id.t_m );
        edad =  (TextView) findViewById( R.id.ed_m );
        c_especiales =  (TextView) findViewById( R.id.c_m );
        imgFoto = (ImageView) findViewById( R.id.f_m );

        nombre.setText( perfil.getNombre() );
        especie.setText( "Especie: " + perfil.getEspecie() );
        genero.setText( "Género: " + perfil.getGenero() );
        raza.setText( "Raza: " + perfil.getRaza() );
        tamanio.setText( "Tamaño: " + perfil.getTamanio() );
        edad.setText( "Edad: " + perfil.getEdad() );
        c_especiales.setText("Características especiales: \n" + perfil.getCaract_esp());

        if ( perfil.getFoto() != null ) {
            Bitmap bitmap = null;
            ByteArrayInputStream bais = new ByteArrayInputStream( perfil.getFoto() );
            bitmap = BitmapFactory.decodeStream( bais );
            imgFoto.setImageBitmap( bitmap );
        }
    }

    public void EliminarPerfil( View view ) {
        AlertDialog.Builder builder= new AlertDialog.Builder( this );
        builder.setMessage( "Estás a punto de eliminar el perfil actual ¿Deseas continuar?" );
        builder.setTitle( "Confirmar eliminación" );
        builder.setPositiveButton( "Confirmar" , new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                BaseDatosSQLite helper = new BaseDatosSQLite( getApplicationContext(), "BD_Mascotas", null, 1);
                SQLiteDatabase db = helper.getWritableDatabase();

                final String SENTENCIA_ELIMINACION = ""+Utilidades.CAMPO_DUENIO_MASCOTA+" = '"+correo+"' "+
                        "and "+Utilidades.CAMPO_NOMBRE_MASCOTA+" = '"+perfil.getNombre()+"' ";

                db.delete( Utilidades.TABLA_MASCOTA, SENTENCIA_ELIMINACION, null );
                db.close();
                Toast.makeText( getApplicationContext(), "El perfil ha sido eliminado exitosamente", Toast.LENGTH_LONG ).show();
                Intent ir_a_lista = new Intent( VentanaSeleccionPerfil.this, VentanaPerfiles.class );
                ir_a_lista.putExtra( "correo", correo );
                startActivity( ir_a_lista );
                finish();
            }
        });
        builder.setNegativeButton( "Cancelar" , new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}