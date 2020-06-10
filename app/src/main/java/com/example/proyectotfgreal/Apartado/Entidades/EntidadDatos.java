package com.example.proyectotfgreal.Apartado.Entidades;

import android.util.Log;

public class EntidadDatos {

    public String imgFoto;
    public String nombreModelo;
    public String marca;
    public String ano;
    public String hp;
    public String motor;
    public String tipoCombustible;
    public String traccion;
    public String transmision;
    public String marchas;
    public String velocidadMaxima;
    public String cilindrada;
    public String cuerpo;
    public String version;

    public EntidadDatos(String imgFotoR,String modeloR, String serie,String marcaR,String fechaR, String hpR,
                   String nombreMotorR, String combustileR, String traccionR, String transmisionCompeltaR,
                   String velocidadMaximaR, String cilindradaR, String cuerpoR, String versionR)
    {
        Log.d("EntidadesCreadas", "Entrado en entidad credada ");
        imgFoto=imgFotoR;
        nombreModelo =serie+" "+modeloR;
        marca =marcaR;
        ano =spliteameEsaF(fechaR);
        hp = hpR;
        motor=nombreMotorR;
        Log.d("EntidadesCreadas", "Mitad en entidad credada ");
        tipoCombustible =seleccionCombustible(combustileR);
        Log.d("EntidadesCreadas", "seleccionCombustible ");
        traccion =seleccionTraccion(traccionR);
        Log.d("EntidadesCreadas", "seleccionTraccion ");
        transmision =splitTransmision(transmisionCompeltaR);
        Log.d("EntidadesCreadas", "splitTransmision ");
        marchas=splitMarcha(transmisionCompeltaR);
        Log.d("EntidadesCreadas", "splitMarcha ");
        velocidadMaxima=velocidadMaximaR;
        Log.d("EntidadesCreadas", "Segunda mitad en entidad credada ");
        cilindrada=cilindradaR;
        cuerpo=cuerpoR;
        if(versionR.equals("null")){
            version="";
        }else {
            version = versionR;
        }
        Log.d("EntidadesCreadas", "Creado entidad "+getNombreModelo());

    }

    public String spliteameEsaF(String fecha){
        String [] fechaCompleta=fecha.split("-");
        String result = fechaCompleta[0];
        return result;
    }
    public String splitTransmision(String transmision){
        String [] transmisionCompleta=transmision.split("_");
        String result = transmisionCompleta[0];
        Log.d("EntidadesCreadas", "splitTransmision : "+result);
        return result;
    }

    public String splitMarcha(String transmision){
        String [] transmisionCompleta=transmision.split("_");
        String result = transmisionCompleta[1];
        return result;
    }

    public String seleccionCombustible(String combustible){
        String result="";
        switch(combustible){
            case "Gasolina":
                result="G";
                break;
            case "Diesel":
                result="D";
                break;
            case "Electrico":
                result="E";
                break;
            case "Gasolina-Electricidad":
                result="G/E";
                break;
        }
        return result;
    }

    public String seleccionTraccion(String traccion){
        String result="";
        switch(traccion){
            case "4x4":
                result="4x4";
                break;
            case "Trasera":
                result="T";
                break;
            case "Delantera":
                result="D";
                break;
        }
        return result;
    }

    public String getImgFoto() {
        return imgFoto;
    }

    public String getNombreModelo() {
        return nombreModelo;
    }

    public String getMarca() {
        return marca;
    }

    public String getAno() {
        return ano;
    }

    public String getMotor() {
        return motor;
    }

    public String getTipoCombustible() {
        return tipoCombustible;
    }

    public String getTraccion() {
        return traccion;
    }

    public String getTransmision() {
        return transmision;
    }

    public String getMarchas() {
        return marchas;
    }

    public String getVelocidadMaxima() {
        return velocidadMaxima;
    }

    public String getCilindrada() {
        return cilindrada;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public String getVersion() {
        return version;
    }
    public String getHp(){ return hp; }
}
