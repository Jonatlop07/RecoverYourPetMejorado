package com.example.jonatlop.recoveryourpet.Ventanas;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jonatlop.recoveryourpet.Entidades.PerfilMascota;
import com.example.jonatlop.recoveryourpet.R;
import com.example.jonatlop.recoveryourpet.utilidades.Adaptador;
import com.example.jonatlop.recoveryourpet.utilidades.Utilidades;

import java.util.ArrayList;

import OpenHelper.BaseDatosSQLite;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentPerfiles.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentPerfiles#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentPerfiles extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FragmentPerfiles() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentPerfiles.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentPerfiles newInstance(String param1, String param2) {
        FragmentPerfiles fragment = new FragmentPerfiles();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    private BaseDatosSQLite helper = new BaseDatosSQLite( getContext(), "BD_Mascotas", null, 1 );
    private FragmentVerPerfiles fragmentVerPerfiles;
    private String correo;
    private ListView lv_perfiles;
    private Adaptador adaptador;
    private PerfilMascota perfil_mascota;
    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState ) {
        view = inflater.inflate( R.layout.fragment_perfiles, container, false );

        fragmentVerPerfiles = new FragmentVerPerfiles();

        correo = getArguments().getString("correo");

        lv_perfiles = (ListView) view.findViewById( R.id.lista_mis_perfiles );

        /*Cursor cursor = helper.ConsultarMisPerfilesCreados( correo );

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
            Toast.makeText( getActivity(), "No has creado un perfil de mascota hasta el momento",
                    Toast.LENGTH_LONG ).show();
        }

        lv_perfiles.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick( AdapterView<?> parent, View view, int position, long id ) {
                Bundle args = new Bundle();
                args.putSerializable( "objetoPerfil", lista_perfiles.get( position ) );

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                fragmentVerPerfiles.setArguments( args );
                transaction.replace( R.id.contenedorFragment, fragmentVerPerfiles );
            }
        });*/

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
