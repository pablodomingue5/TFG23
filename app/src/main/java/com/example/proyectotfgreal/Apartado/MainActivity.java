package com.example.proyectotfgreal.Apartado;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.proyectotfgreal.R;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public ArrayList<Entidad> entidad=new ArrayList() ;
    private Adaptador adaptador;
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager iManager;
    SharedPreferences sharedPreferences;
    String ipSeleccionada;
    String numeroUsuarioSeleccionado;
    //
    Boolean activoDialog=false;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Tipos de Coches");
        //shared preferences son las preferencias puedes quitarlo
       // sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        //String valorUsuario=sharedPreferences.getString("UsuarioConectado","");
        //if(valorUsuario==""){
            //Aqui se llama al codigo de opendialog, junto al activoDialog son innecesdairos

          //  activoDialog=true;
       // }
        //
        setContentView(R.layout.activity_main);
        recycler= (RecyclerView) findViewById(R.id.ReciclerView);
        recycler.setHasFixedSize(true);
        iManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(iManager);
        llamarHttpClass();

    }
    private void llamarHttpClass() {
        GetHTTPApartados claes= new GetHTTPApartados(entidad,recycler, adapter,MainActivity.this);
        claes.execute();
    }

}