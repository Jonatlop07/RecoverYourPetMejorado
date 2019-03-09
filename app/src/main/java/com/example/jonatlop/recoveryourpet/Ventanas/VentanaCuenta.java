package com.example.jonatlop.recoveryourpet.Ventanas;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.jonatlop.recoveryourpet.R;

public class VentanaCuenta extends AppCompatActivity
        implements FragmentInformacionCuenta.OnFragmentInteractionListener, FragmentPerfiles.OnFragmentInteractionListener,
                   FragmentVerPerfiles.OnFragmentInteractionListener {

    private FragmentInformacionCuenta fragmentInfo;
    private FragmentPerfiles fragmentPerfiles;
    private String correo;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.ventana_cuenta );

        correo = getIntent().getExtras().getString( "correo" );

        /*Bundle args = new Bundle();

        args.putString( "correo_u", correo );

        fragmentInfo = new FragmentInformacionCuenta();
        fragmentPerfiles = new FragmentPerfiles();

        fragmentInfo.setArguments( args );

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(R.id.contenedorFragment, fragmentInfo);
        ft.commit();*/
    }

    public boolean onCreateOptionsMenu( Menu menu ) {
        getMenuInflater().inflate( R.menu.overflow, menu );
        return true;
    }

    public boolean onOptionsItemSelected( MenuItem item ) {
        int id = item.getItemId();

        if ( id == R.id.item_micuenta ) {
            return true;
        } else if ( id == R.id.item_crear ) {
            Intent creaPerfil = new Intent( VentanaCuenta.this, VentanaCrearPerfil.class );
            creaPerfil.putExtra( "correo", correo );
            startActivity( creaPerfil );
            finish();
            return true;
        } else if ( id == R.id.item_buscar ) {
            Intent buscaPerfil = new Intent( VentanaCuenta.this, VentanaBuscarPerfil.class );
            buscaPerfil.putExtra( "correo", correo );
            startActivity( buscaPerfil );
            finish();
            return true;
        } else if (id == R.id.item_cerrar) {
            Intent cierraSesion = new Intent( VentanaCuenta.this, VentanaIngreso.class );
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
    public void onFragmentInteraction(Uri uri) {

    }

    public void onClick( View view ) {
        switch ( view.getId() ) {
            case R.id.btn_verInfo:
                Intent ver_info = new Intent( VentanaCuenta.this, VentanaInformacionCuenta.class );
                ver_info.putExtra( "correo", correo );
                startActivity( ver_info );
                break;
            case R.id.btn_verPerfiles:
                Intent ver_perfiles = new Intent( VentanaCuenta.this, VentanaPerfiles.class );
                ver_perfiles.putExtra( "correo", correo );
                startActivity( ver_perfiles );
                break;
        }
        /*Bundle args = new Bundle();
        args.putString( "correo", correo );

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        fragmentPerfiles.setArguments( args );

        switch ( view.getId() ) {
            case R.id.btn_verInfo:
                transaction.replace( R.id.contenedorFragment, fragmentInfo );
                break;
            case R.id.btn_verPerfiles:
                transaction.replace( R.id.contenedorFragment, fragmentPerfiles );
                break;
        }
        transaction.commit();*/
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder= new AlertDialog.Builder( this );
        builder.setMessage( "Estás a punto de cerrar sesión ¿Deseas continuar?" );
        builder.setTitle( "Mensaje de confirmación" );
        builder.setPositiveButton( "Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent regresar_ingreso = new Intent( VentanaCuenta.this, VentanaIngreso.class );
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
