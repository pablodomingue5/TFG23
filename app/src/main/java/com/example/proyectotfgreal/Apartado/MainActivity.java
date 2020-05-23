package com.example.proyectotfgreal.Apartado;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import com.example.proyectotfgreal.R;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public ArrayList<Entidad> entidad=new ArrayList() ;
    private Adaptador adaptador;
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
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
        llamarHttpClass();
    }
    private void llamarHttpClass() {
        GetHTTPApartados claes= new GetHTTPApartados(entidad,recycler, adapter,MainActivity.this);
        claes.execute();
    }
}