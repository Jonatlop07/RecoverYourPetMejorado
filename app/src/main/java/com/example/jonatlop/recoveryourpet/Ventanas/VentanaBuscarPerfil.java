package com.example.jonatlop.recoveryourpet.Ventanas;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.jonatlop.recoveryourpet.R;

public class VentanaBuscarPerfil extends AppCompatActivity {

    private Spinner especie_mascota, genero_mascota, raza_perro, raza_gato, tam_mascota, edad_mascota;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.ventana_buscar_perfil );

        especie_mascota = (Spinner) findViewById( R.id.especieMascota );
        genero_mascota = (Spinner) findViewById( R.id.generoMascota );
        raza_perro = (Spinner) findViewById( R.id.razaPerro );
        raza_gato = (Spinner) findViewById( R.id.razaGato );
        tam_mascota = (Spinner) findViewById( R.id.tamañoMascota );
        edad_mascota = (Spinner) findViewById( R.id.edadMascota );

        String[] opc_especie = { "Perro", "Gato" };
        ArrayAdapter<String> ad_especie = new ArrayAdapter<String>( this, android.R.layout.simple_spinner_item, opc_especie );
        especie_mascota.setAdapter( ad_especie );

        String[] opc_genero = { "Macho", "Hembra" };
        ArrayAdapter<String> ad_genero = new ArrayAdapter<String>( this, android.R.layout.simple_spinner_item, opc_genero );
        genero_mascota.setAdapter( ad_genero );

        String[] opc_tam = { "No responde", "Pequeño", "Mediano", "Grande" };
        ArrayAdapter<String> ad_tam = new ArrayAdapter<String>( this, android.R.layout.simple_spinner_item, opc_tam );
        tam_mascota.setAdapter( ad_tam );

        String[] opc_edad = { "No responde", "Entre 0 y 1 años", "Entre 1 y 3 años",
                              "Entre 3 y 5 años", "Entre 5 y 7 años", "Más de 7 años"};
        ArrayAdapter<String> ad_edad = new ArrayAdapter<String>( this, android.R.layout.simple_spinner_item, opc_edad );
        edad_mascota.setAdapter( ad_edad );

        String[] opc_raza_perro = { "No aplica", "Aidi", "Akita Inu", "Alangu", "Azawakh", "Bakharwal", "Barbet",
                                    "Beagle", "Border Collie", "Bóxer", "Boyero de Berna", "Brittany", "Bulldog",
                                    "Bull Terrier", "Chihuahua", "Chow Chow", "Cocker", "Collie", "Dálmata",
                                    "Dóberman", "Dogo", "Drever", "Esquimal Americano", "Galgo", "Golden Retriever",
                                    "Gran Danés", "Heeshond", "Hovawart", "Husky siberiano", "Jack Russell Terrier",
                                    "Komondor", "Labrador Retriever", "Leonberger", "Mastín", "Papillon", "Pastor Alemán",
                                    "Pastor Blanco suizo", "Pequinés", "Pincher", "Pit Bull", "Poitevino", "Poodle",
                                    "Pug", "Puli", "Rottweiler", "Ridgeback", "Sabueso de Tirol", "Sabueso Artesiano",
                                    "Saluki", "Samoyedo", "San Bernardo", "Schnauzer", "Shiba Inu", "Teckel", "Vizsla",
                                    "Whippet", "Yorkshire Terrier" };
        ArrayAdapter<String> ad_raza_perro = new ArrayAdapter<String>( this, android.R.layout.simple_spinner_item, opc_raza_perro );
        raza_perro.setAdapter( ad_raza_perro );

        String [] opc_raza_gato = { "No aplica", "Abisinio", "American Shorthair", "Angora turco", "Belga", "Bombay",
                                    "Bosque de Noruega", "British Shorthair", "Burmilla", "Chartreux", "Curl Americano",
                                    "Gato Somalí", "German Rex", "Habana Brown", "Himalayo", "Korat", "LaPerm", "Maine Coon",
                                    "Manx", "Mau Egipcio", "Munchkin", "Nebelung", "Ocicat", "Persa", "Peterbald",
                                    "Ragamuffin", "Ragdoll", "Ruso", "Savannah", "Scottish Fold", "Siamés", "Siberiano",
                                    "Singapura", "Snowshoe", "Sokoke", "Sphynx", "Toyger"};
        ArrayAdapter<String> ad_raza_gato = new ArrayAdapter<String>( this, android.R.layout.simple_spinner_item, opc_raza_gato );
        raza_gato.setAdapter( ad_raza_gato );
    }

    public boolean onCreateOptionsMenu( Menu menu ) {
        getMenuInflater().inflate( R.menu.overflow, menu );
        return true;
    }

    public boolean onOptionsItemSelected( MenuItem item ) {
        int id = item.getItemId();

        if ( id == R.id.item_micuenta ) {
            Intent verCuenta = new Intent( VentanaBuscarPerfil.this, VentanaCuenta.class );
            startActivity( verCuenta );
            finish();
            return true;
        } else if ( id == R.id.item_crear ) {
            Intent creaPerfil = new Intent( VentanaBuscarPerfil.this, VentanaCrearPerfil.class );
            startActivity( creaPerfil );
            finish();
            return true;
        } else if ( id == R.id.item_buscar ) {
            return true;
        } else if ( id == R.id.item_cerrar ) {
            Intent cierraSesion = new Intent( VentanaBuscarPerfil.this, VentanaIngreso.class );
            startActivity( cierraSesion );
            finish();
            return true;
        } else if ( id == R.id.item_salir ) {
            finish();
            System.exit( 0 );
        }
        return super.onOptionsItemSelected( item );
    }

    @RequiresApi( api = Build.VERSION_CODES.KITKAT )
    public void RealizarBusqueda ( View view ) {
        String especie_m = especie_mascota.getSelectedItem().toString();
        String genero_m = genero_mascota.getSelectedItem().toString();
        String tam_m = tam_mascota.getSelectedItem().toString();
        String edad_m = edad_mascota.getSelectedItem().toString();

        String raza_m;

        if ( especie_m.equals( "Perro" ) ) {
            raza_m = raza_perro.getSelectedItem().toString();
        } else {
            raza_m = raza_gato.getSelectedItem().toString();
        }

        String[] parametros_busqueda = { especie_m, genero_m, raza_m, tam_m, edad_m };

        Intent realizar_busqueda = new Intent ( VentanaBuscarPerfil.this, VentanaListaPerfiles.class );
        realizar_busqueda.putExtra( "parametros_busqueda", parametros_busqueda );
        startActivity( realizar_busqueda );
        finish();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder= new AlertDialog.Builder( this );
        builder.setMessage( "Estás a punto de cerrar sesión ¿Deseas continuar?" );
        builder.setTitle( "Mensaje de confirmación" );
        builder.setPositiveButton( "Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick( DialogInterface dialog, int which ) {
                Intent regresar_ingreso = new Intent( VentanaBuscarPerfil.this, VentanaIngreso.class );
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
