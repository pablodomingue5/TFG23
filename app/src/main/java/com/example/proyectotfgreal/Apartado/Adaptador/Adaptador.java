package com.example.proyectotfgreal.Apartado.Adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.proyectotfgreal.Apartado.Entidad;
import com.example.proyectotfgreal.R;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {
  private Context context;
  private ArrayList<Entidad> listItems;
  private String urlCosa;

    public Adaptador(Context context, ArrayList<Entidad> listItems) {
        this.context = context;
        this.listItems = listItems;
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int position) {
        return listItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       Entidad Item = (Entidad) getItem(position);

       convertView= LayoutInflater.from(context).inflate(R.layout.item,null);
        ImageView imgFoto = (ImageView) convertView.findViewById(R.id.imgFoto);
        try {
            String EDteamImage = Item.getUrlImagen();
            Glide.with(context)
                    .load(EDteamImage)
                    .into(imgFoto);
        }
        catch(NullPointerException e){
            Toast.makeText(context,"NULL POINT",Toast.LENGTH_LONG).show();
        }
        TextView Titulo = (TextView) convertView.findViewById(R.id.Titulo);
        //TextView Contenido = (TextView) convertView.findViewById(R.id.Contenido);

        imgFoto.setImageResource(Item.getImgFoto());
        Titulo.setText(Item.getTitulo());
        //Contenido.setText((Item.getTitulo()));
        return convertView;
    }
}
