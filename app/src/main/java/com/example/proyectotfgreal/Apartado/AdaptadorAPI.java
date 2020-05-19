package com.example.proyectotfgreal.Apartado;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectotfgreal.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdaptadorAPI extends RecyclerView.Adapter<AdaptadorAPI.PersonViewHolder>  {
    private List<Entidad> items;

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
        return items.size();
    }

    public AdaptadorAPI(List<Entidad> items) {
        this.items = items;
    }

    public List<Entidad> getItems(){
        return this.items;
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview, parent, false);
        return new PersonViewHolder(v);

    }

    // @Override


    public void onBindViewHolder(PersonViewHolder viewHolder, final int i) {
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
                // Intent iconIntent = new Intent(view.getContext(), BioActivity.class);
                //  iconIntent.putExtras(bundle);
                //  view.getContext().startActivity(iconIntent);
            }
        });


    }
}

