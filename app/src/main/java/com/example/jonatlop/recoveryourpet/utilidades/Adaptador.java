package com.example.jonatlop.recoveryourpet.utilidades;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jonatlop.recoveryourpet.Entidades.PerfilMascota;
import com.example.jonatlop.recoveryourpet.R;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

public class Adaptador extends BaseAdapter {
    private Context context;
    private ArrayList<PerfilMascota> listItems;

    public Adaptador( Context context, ArrayList<PerfilMascota> listItems ) {
        this.context = context;
        this.listItems = listItems;
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem( int position ) {
        return listItems.get( position );
    }

    @Override
    public long getItemId( int position ) {
        return 0;
    }

    @Override
    public View getView( int position, View convertView, ViewGroup parent ) {
        final PerfilMascota perfil = (PerfilMascota) getItem( position );
        Bitmap bitmap = null;

        convertView = LayoutInflater.from( context ).inflate( R.layout.item, null );
        ImageView imgFoto = (ImageView) convertView.findViewById( R.id.imgFoto );
        TextView perfNombre = (TextView) convertView.findViewById( R.id.nombre_mascota );
        TextView perfRaza = (TextView) convertView.findViewById( R.id.raza_mascota );
        TextView perfTamanio = (TextView) convertView.findViewById( R.id.tam_mascota );
        TextView perfEdad = (TextView) convertView.findViewById( R.id.edad_mascota );


        perfNombre.setText( perfil.getNombre() );
        perfRaza.setText( perfil.getRaza() );
        perfTamanio.setText( perfil.getTamanio() );
        perfEdad.setText( perfil.getEdad() );

        if ( perfil.getFoto() != null ) {
            ByteArrayInputStream bais = new ByteArrayInputStream( perfil.getFoto() );
            bitmap = BitmapFactory.decodeStream( bais );
            imgFoto.setImageBitmap( bitmap );
        }

        return convertView;
    }
}
