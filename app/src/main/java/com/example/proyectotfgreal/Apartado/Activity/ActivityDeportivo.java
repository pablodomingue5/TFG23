package com.example.proyectotfgreal.Apartado.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.proyectotfgreal.Apartado.Adaptador.Adaptador;
import com.example.proyectotfgreal.Apartado.Entidad;
import com.example.proyectotfgreal.R;

import java.util.ArrayList;

public class ActivityDeportivo extends AppCompatActivity {
    private ListView lvItems;
    private Adaptador adaptador;
    public ImageView imgView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Deportivos");
        setContentView(R.layout.activity_deportivo);
        imgView1 = (ImageView)findViewById(R.id.imgFoto);

        lvItems=(ListView) findViewById(R.id.LvItems);

        adaptador = new Adaptador(this,GetArrayItems());
        lvItems.setAdapter(adaptador);
    }

    private ArrayList<Entidad> GetArrayItems() {
        ArrayList<Entidad> listItems = new ArrayList<>();
        listItems.add(new Entidad(R.drawable.ic_launcher_background,"Compacto Deportivo","https://i.ibb.co/y5SGLJs/21-Fiat-Abarth-595-Competizione.jpg"));
        listItems.add(new Entidad(R.drawable.ic_launcher_background,"Deportivo","https://i.ibb.co/qx9dhkz/3-Mazda-RX7-III.jpg"));
        listItems.add(new Entidad(R.drawable.ic_launcher_background,"Gran Turismo","https://i.ibb.co/52xT43D/37-Ferrari-612-Scaglietti.jpg"));
        listItems.add(new Entidad(R.drawable.ic_launcher_background,"Superdeportivo","https://i.ibb.co/C7MPdFp/28-Lamborghini-Diablo-VT.jpg"));
        listItems.add(new Entidad(R.drawable.ic_launcher_background,"Muscle","https://i.ibb.co/m4Knb9F/10-Ford-Mustang-VI.jpg"));
        listItems.add(new Entidad(R.drawable.ic_launcher_background,"Descapotable","https://i.ibb.co/t8shm8K/1-Mazda-MX-5-III.jpg"));
        return listItems;
    }
}
