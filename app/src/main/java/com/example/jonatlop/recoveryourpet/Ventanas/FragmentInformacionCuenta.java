package com.example.jonatlop.recoveryourpet.Ventanas;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jonatlop.recoveryourpet.Entidades.PerfilMascota;
import com.example.jonatlop.recoveryourpet.R;
import com.example.jonatlop.recoveryourpet.utilidades.Utilidades;

import OpenHelper.BaseDatosSQLite;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentInformacionCuenta.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentInformacionCuenta#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentInformacionCuenta extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FragmentInformacionCuenta() {
        // Required empty public constructor
    }

    public static FragmentInformacionCuenta newInstance(String param1, String param2) {
        FragmentInformacionCuenta fragment = new FragmentInformacionCuenta();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private BaseDatosSQLite helper = new BaseDatosSQLite( this.getActivity(), "BD_Usuarios", null, 1 );
    private String correo_u;
    private TextView cuenta_nombres, cuenta_apellidos, cuenta_genero, cuenta_telefono, cuenta_correo;

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
        View view = inflater.inflate(R.layout.fragment_informacion_cuenta, container, false);
        correo_u = getArguments().getString( "correo_u" );

        cuenta_nombres = (TextView) view.findViewById(R.id.cuenta_nombres);
        cuenta_apellidos = (TextView) view.findViewById(R.id.cuenta_apellidos);
        cuenta_genero = (TextView) view.findViewById(R.id.cuenta_genero);
        cuenta_telefono = (TextView) view.findViewById(R.id.cuenta_telefono);
        cuenta_correo = (TextView) view.findViewById(R.id.cuenta_correo);

        /*Cursor cursor = helper.ConsultarInformacionCuenta( correo_u );

        if ( cursor.moveToFirst() ) {
            cuenta_nombres.setText("Nombres: " + cursor.getString(cursor.getColumnIndex(Utilidades.CAMPO_NOMBRES_USUARIO)));
            cuenta_apellidos.setText("Apellidos: " + cursor.getString( cursor.getColumnIndex(Utilidades.CAMPO_APELLIDOS_USUARIO)));
            cuenta_genero.setText("Género: " + cursor.getString( cursor.getColumnIndex(Utilidades.CAMPO_GENERO_USUARIO)));
            cuenta_telefono.setText("Teléfono: " + cursor.getString( cursor.getColumnIndex(Utilidades.CAMPO_TELEFONO_USUARIO)));
            cuenta_correo.setText("Correo: " + cursor.getString( cursor.getColumnIndex(Utilidades.CAMPO_CORREO_USUARIO)));
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
