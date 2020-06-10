package com.example.proyectotfgreal.Apartado;

import android.content.Intent;
import android.util.Log;
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
            cardView = v.findViewById(R.id.cardview1);
            imgFoto = v.findViewById(R.id.imgFoto1);
            lblModelo = v.findViewById(R.id.txtComentario);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public AdaptadorAPI(List<Entidad> itemsR) {
        items = itemsR;
    }

    public List<Entidad> getItems(){
        return items;
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview, parent, false);
        return new PersonViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PersonViewHolder viewHolder, final int i) {
        final int numeroSeleccionado=items.get(i).getNumeroId();
        final String nombreSeleccionado=items.get(i).getTitulo();
        final String imagenSeleccionada=items.get(i).getUrlImagen();
        Log.d("OnBindViewHolder",imagenSeleccionada);
        Picasso.with(viewHolder.imgFoto.getContext())
                .load(imagenSeleccionada).resize(370,230).into(viewHolder.imgFoto);
        viewHolder.lblModelo.setText(nombreSeleccionado);
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iconIntent = new Intent(view.getContext(), ActivityDatos.class);
                iconIntent.putExtra("NumeroSeleccionado",numeroSeleccionado);
                view.getContext().startActivity(iconIntent);
            }
        });
    }
}

