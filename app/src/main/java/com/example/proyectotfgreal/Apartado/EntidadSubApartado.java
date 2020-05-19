package com.example.proyectotfgreal.Apartado;

public class EntidadSubApartado {
   private String nombreSubApartado;
    private String numeroIdentificadorSubApartado;
    private String imagen;
    public String getNombreSubApartado() {
        return nombreSubApartado;
    }
    public void setNombreSubApartado(String nombreSubApartado) {
        this.nombreSubApartado = nombreSubApartado;
    }
    public String getNumeroIdentificadorSubApartado() {
        return numeroIdentificadorSubApartado;
    }
    public void setNumeroIdentificadorSubApartado(String numeroIdentificadorSubApartado) {
        this.numeroIdentificadorSubApartado = numeroIdentificadorSubApartado;
    }
    public String getImagenSubApartado(){
        return imagen;
    }

    public EntidadSubApartado(String s, String i, String imagenR){
        nombreSubApartado=s;
        numeroIdentificadorSubApartado=i;
        imagen=imagenR;
    }
}
