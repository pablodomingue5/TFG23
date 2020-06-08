package com.example.proyectotfgreal.Apartado;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.proyectotfgreal.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;

class GetHTTPUsuario extends AsyncTask<Void,Void,String> {
    public String getNumeroResultante() {
        return numeroResultante;
    }

    public void setNumeroResultante(String numeroResultante) {
        this.numeroResultante = numeroResultante;
    }

    public String numeroResultante;
    public String nombreUsuario;
    public Context contexto;
    GetHTTPUsuario(String nombreUsuarioR, Context contextoR){
        nombreUsuario=nombreUsuarioR;
        contexto=contextoR;
    }

    @Override
    protected String doInBackground(Void... voids) {
        String result = null;
        Log.d("EnvioSentencia","Llega a in do background");
        try {
            String ip = contexto.getString(R.string.ip);
            //192.168.1.115/TFG/adacc.php?fechaComentario=VARIABLE&contenidoComentario=VARIABLE&idModeloFK=VARIABLE&idUsuarioFK=VARIABLE
            String wsURL = "http://"+ip+"/TFG/adacc.php?" +"nombreUsuario"+ "=" + nombreUsuario;
            Log.d("EnvioSentencia",wsURL+"");
            URL url = new URL(wsURL);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            result = leerResultado(in);
            if(result.contains("[]")){
                wsURL = "http://"+ip+"/TFG/adacc.php?" +"nombreUsuario2"+ "=" + nombreUsuario;
                url = new URL(wsURL);
                Log.d("EnvioSentencia","Entro en if");
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream ins = new BufferedInputStream(urlConnection.getInputStream());
                Log.d("EnvioSentencia",result);
                result = leerResultado(ins);
                result=(InterpretarDato1(result));
            }else{
                //Recoger
                result=(InterpretarDato2(result));
            }
            Log.d("EnvioSentencia", result);
        } catch (Exception e) {
            Log.e("Erro0r", "Erro0r cacth IP 191.168.1");
            e.printStackTrace();
        }
        return getNumeroResultante();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        setNumeroResultante(s);
    }

    private String InterpretarDato1(String s){
        String idUsuarioObtenido="";
        try {
            Log.d("InterpretarDatos", s);
            Log.d("InterpretarDatos", "Principio interpretar");
            JSONObject jsonObject = new JSONObject(URLDecoder.decode(s, "UTF-8"));
            JSONArray jsonArray = jsonObject.getJSONArray("");
            // JSONObject jsonObject = new JSONObject(responseStrBuilder.toString());
            Log.d("InterpretarDatos", "Primer objeto del try");
            //JSONArray jsonArray = jsonObject.getJSONArray("coches");
            Log.d("InterpretarDatos", "Array del try");
            Log.d("InterpretarDatos", jsonArray + "");
            idUsuarioObtenido= jsonArray.getJSONObject(0).getInt("id")+"";
        } catch(UnsupportedEncodingException | JSONException e){
            Log.e("InterpretarDatos", "Erro0r CATCH inputStream: "+e);
        }
        return idUsuarioObtenido;
    }

    private String InterpretarDato2(String s) {
        String idUsuarioObtenido="";
        try {
            Log.d("InterpretarDatos", "Principio interpretar");
            JSONObject jsonObject = new JSONObject(URLDecoder.decode(s, "UTF-8"));
            JSONArray jsonArray = jsonObject.getJSONArray("");
            // JSONObject jsonObject = new JSONObject(responseStrBuilder.toString());
            Log.d("InterpretarDatos", "Primer objeto del try");
            //JSONArray jsonArray = jsonObject.getJSONArray("coches");
            Log.d("InterpretarDatos", "Array del try");
            Log.d("InterpretarDatos", jsonArray + "");
            idUsuarioObtenido= jsonArray.getJSONObject(0).getInt("idUsuario")+"";
            //Aqui hace la insercción y devuelve el numero aunque probablemente no te haga falta dejalo como está
        } catch(UnsupportedEncodingException | JSONException e){
            Log.e("InterpretarDatos", "Erro0r CATCH inputStream");
        }
        return idUsuarioObtenido;
    }

    private String leerResultado(InputStream in) {
     String rLine = "";
        Log.d("InputStream","Comienzo metodo");
    StringBuilder answer = new StringBuilder();
        Log.d("InputStream","Comienza inputStreamReader");
    InputStreamReader isr = new InputStreamReader(in);
        Log.d("InputStream","Comienza Buffered reader");
    BufferedReader rd = new BufferedReader(isr);
        Log.d("InputStream","Comienza try y while");
        try {
        while ((rLine = rd.readLine()) != null) {
            answer.append(rLine);
        }
        Log.d("InputStream","Finaliza while");
    } catch (
    IOException e) {
        e.printStackTrace();
        Log.e("InputStream", "Erro0r CATCH inputStream");
    }
        return answer.toString();
    }
}
