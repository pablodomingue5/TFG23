package com.example.proyectotfgreal.Apartado;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.proyectotfgreal.Apartado.Entidad;
import com.example.proyectotfgreal.R;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Adaptador.PersonViewHolder> {
    private ArrayList<Entidad> items;
    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public CardView cardView;
        public ImageView imgFoto;
        public TextView lblModelo;


        public PersonViewHolder(View v) {
            super(v);
            cardView = (CardView) v.findViewById(R.id.cardview1);
            imgFoto = (ImageView) v.findViewById(R.id.imgFoto1);
            lblModelo = (TextView) v.findViewById(R.id.txtView);

        }
    }
   @Override
    public int getItemCount() {
       if (items != null){
           return items.size();
       }else{
           return 0 ;
       }
       }


    public Adaptador(ArrayList<Entidad> items) {
        this.items = items;
    }

    public List<Entidad> getItems(){
        return this.items;
    }
    @NonNull
    @Override
    public Adaptador.PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview, parent, false);
        return new PersonViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder viewHolder,final int i) {
        //viewHolder.imagen.setImageResource(items.get(i).getImagen());
        Picasso.with(viewHolder.imgFoto.getContext())
                .load(items.get(i).getUrlImagen()).into(viewHolder.imgFoto);
        viewHolder.lblModelo.setText(items.get(i).getTitulo());
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("curImagen", items.get(i).getUrlImagen());
                bundle.putString("curNombre", items.get(i).getTitulo());
               /* Intent iconIntent = new Intent(view.getContext(), BioActivity.class);
                iconIntent.putExtras(bundle);
                view.getContext().startActivity(iconIntent);*/
            }
        });


    }
}
 /* private Context context;
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
}*/
