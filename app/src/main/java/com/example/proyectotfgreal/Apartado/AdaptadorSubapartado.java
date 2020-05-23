package com.example.proyectotfgreal.Apartado;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectotfgreal.R;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class AdaptadorSubapartado extends RecyclerView.Adapter<AdaptadorSubapartado.PersonViewHolder> {

    private ArrayList<EntidadSubApartado> items;
    MainActivity mainActivity;
    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        private CardView cardView;
        private ImageView imgFoto;
        private TextView lblModelo;
        public Context context;

        private PersonViewHolder(View v) {
            super(v);
            cardView =  v.findViewById(R.id.cardview1);
            imgFoto = v.findViewById(R.id.imgFoto1);
            lblModelo =  v.findViewById(R.id.txtView);

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


    public AdaptadorSubapartado(ArrayList<EntidadSubApartado> items) {
        this.items = items;
    }

    public ArrayList<EntidadSubApartado> getItems(){
        return this.items;
    }
    @NonNull
    @Override
    public AdaptadorSubapartado.PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        return new AdaptadorSubapartado.PersonViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdaptadorSubapartado.PersonViewHolder viewHolder, final int i) {
        Picasso.with(viewHolder.imgFoto.getContext())
                .load(items.get(i).getImagenSubApartado()).resize(370,230).into(viewHolder.imgFoto);
        viewHolder.lblModelo.setText(items.get(i).
    getNombreSubApartado());
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombreSubApartadoElegido=items.get(i).getNombreSubApartado();
                String numeroElegido = items.get(i).getNumeroIdentificadorSubApartado();
                Toast.makeText(view.getContext(), "Clicaste"+items.get(i).getNombreSubApartado(),Toast.LENGTH_SHORT).show();
                Intent iconIntent = new Intent (view.getContext(), ActivityAPI.class);
                iconIntent.putExtra("nomnbreApartadoSeleccionado",nombreSubApartadoElegido);
                iconIntent.putExtra("numeroIdentificadorApartado",numeroElegido);
                view.getContext().startActivity(iconIntent);
            }
        });
    }
}

