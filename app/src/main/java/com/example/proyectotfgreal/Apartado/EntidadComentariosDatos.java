package com.example.proyectotfgreal.Apartado;

import java.util.Date;

public class EntidadComentariosDatos {
    String fecha;
    String nombreUsuario;
    String contenidoComentario;

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContenidoComentario() {
        return contenidoComentario;
    }

    public void setContenidoComentario(String contenidoComentario) {
        this.contenidoComentario = contenidoComentario;
    }
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    EntidadComentariosDatos(String fechaR, String nombreUsuarioR, String contenidoComentarioR){
        //Recoger fecha y si es necario transformar a un formato europeo

        //Coger fecha actual
        //Date d=new Date();

        //Restar fecha sacada con actual
        fecha=fechaR;
        nombreUsuario=nombreUsuarioR;
        contenidoComentario=contenidoComentarioR;
    }


}
