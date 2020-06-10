package com.example.proyectotfgreal.Apartado.Entidades;

public class EntidadSubApartado {
   private String nombreSubApartado;
    private String numeroIdentificadorSubApartado;
    private String imagen;
    public EntidadSubApartado(String s, String i, String imagenR){
        nombreSubApartado=s;
        numeroIdentificadorSubApartado=i;
        imagen=imagenR;
    }

    public String getNombreSubApartado() {
        return nombreSubApartado;
    }
    public String getNumeroIdentificadorSubApartado() {
        return numeroIdentificadorSubApartado;
    }
    public String getImagenSubApartado(){
        return imagen;
    }

}
