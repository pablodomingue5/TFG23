package com.example.proyectotfgreal.Apartado;

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

    EntidadComentariosDatos(String fechaR, String nombreUsuarioR, String contenidoComentarioR){
        fecha=fechaR;
        nombreUsuario=nombreUsuarioR;
        contenidoComentario=contenidoComentarioR;
    }


}
