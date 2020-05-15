package com.example.proyectotfgreal.Apartado;

import java.util.ArrayList;

public class EntidadApartado {
    String nombreApartado;
    String imagen;
    String identificadorApartado;
    ArrayList<EntidadSubApartado> listaSub;

    public String getNombreApartado() {
        return nombreApartado;
    }
    public void setNombreApartado(String nombreApartado) {
        this.nombreApartado = nombreApartado;
    }
    public ArrayList<EntidadSubApartado> getListaSub() {
        return listaSub;
    }
    public void setListaSub(ArrayList<EntidadSubApartado> listaSub) {
        this.listaSub = listaSub;
    }
    public String getIdentificadorApartado() {
        return identificadorApartado;
    }
    public void setIdentificadorApartado(String identificadorApartado) {
        this.identificadorApartado = identificadorApartado;
    }
    EntidadApartado(String s, String i, String imagenR) {
        if (s.contains("-")) {
            String[] lols = s.split("-");
            nombreApartado = lols[0];
            imagen = imagenR;
            EntidadSubApartado x = new EntidadSubApartado(lols[1], i, imagen);
            listaSub.add(x);
        } else {
            nombreApartado = s;
            identificadorApartado = i;
        }
    }
    public void addSubApartadoLista(EntidadSubApartado x){
        listaSub.add(x);
    }

}
