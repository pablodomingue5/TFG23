package com.example.proyectotfgreal.Apartado;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.proyectotfgreal.R;
import java.util.ArrayList;

public class ActivitySubApartado extends Activity {
    ArrayList <String> nombresSubApartadoRecibido;
    ArrayList <String> identicadorSubApartadoRecibido;
    ArrayList <String> imagenesSubApartadoRecibido;
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager iManager;
    public ArrayList<EntidadSubApartado> httpList= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String nombreApartadoSeleccionado = getIntent().getExtras().getString("ApartadoSeleccionadoSub");
        setTitle(nombreApartadoSeleccionado);
        setContentView(R.layout.activity1);
        recycler= findViewById(R.id.ReciclerView1);
        recycler.setHasFixedSize(true);
        iManager = new LinearLayoutManager(this);
        adapter = new AdaptadorSubapartado(this.httpList);
        recycler.setLayoutManager(iManager);
        recycler.setAdapter(adapter);
        nombresSubApartadoRecibido=getIntent().getExtras().getStringArrayList("SubApartadoNombre");
        identicadorSubApartadoRecibido=getIntent().getExtras().getStringArrayList("SubApartadoIdentificador");
        imagenesSubApartadoRecibido=getIntent().getExtras().getStringArrayList("SubApartadoImagenes");
        for(int a=0;a<nombresSubApartadoRecibido.size();a++){
            EntidadSubApartado x = new EntidadSubApartado (nombresSubApartadoRecibido.get(a), identicadorSubApartadoRecibido.get(a),imagenesSubApartadoRecibido.get(a));
            Log.d("Activity1","Datos recibidos: "+nombresSubApartadoRecibido.get(a)+" "+imagenesSubApartadoRecibido.get(a));
            httpList.add(x);
        }
    }

}

