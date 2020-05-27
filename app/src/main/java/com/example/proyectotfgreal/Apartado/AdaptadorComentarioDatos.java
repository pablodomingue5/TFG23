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

public class AdaptadorComentarioDatos extends RecyclerView.Adapter<AdaptadorComentarioDatos.PersonViewHolder>  {
    private List<EntidadComentariosDatos> items;
    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public CardView cardview2;
        public TextView txtNombre;
        public TextView txtFecha;
        public TextView txtComentario;

        public PersonViewHolder(View v) {
            super(v);
            cardview2 = (CardView) v.findViewById(R.id.cardview2);
            txtNombre = (TextView) v.findViewById(R.id.txtNombre);
            txtFecha = (TextView) v.findViewById(R.id.txtFecha);
            txtComentario = (TextView )v.findViewById(R.id.txtComentario);

        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public AdaptadorComentarioDatos(List<EntidadComentariosDatos> items) {
        this.items = items;
    }

    public List<EntidadComentariosDatos> getItems(){
        return this.items;
    }

    @NonNull
    @Override
    public AdaptadorComentarioDatos.PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_comentarios, parent, false);
        return new AdaptadorComentarioDatos.PersonViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AdaptadorComentarioDatos.PersonViewHolder viewHolder, final int i) {
        final String fecha=items.get(i).getFecha();
        final String nombreUsuario=items.get(i).getNombreUsuario();
        final String contenidoComentario=items.get(i).getContenidoComentario();
       // Log.d("OnBindViewHolder",imagenSeleccionada);
        //Picasso.with(viewHolder.imgFoto.getContext())
          //      .load(imagenSeleccionada).resize(370,230).into(viewHolder.imgFoto);
        viewHolder.txtNombre.setText(nombreUsuario);
        viewHolder.txtFecha.setText(fecha);
        viewHolder.txtComentario.setText(contenidoComentario);
        viewHolder.cardview2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Cosa de Testing la mantengo para el futuro por si se quisiera hacer algo raro
               /* Intent iconIntent = new Intent(view.getContext(), ActivityDatos.class);
               // iconIntent.putExtra("NumeroSeleccionado",numeroSeleccionado);
                view.getContext().startActivity(iconIntent);*/
            }
        });
    }
}

