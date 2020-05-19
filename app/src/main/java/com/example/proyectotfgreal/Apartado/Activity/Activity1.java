package com.example.proyectotfgreal.Apartado.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectotfgreal.Apartado.Adaptador;
import com.example.proyectotfgreal.Apartado.AdaptadorSubapartado;
import com.example.proyectotfgreal.Apartado.Entidad;
import com.example.proyectotfgreal.Apartado.EntidadSubApartado;
import com.example.proyectotfgreal.Apartado.GetHTTPApartados;
import com.example.proyectotfgreal.Apartado.MainActivity;
import com.example.proyectotfgreal.R;

import java.util.ArrayList;

public class Activity1 extends Activity {
    public ArrayList<EntidadSubApartado> entidad=new ArrayList() ;
    private ListView lvItems;
    private Adaptador adaptador;
    public ImageView imgView1;
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager iManager;

    public ArrayList<EntidadSubApartado> httpList= new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Tipos de Coches");

        setContentView(R.layout.activity1);
        recycler= (RecyclerView) findViewById(R.id.ReciclerView1);
        recycler.setHasFixedSize(true);
        iManager = new LinearLayoutManager(this);
/*
        ArrayList <String> nombresSubApartadoRecibido=getIntent().getExtras().getStringArrayList("SubApartadoNombre");
        ArrayList <String> identicadorSubApartadoRecibido=getIntent().getExtras().getStringArrayList("SubApartadoIdentificador");
        ArrayList <String> imagenesSubApartadoRecibido=getIntent().getExtras().getStringArrayList("SubApartadoImagenes");
        for(int a=0;a<nombresSubApartadoRecibido.size();a++){
            EntidadSubApartado x = new EntidadSubApartado (nombresSubApartadoRecibido.get(a), identicadorSubApartadoRecibido.get(a),imagenesSubApartadoRecibido.get(a));
            Log.d("Activity1","Datos recibidos: "+nombresSubApartadoRecibido.get(a)+" "+imagenesSubApartadoRecibido.get(a));
            httpList.add(x);
        }*/
        adapter = new AdaptadorSubapartado(this.httpList);
        recycler.setLayoutManager(iManager);
        recycler.setAdapter(adapter);
        ArrayList <String> nombresSubApartadoRecibido=getIntent().getExtras().getStringArrayList("SubApartadoNombre");
        ArrayList <String> identicadorSubApartadoRecibido=getIntent().getExtras().getStringArrayList("SubApartadoIdentificador");
        ArrayList <String> imagenesSubApartadoRecibido=getIntent().getExtras().getStringArrayList("SubApartadoImagenes");
        for(int a=0;a<nombresSubApartadoRecibido.size();a++){
            EntidadSubApartado x = new EntidadSubApartado (nombresSubApartadoRecibido.get(a), identicadorSubApartadoRecibido.get(a),imagenesSubApartadoRecibido.get(a));
            Log.d("Activity1","Datos recibidos: "+nombresSubApartadoRecibido.get(a)+" "+imagenesSubApartadoRecibido.get(a));
            httpList.add(x);
        }
    }

}

