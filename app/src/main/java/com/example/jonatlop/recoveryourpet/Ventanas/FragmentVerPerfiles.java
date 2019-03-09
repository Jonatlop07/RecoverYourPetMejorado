package com.example.jonatlop.recoveryourpet.Ventanas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jonatlop.recoveryourpet.Entidades.PerfilMascota;
import com.example.jonatlop.recoveryourpet.R;

import java.io.ByteArrayInputStream;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentVerPerfiles.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentVerPerfiles#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentVerPerfiles extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FragmentVerPerfiles() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentVerPerfiles.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentVerPerfiles newInstance(String param1, String param2) {
        FragmentVerPerfiles fragment = new FragmentVerPerfiles();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private PerfilMascota perfil;
    private TextView nombre, especie, genero, raza, tamanio, edad, c_especiales;
    private ImageView imgFoto;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ver_perfiles, container, false);

        /*perfil = (PerfilMascota) getArguments().getSerializable( "objetoPerfil" );

        nombre =  (TextView) view.findViewById( R.id.n_m );
        especie =  (TextView) view.findViewById( R.id.e_m );
        genero =  (TextView) view.findViewById( R.id.g_m );
        raza =  (TextView) view.findViewById( R.id.r_m );
        tamanio =  (TextView) view.findViewById( R.id.t_m );
        edad =  (TextView) view.findViewById( R.id.ed_m );
        c_especiales =  (TextView) view.findViewById( R.id.c_m );
        imgFoto = (ImageView) view.findViewById( R.id.f_m );

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
        }*/

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
