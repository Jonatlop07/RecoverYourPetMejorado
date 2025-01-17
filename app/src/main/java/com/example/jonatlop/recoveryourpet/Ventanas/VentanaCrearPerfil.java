package com.example.jonatlop.recoveryourpet.Ventanas;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jonatlop.recoveryourpet.R;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import OpenHelper.BaseDatosSQLite;

public class VentanaCrearPerfil extends AppCompatActivity {
    public static int SELECT_PICTURE = 1;

    private String name = "", correo;

    private BaseDatosSQLite helper = new BaseDatosSQLite( this, "BD_Mascotas", null, 1 );

    private Spinner especie_mascota, genero_mascota, raza_perro, raza_gato, tam_mascota, edad_mascota;
    private EditText nombre_mascota, caract_esp;
    private ImageView iv;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.ventana_crear_perfil );

        correo = getIntent().getExtras().getString( "correo" );

        name = Environment.getExternalStorageDirectory() + "/test.jpg";

        nombre_mascota= (EditText) findViewById( R.id.nombre_m );
        caract_esp = (EditText) findViewById( R.id.caract_especificas );
        especie_mascota = (Spinner) findViewById( R.id.especie_m );
        genero_mascota = (Spinner) findViewById( R.id.genero_m );
        raza_perro = (Spinner) findViewById( R.id.raza_p );
        raza_gato = (Spinner) findViewById( R.id.raza_g );
        tam_mascota = (Spinner) findViewById( R.id.tam_m );
        edad_mascota = (Spinner) findViewById( R.id.edad_m );
        iv = (ImageView) findViewById( R.id.imgMascota );

        String[] opc_especie = { "Perro", "Gato" };
        ArrayAdapter<String> ad_especie = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opc_especie);
        especie_mascota.setAdapter( ad_especie );

        String[] opc_genero = { "Macho", "Hembra" };
        ArrayAdapter<String> ad_genero = new ArrayAdapter<String>( this, android.R.layout.simple_spinner_item, opc_genero );
        genero_mascota.setAdapter( ad_genero );

        String[] opc_tam = { "No responde", "Pequeño", "Mediano", "Grande" };
        ArrayAdapter<String> ad_tam = new ArrayAdapter<String>( this, android.R.layout.simple_spinner_item, opc_tam );
        tam_mascota.setAdapter( ad_tam );

        String[] opc_edad = { "No responde", "Entre 0 y 1 años", "Entre 1 y 3 años",
                              "Entre 3 y 5 años", "Entre 5 y 7 años", "Más de 7 años" };
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

        Button btnInsertarImg = (Button) findViewById( R.id.inserta_foto );
        btnInsertarImg.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                Intent intent = new Intent( Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI );
                int code = SELECT_PICTURE;
                startActivityForResult( intent, code );
            }
        });
    }

    public boolean onCreateOptionsMenu( Menu menu ) {
        getMenuInflater().inflate( R.menu.overflow, menu );
        return true;
    }

    public boolean onOptionsItemSelected( MenuItem item ) {
        int id = item.getItemId();

        if ( id == R.id.item_micuenta ) {
            Intent verCuenta = new Intent( VentanaCrearPerfil.this, VentanaCuenta.class );
            verCuenta.putExtra( "correo", correo );
            startActivity( verCuenta );
            finish();
            return true;
        } else if ( id == R.id.item_crear ) {
            nombre_mascota.setText( "" );
            caract_esp.setText( "" );
            return true;
        } else if ( id == R.id.item_buscar ) {
            Intent buscaPerfil = new Intent( VentanaCrearPerfil.this, VentanaBuscarPerfil.class );
            buscaPerfil.putExtra( "correo", correo );
            startActivity( buscaPerfil );
            finish();
            return true;
        } else if ( id == R.id.item_cerrar ) {
            Intent cierraSesion = new Intent( VentanaCrearPerfil.this, VentanaIngreso.class );
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
    protected void onActivityResult ( int requestCode, int resultCode, Intent data ) {
        if ( resultCode == RESULT_OK ) {
            Uri selectedImage = data.getData();
            InputStream is;
            try {
                is = getContentResolver().openInputStream( selectedImage );
                BufferedInputStream bis = new BufferedInputStream( is );
                Bitmap bitmap = BitmapFactory.decodeStream( bis );
                iv.setImageBitmap( bitmap );
            } catch ( FileNotFoundException e ) {
                e.printStackTrace();
            }
        } else {
            return;
        }

    }

    private byte[] imageToByte( ImageView image ) {
        Bitmap bitmap = ( (BitmapDrawable) image.getDrawable() ).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress( Bitmap.CompressFormat.PNG, 100, stream );
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    public void RegistrarPerfil (View view) {
        String nombre_m = nombre_mascota.getText().toString();
        String especie_m = especie_mascota.getSelectedItem().toString();
        String genero_m = genero_mascota.getSelectedItem().toString();
        String tam_m = tam_mascota.getSelectedItem().toString();
        String edad_m = edad_mascota.getSelectedItem().toString();
        String caract_esp_m = caract_esp.getText().toString();
        String raza_m;
        byte[] foto_byte = imageToByte( iv );

        if (especie_m.equals( "Perro" ) ) {
            raza_m = raza_perro.getSelectedItem().toString();
        } else {
            raza_m = raza_gato.getSelectedItem().toString();
        }

        if ( nombre_m.isEmpty() ) {
            Toast.makeText( getApplicationContext(), "Por favor, ingresa el nombre de la mascota",
                           Toast.LENGTH_SHORT ).show();
        } else {
            nombre_mascota.setText( "" );
            caract_esp.setText( "" );
            helper.abrir();
            helper.insertarPerfil( nombre_m, especie_m, genero_m, raza_m, tam_m, edad_m, caract_esp_m, foto_byte, correo );
            helper.cerrar();

            Toast.makeText( getApplicationContext(), "Perfil completado y guardado con éxito.", Toast.LENGTH_LONG ).show();
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder= new AlertDialog.Builder( this );
        builder.setMessage( "Estás a punto de cerrar sesión ¿Deseas continuar?" );
        builder.setTitle( "Mensaje de confirmación" );
        builder.setPositiveButton( "Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick( DialogInterface dialog, int which ) {
                Intent regresar_ingreso = new Intent( VentanaCrearPerfil.this, VentanaIngreso.class );
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
