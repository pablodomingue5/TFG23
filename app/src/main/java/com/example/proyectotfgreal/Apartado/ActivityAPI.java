package com.example.proyectotfgreal.Apartado;

import android.app.Activity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.proyectotfgreal.R;
import java.util.ArrayList;
import java.util.List;

public class ActivityAPI extends Activity {
    public List<Entidad> entidad=new ArrayList() ;
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private AdaptadorAPI adaptador;
    private RecyclerView.LayoutManager iManager;
    public String numeroRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String nombreApartadoSeleccionado =getIntent().getExtras().getString("nombreApartadoSeleccionado");
        setTitle(nombreApartadoSeleccionado);
        setContentView(R.layout.activity_main);
        String numeroIdentificadorApartado =getIntent().getExtras().getString("numeroIdentificadorApartado");
        numeroRegistro = numeroIdentificadorApartado;
        recycler= (RecyclerView) findViewById(R.id.ReciclerView);
        recycler.setHasFixedSize(true);
        iManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(iManager);
        llamarHttpClass();
    }

    private void llamarHttpClass() {
        GetHTTPApi claes= new GetHTTPApi(entidad,recycler, adapter, numeroRegistro,ActivityAPI.this);
        claes.execute();
    }


}