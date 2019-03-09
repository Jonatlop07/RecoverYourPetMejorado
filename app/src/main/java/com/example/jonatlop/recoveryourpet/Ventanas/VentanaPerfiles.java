package com.example.jonatlop.recoveryourpet.Ventanas;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jonatlop.recoveryourpet.Entidades.PerfilMascota;
import com.example.jonatlop.recoveryourpet.R;
import com.example.jonatlop.recoveryourpet.utilidades.Adaptador;
import com.example.jonatlop.recoveryourpet.utilidades.Utilidades;

import java.util.ArrayList;

import OpenHelper.BaseDatosSQLite;

public class VentanaPerfiles extends AppCompatActivity {
    private BaseDatosSQLite helper = new BaseDatosSQLite( this, "BD_Mascotas", null, 1 );
    private FragmentVerPerfiles fragmentVerPerfiles;
    private String correo;
    private ListView lv_perfiles;
    private Adaptador adaptador;
    private PerfilMascota perfil_mascota;
    private View view;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.ventana_perfiles );

        correo = getIntent().getExtras().getString("correo");

        lv_perfiles = (ListView) findViewById( R.id.lista_mis_perfiles );
        Cursor cursor = helper.ConsultarMisPerfilesCreados( correo );

        final ArrayList<PerfilMascota> lista_perfiles = new ArrayList<>();

        if ( cursor.getCount() > 0 ) {
            if ( cursor.moveToFirst() ) {
                do {
                    perfil_mascota = new PerfilMascota( cursor.getString( cursor.getColumnIndex( Utilidades.CAMPO_NOMBRE_MASCOTA ) ),
                            cursor.getString( cursor.getColumnIndex( Utilidades.CAMPO_ESPECIE_MASCOTA ) ),
                            cursor.getString( cursor.getColumnIndex( Utilidades.CAMPO_GENERO_MASCOTA ) ),
                            cursor.getString( cursor.getColumnIndex( Utilidades.CAMPO_RAZA_MASCOTA ) ),
                            cursor.getString( cursor.getColumnIndex( Utilidades.CAMPO_TAMANIO_MASCOTA ) ),
                            cursor.getString( cursor.getColumnIndex( Utilidades.CAMPO_EDAD_MASCOTA ) ),
                            cursor.getBlob( cursor.getColumnIndex( Utilidades.CAMPO_FOTO_MASCOTA ) ),
                            cursor.getString( cursor.getColumnIndex( Utilidades.CAMPO_CARACT_ESP_MASCOTA )),
                            cursor.getString( cursor.getColumnIndex( Utilidades.CAMPO_DUENIO_MASCOTA )));
                    lista_perfiles.add( perfil_mascota );
                } while( cursor.moveToNext() );
            }
        } else {
            Toast.makeText( this, "No has creado un perfil de mascota hasta el momento",
                    Toast.LENGTH_LONG ).show();
        }

        adaptador = new Adaptador( this, lista_perfiles );
        lv_perfiles.setAdapter( adaptador );

        lv_perfiles.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick( AdapterView<?> parent, View view, int position, long id ) {
                Intent ver_seleccion = new Intent( VentanaPerfiles.this, VentanaVerPerfil.class );
                ver_seleccion.putExtra( "objetoPerfil", lista_perfiles.get(position) );
                startActivity( ver_seleccion );
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
