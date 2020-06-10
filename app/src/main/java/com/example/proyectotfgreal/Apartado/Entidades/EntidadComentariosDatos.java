package com.example.proyectotfgreal.Apartado.Entidades;

public class EntidadComentariosDatos {
    String fecha;
    String nombreUsuario;
    String contenidoComentario;

    public String getNombreUsuario() {
        return nombreUsuario;
    }
    public String getContenidoComentario() {
        return contenidoComentario;
    }
    public String getFecha() {
        return fecha;
    }

    public EntidadComentariosDatos(String fechaR, String nombreUsuarioR, String contenidoComentarioR){
        fecha=fechaR;
        nombreUsuario=nombreUsuarioR;
        contenidoComentario=contenidoComentarioR;
    }


}
