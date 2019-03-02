package com.example.jonatlop.recoveryourpet;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import OpenHelper.AlmacenDatosUsuario;

public class VentanaIngreso extends AppCompatActivity {

    private TextView tvRegistrese;
    private EditText correo_usuario, clave_usuario;

    AlmacenDatosUsuario helper = new AlmacenDatosUsuario(this, "BD_Usuarios", null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ventana_ingreso);

        correo_usuario = (EditText) findViewById(R.id.ingresa_correo);
        clave_usuario = (EditText) findViewById(R.id.ingresa_clave);
        tvRegistrese = (TextView) findViewById(R.id.tv_Registrese);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void IniciarSesion(View view) {
        String correo_u = correo_usuario.getText().toString();
        String clave_u = clave_usuario.getText().toString();
        try (Cursor cursor = helper.ConsultarCorreoClave(correo_u, clave_u)) {
            if (cursor.getCount() > 0) {
                Intent iniciar_sesion = new Intent (getApplicationContext(), VentanaCuenta.class);
                iniciar_sesion.putExtra("correo", correo_u);
                startActivity(iniciar_sesion);
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "Correo y/o clave incorrectos",
                        Toast.LENGTH_SHORT).show();
            }
            correo_usuario.setText("");
            clave_usuario.setText("");
            correo_usuario.findFocus();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void irVentanaRegistro(View view) {
        Intent enlace_registrarse = new Intent(this, VentanaRegistro.class);
        startActivity(enlace_registrarse);
        finish();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setMessage("Estás a punto de cerrar la aplicación ¿Deseas continuar?");
        builder.setTitle("Mensaje de confirmación");
        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                System.exit(0);
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
