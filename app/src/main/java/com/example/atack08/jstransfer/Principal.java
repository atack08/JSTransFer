package com.example.atack08.jstransfer;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Principal extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ListView listaDesplegable;
    private String[] opcionesMenu;
    private String  mTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        opcionesMenu = new String[]{"Marte","Jupiter","Venus","Plutón","Saturno","Mercurio"};
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        listaDesplegable = (ListView) findViewById(R.id.left_drawer);

        //Creamos el adaptador y lo asignamos a la lista
        listaDesplegable.setAdapter(new ArrayAdapter<String>(this,R.layout.drawer_list_item, opcionesMenu));


        //Creamos el listener para la lista
        listaDesplegable.setOnItemClickListener(new DrawerItemClickListener());

    }


    //CLASE INTERNA PARA EL LISTENER DEL DRAWER
    private class DrawerItemClickListener implements ListView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

            seleccionarOpcion(position);
        }
    }

    //MÉTODO PARA CAMBIAR LOS FRAGMENTS
    private void seleccionarOpcion(int posicion){

        //CREAMOS FRAGMENT NUEVO Y ESPECIFICAMOS LA OPCION PARA ESA POSICION
        Fragment fragment = new OptionFragment();

        Bundle args = new Bundle();
        args.putInt(OptionFragment.ARG_PLANET_NUMBER, posicion);
        fragment.setArguments(args);

        //INSERTAMOS EL FRAGMENT REMPLAZANDO AL FRAGMENT EXISTENTE
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();


       // ACTUALIZAMOS EL TÍTULO DE LA SELECCION DE LA LISTA
        //CERRAMOS EL DRAWER
        listaDesplegable.setItemChecked(posicion,true);
        setTitle(opcionesMenu[posicion]);
        mDrawerLayout.closeDrawer(listaDesplegable);

    }


    //CLASE PRIVADA PARA LOS FRAGMENTS
    public static class OptionFragment extends Fragment {
        public static final String ARG_PLANET_NUMBER = "planet_number";

        public OptionFragment() {
            // Empty constructor required for fragment subclasses
        }
    }

    public void cambiarTitulo (String title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }


}
