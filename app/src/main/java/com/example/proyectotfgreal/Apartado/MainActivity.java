package com.example.proyectotfgreal.Apartado;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.proyectotfgreal.Apartado.Activity.ActivityDeportivo;
import com.example.proyectotfgreal.Apartado.Activity.ActivityLujo;
import com.example.proyectotfgreal.Apartado.Activity.ActivityTodocaminos;
import com.example.proyectotfgreal.Apartado.Adaptador.Adaptador;
import com.example.proyectotfgreal.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView lvItems;
    private Adaptador adaptador;
    public ImageView imgView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Tipos de Coches");
        setContentView(R.layout.activity_main);
        imgView1 = (ImageView)findViewById(R.id.imgFoto);

        lvItems=(ListView) findViewById(R.id.LvItems);

        adaptador = new Adaptador(this,GetArrayItems());
        lvItems.setAdapter(adaptador);
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        Intent intentLujo = new Intent(MainActivity.this, ActivityLujo.class);
                       startActivity(intentLujo);
                        break;
                    case 4:
                        Intent intentDeportivo = new Intent(MainActivity.this, ActivityDeportivo.class);
                        startActivity(intentDeportivo);
                        break;
                    case 5:
                        Intent intentTodoterreno = new Intent(MainActivity.this, ActivityTodocaminos.class);
                        startActivity(intentTodoterreno);
                        break;

                }
            }
        });
    }

    private ArrayList<Entidad> GetArrayItems() {
        ArrayList<Entidad> listItems = new ArrayList<>();
        listItems.add(new Entidad(R.drawable.ic_launcher_background,"MINICOMPACTO","https://i.ibb.co/y5SGLJs/21-Fiat-Abarth-595-Competizione.jpg"));
        listItems.add(new Entidad(R.drawable.ic_launcher_background,"SUBCOMPACTO","https://i.ibb.co/Xz7v37R/19-Renault-Clio-IV.jpg"));
        listItems.add(new Entidad(R.drawable.ic_launcher_background,"FAMILIAR","https://i.ibb.co/16X6ygg/31-Renault-Scenic-IV.jpg"));
        listItems.add(new Entidad(R.drawable.ic_launcher_background,"VEHICULOS DE LUJO","https://i.ibb.co/qrWYNFh/6-Mercedes-Clase-E-Coupe.jpg"));
        listItems.add(new Entidad(R.drawable.ic_launcher_background,"DEPORTIVOS","https://i.ibb.co/qx9dhkz/3-Mazda-RX7-III.jpg"));
        listItems.add(new Entidad(R.drawable.ic_launcher_background,"TODOCAMINOS","https://i.ibb.co/mvXcKjr/32-Honda-CRV-IIIfacelift2010.jpg"));

        /*
        Esto se manejará como segundo apartado
        listItems.add(new Entidad(R.drawable.ic_launcher_background,"Rally",""));
        listItems.add(new Entidad(R.drawable.ic_launcher_background,"Circuitos",""));
        listItems.add(new Entidad(R.drawable.ic_launcher_background,"Autocross",""));
        listItems.add(new Entidad(R.drawable.ic_launcher_background,"Rallycross",""));
        listItems.add(new Entidad(R.drawable.ic_launcher_background,"Drifting","https://i.ibb.co/qx9dhkz/3-Mazda-RX7-III.jpg"));
        listItems.add(new Entidad(R.drawable.ic_launcher_background,"Hill climbing",""));
         */
        return listItems;
    }
}
