package com.example.jonatlop.recoveryourpet.Ventanas;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

public class VentanaListaPerfiles extends AppCompatActivity {

    private BaseDatosSQLite helper = new BaseDatosSQLite( this, "BD_Mascotas", null, 1 );

    private String[] conjunto_datos = new String[ 5 ];
    private ListView lv;
    private Adaptador adaptador;
    private PerfilMascota perfil_mascota;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.ventana_lista_perfiles );

        lv = (ListView) findViewById( R.id.lista );

        conjunto_datos = getIntent().getExtras().getStringArray( "parametros_busqueda" );
        Cursor cursor = helper.ConsultarPerfilesMascotas( conjunto_datos[ 0 ], conjunto_datos[ 1 ],
                                                  conjunto_datos[ 2 ], conjunto_datos[ 3 ],
                                                  conjunto_datos[ 4 ] );

        final ArrayList<PerfilMascota> lista_resultados = new ArrayList<>();

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
                                                        cursor.getString( cursor.getColumnIndex( Utilidades.CAMPO_CARACT_ESP_MASCOTA)));
                    lista_resultados.add( perfil_mascota );
                } while( cursor.moveToNext() );
            }
        } else {
            Toast.makeText( getApplicationContext(), "No se encontraron resultados para su búsqueda",
                            Toast.LENGTH_LONG ).show();
        }

        adaptador = new Adaptador( this, lista_resultados );
        lv.setAdapter( adaptador );

        lv.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick( AdapterView<?> parent, View view, int position, long id ) {
                Intent ver_perfil = new Intent( VentanaListaPerfiles.this, VentanaVerPerfil.class );
                ver_perfil.putExtra( "objetoPerfil", lista_resultados.get(position) );
                startActivity( ver_perfil );
            }
        });
    }

    public boolean onCreateOptionsMenu( Menu menu ) {
        getMenuInflater().inflate( R.menu.overflow, menu );
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if ( id == R.id.item_micuenta ) {
            Intent verCuenta = new Intent( VentanaListaPerfiles.this, VentanaCuenta.class );
            startActivity( verCuenta );
            finish();
            return true;
        } else if (id == R.id.item_crear) {
            Intent creaPerfil = new Intent( VentanaListaPerfiles.this, VentanaCrearPerfil.class );
            startActivity( creaPerfil );
            finish();
            return true;
        } else if ( id == R.id.item_buscar ) {
            Intent buscaPerfil = new Intent( VentanaListaPerfiles.this, VentanaBuscarPerfil.class );
            startActivity( buscaPerfil );
            finish();
            return true;
        } else if ( id == R.id.item_cerrar ) {
            Intent cierraSesion = new Intent( VentanaListaPerfiles.this, VentanaIngreso.class );
            startActivity( cierraSesion );
            finish();
            return true;
        } else if ( id == R.id.item_salir ) {
            finish();
            System.exit( 0 );
        }
        return super.onOptionsItemSelected( item );
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder= new AlertDialog.Builder( this );
        builder.setMessage( "¿Deseas realizar la búsqueda de nuevo?" );
        builder.setPositiveButton( "Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick( DialogInterface dialog, int which ) {
                Intent regresar_busqueda = new Intent( VentanaListaPerfiles.this, VentanaBuscarPerfil.class );
                startActivity( regresar_busqueda );
                finish();
            }
        });
        builder.setNegativeButton( "No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick( DialogInterface dialog, int which ) {
                dialog.cancel();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
