package com.example.proyectotfgreal.Apartado;

import android.app.Activity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectotfgreal.Apartado.Adaptadores.AdaptadorAPI;
import com.example.proyectotfgreal.Apartado.Entidades.Entidad;
import com.example.proyectotfgreal.R;
import java.util.ArrayList;
import java.util.List;

public class ActivityAPI extends Activity {
    public List<Entidad> entidad=new ArrayList() ;
    private RecyclerView recycler;
    public AdaptadorAPI adaptador;
    private RecyclerView.LayoutManager iManager;
    public String numeroRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String nombreApartadoSeleccionado =getIntent().getExtras().getString("nombreApartadoSeleccionado");
        setTitle(nombreApartadoSeleccionado);
        setContentView(R.layout.activity_main);
        numeroRegistro = getIntent().getExtras().getString("numeroIdentificadorApartado");;
        recycler= findViewById(R.id.ReciclerView);
        recycler.setHasFixedSize(true);
        iManager = new LinearLayoutManager(getApplicationContext());
        recycler.setLayoutManager(iManager);
        llamarHttpClass();
    }

    private void llamarHttpClass() {
        ConsultaVehiculosApartado claes= new ConsultaVehiculosApartado(entidad,recycler, adaptador, numeroRegistro,ActivityAPI.this);
        claes.execute();
    }
}