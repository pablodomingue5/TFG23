package com.example.proyectotfgreal.Apartado;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MandandoCosa {
    private String comentario;
    private String numeroModelo;
    private String fechaActual;
    public MandandoCosa(String comentario, String numeroModelo){
        this.comentario=comentario;
        this.numeroModelo=numeroModelo;
        //fecha creado automaticamente con el formato AAAA-MM-DD
        this.fechaActual=metodoFechaActual();
    }
    private String metodoFechaActual() {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        return timeStamp;
    }
    public String getNumeroModelo() {
        return numeroModelo;
    }

    public String getComentario() {
        return comentario;
    }

    public String getFechaActual() {
        return fechaActual;
    }

}
