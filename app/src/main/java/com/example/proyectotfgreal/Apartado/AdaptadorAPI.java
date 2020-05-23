package com.example.proyectotfgreal.Apartado;

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

    @Override
    public void onBindViewHolder(PersonViewHolder viewHolder, final int i) {
        final String nombreTitulo=items.get(i).getTitulo();
        Picasso.with(viewHolder.imgFoto.getContext())
                .load(items.get(i).getUrlImagen()).into(viewHolder.imgFoto);
        viewHolder.lblModelo.setText(items.get(i).getTitulo());
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Cosa de Testing la mantengo para el futuro por si se quisiera hacer algo raro
                Toast.makeText(view.getContext(), "Clicaste en "+nombreTitulo,Toast.LENGTH_SHORT).show();

                // Intent iconIntent = new Intent(view.getContext(), BioActivity.class);
                //  iconIntent.putExtras(bundle);
                //  view.getContext().startActivity(iconIntent);
            }
        });
    }
}

