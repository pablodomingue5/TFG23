package com.example.proyectotfgreal.Apartado.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.proyectotfgreal.Apartado.Adaptador.Adaptador;
import com.example.proyectotfgreal.Apartado.Entidad;
import com.example.proyectotfgreal.R;

import java.util.ArrayList;

public class ActivityTodocaminos extends AppCompatActivity {
    private ListView lvItems;
    private Adaptador adaptador;
    public ImageView imgView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todocaminos);
        setTitle("Todocaminos");
        imgView1 = (ImageView)findViewById(R.id.imgFoto);
        lvItems=(ListView) findViewById(R.id.LvItems);
        adaptador = new Adaptador(this,GetArrayItems());
        lvItems.setAdapter(adaptador);
    }
    private ArrayList<Entidad> GetArrayItems() {
        ArrayList<Entidad> listItems = new ArrayList<>();
        listItems.add(new Entidad(R.drawable.ic_launcher_background,"SUV","https://i.ibb.co/mvXcKjr/32-Honda-CRV-IIIfacelift2010.jpg"));
        listItems.add(new Entidad(R.drawable.ic_launcher_background,"Todoterreno","https://i.ibb.co/51J3LDz/33-Toyota-Land-Cruiser-Prado.jpg"));
        return listItems;
    }
}
