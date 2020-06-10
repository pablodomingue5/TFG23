package com.example.proyectotfgreal.Apartado;

import android.util.Log;
import java.util.ArrayList;

public class EntidadApartado {
    String nombreApartado;
    String imagen;
    String identificadorApartado;
    ArrayList<EntidadSubApartado> listaSub=new ArrayList<>();

    public String getNombreApartado() {
        return nombreApartado;
    }
    public ArrayList<EntidadSubApartado> getListaSub() {
        return listaSub;
    }
    public String getIdentificadorApartado() {
        return identificadorApartado;
    }

    public String getImagen(){
        return imagen;
    }
    EntidadApartado(String s, String i, String imagenR) {
        Log.d("EntidadApartado","El nombre="+s);
        if (s.contains("-")) {
            String[] lols = s.split("-");
            nombreApartado = lols[0];
            Log.d("EntidadApartado","El nombre="+nombreApartado);
            imagen = imagenR;
            EntidadSubApartado x = new EntidadSubApartado(lols[1], i, imagen);
            listaSub.add(x);
        } else {
            nombreApartado = s;
            Log.d("EntidadApartado","El nombre NO SPLITEADO="+nombreApartado);
            imagen = imagenR;
            identificadorApartado = i;
        }
    }
    public void addSubApartadoLista(EntidadSubApartado x){
        listaSub.add(x);
    }

}
