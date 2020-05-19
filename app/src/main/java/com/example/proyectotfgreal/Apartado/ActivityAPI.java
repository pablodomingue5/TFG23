package com.example.proyectotfgreal.Apartado;

import android.app.Activity;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectotfgreal.R;

import java.util.ArrayList;
import java.util.List;

public class ActivityAPI extends Activity {
    public List<Entidad> entidad=new ArrayList() ;


    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private Adaptador adaptador;
    private RecyclerView.LayoutManager iManager;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Tipos de Coches");
        setContentView(R.layout.activity_main);

        recycler= (RecyclerView) findViewById(R.id.ReciclerView);
        recycler.setHasFixedSize(true);

        iManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(iManager);
        // recycler.setAdapter(adapter);
        llamarHttpClass();

    }

    private void llamarHttpClass() {
        GetHTTPApi claes= new GetHTTPApi(entidad,recycler, adapter,ActivityAPI.this);
        claes.execute();
    }


}