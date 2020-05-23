package com.example.proyectotfgreal.Apartado;

import java.util.ArrayList;

public class Entidad {
    private int imgFoto;
    private String titulo;
    private String urlImagen;
    private ArrayList<EntidadApartado> arrayEntidadApartado;
    public ArrayList<EntidadSubApartado> arraySubApartado;
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

    public int getImgFoto(){
        return imgFoto;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getUrlImagen(){return urlImagen;}
    public  ArrayList <EntidadApartado> getArrayEntidadApartado(){
        return arrayEntidadApartado;
    }
}
