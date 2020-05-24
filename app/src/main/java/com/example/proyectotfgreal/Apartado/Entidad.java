package com.example.proyectotfgreal.Apartado;

import java.util.ArrayList;

public class Entidad {
    private String titulo;
    private String urlImagen;
    private ArrayList<EntidadApartado> arrayEntidadApartado;
    public ArrayList<EntidadSubApartado> arraySubApartado;
    public int numeroId;
    public Entidad(String tituloM, String urlImg){
        // this.imgFoto=imgFoto;
        titulo=tituloM;
        urlImagen=urlImg;
    }

    public Entidad(String tituloM, String urlImg, ArrayList<EntidadApartado> arrayEntidadApartadoM){
       // this.imgFoto=imgFoto;
        titulo=tituloM;
        urlImagen=urlImg;
        arrayEntidadApartado=arrayEntidadApartadoM;
    }

    public Entidad(String tituloM, String urlModelo, int idModelo) {
        titulo=tituloM;
        urlImagen=urlModelo;
        numeroId=idModelo;
    }
    public String getTitulo() {
        return titulo;
    }

    public String getUrlImagen(){return urlImagen;}
    public  ArrayList <EntidadApartado> getArrayEntidadApartado(){
        return arrayEntidadApartado;
    }

    public int getNumeroId() {
        return numeroId;
    }
}
