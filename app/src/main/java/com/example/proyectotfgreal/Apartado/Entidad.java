package com.example.proyectotfgreal.Apartado;

public class Entidad {
    private int imgFoto;
    private String titulo;
    private String urlImagen;

    public Entidad(int imgFoto,String titulo, String urlImg){
        this.imgFoto=imgFoto;
        this.titulo=titulo;
        urlImagen=urlImg;
    }
    public int getImgFoto(){
        return imgFoto;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getUrlImagen(){return urlImagen;}
}
