package com.example.proyectotfgreal.Apartado.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.proyectotfgreal.Apartado.Adaptador.Adaptador;
import com.example.proyectotfgreal.Apartado.Entidad;
import com.example.proyectotfgreal.R;

import java.util.ArrayList;

public class ActivityLujo extends AppCompatActivity {
    private ListView lvItems;
    private Adaptador adaptador;
    public ImageView imgView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Coches de Lujo");
        setContentView(R.layout.activity_lujo);
        imgView1 = (ImageView)findViewById(R.id.imgFoto);
        lvItems=(ListView) findViewById(R.id.LvItems);
        adaptador = new Adaptador(this,GetArrayItems());
        lvItems.setAdapter(adaptador);
    }
    private ArrayList<Entidad> GetArrayItems() {
        ArrayList<Entidad> listItems = new ArrayList<>();
        listItems.add(new Entidad(R.drawable.ic_launcher_background,"Compacto Premium","https://i.ibb.co/qWszXxs/34-Audi-A3-8-V.png"));
        listItems.add(new Entidad(R.drawable.ic_launcher_background,"Ejecutivos","https://i.ibb.co/drLCckD/35-BMW-Serie-7-G11.jpg"));
        listItems.add(new Entidad(R.drawable.ic_launcher_background,"Berlina de Lujo","https://i.ibb.co/LnJ4qGT/5-BMW-Serie5-F10-MLCI.jpg"));
        listItems.add(new Entidad(R.drawable.ic_launcher_background,"Ranchera","https://i.ibb.co/f0KH9s9/36-Mercedes-Benz-CLS-Shooting-Brake.jpg"));
        return listItems;
    }
}
