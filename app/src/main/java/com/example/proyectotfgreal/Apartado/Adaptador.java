package com.example.proyectotfgreal.Apartado;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.ContextThemeWrapper;
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
import com.example.proyectotfgreal.Apartado.Activity.Activity1;
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
        private CardView cardView;
        private ImageView imgFoto;
        private TextView lblModelo;

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
    public void onBindViewHolder (@NonNull final PersonViewHolder viewHolder, final int i) {
        //viewHolder.imagen.setImageResource(items.get(i).getImagen());
        final ArrayList<EntidadApartado>todosApartados = items.get(i).getArrayEntidadApartado();

        Picasso.with(viewHolder.imgFoto.getContext())
                .load(items.get(i).getUrlImagen()).resize(370,230).into(viewHolder.imgFoto);
        viewHolder.lblModelo.setText(items.get(i).getTitulo());
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Clicaste"+items.get(i).getTitulo(),Toast.LENGTH_SHORT).show();
                Bundle bundle = new Bundle();
                bundle.putString("curImagen", items.get(i).getUrlImagen());
                bundle.putString("curNombre", items.get(i).getTitulo());
                for(int a=0; a<todosApartados.size();a++){
                    if(items.get(i).getTitulo().equals(todosApartados.get(a).getNombreApartado())){
                        if(todosApartados.get(a).getListaSub().size()==0){
                            //Aqui
                        }else{
                            ArrayList <EntidadSubApartado> subApartadosDelSeleccionado =  todosApartados.get(a).getListaSub();
                            ArrayList<String> nombresApartados=new ArrayList<>();
                            ArrayList<String> identificadores=new ArrayList<>();
                            ArrayList<String> imagenes=new ArrayList<>();
                            for (EntidadSubApartado subApartado: subApartadosDelSeleccionado) {
                                nombresApartados.add(subApartado.getNombreSubApartado());
                                Log.d("AdaptadorListener","El nombre del subApartado en la lista es "+subApartado.getNombreSubApartado());
                                identificadores.add(subApartado.getNumeroIdentificadorSubApartado());
                                imagenes.add(subApartado.getImagenSubApartado());
                            }
                            Intent iconIntent = new Intent (view.getContext(), Activity1.class);
                            Log.d("AdaptadorListener","Llego a llenar");
                            iconIntent.putStringArrayListExtra("SubApartadoNombre",nombresApartados);
                            Log.d("AdaptadorListener","Introducio nombres");
                            iconIntent.putStringArrayListExtra("SubApartadoIdentificador",identificadores);
                            iconIntent.putStringArrayListExtra("SubApartadoImagenes",imagenes);
                            view.getContext().startActivity(iconIntent);


                            }
                        }
                    }
                }

                /*
                if (items.get(i).getTitulo().equals("Minicompacto")){
                    Toast.makeText(view.getContext(), "HOLIXXX "+items.get(i).getTitulo(),Toast.LENGTH_SHORT).show();
                    Intent iconIntent = new Intent (view.getContext(), Activity1.class);
                    view.getContext().startActivity(iconIntent);
                } else{

                }
                */


               /* Intent iconIntent = new Intent(view.getContext(), BioActivity.class);
                iconIntent.putExtras(bundle);
                view.getContext().startActivity(iconIntent);*/



        })
   ; }

}

