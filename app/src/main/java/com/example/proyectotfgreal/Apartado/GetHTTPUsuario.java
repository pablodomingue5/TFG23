package com.example.proyectotfgreal.Apartado;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
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
    private String nombreUsuario;
    private String comentario;
    private String[] nombreParametros = {"fechaComentario","contenidoComentario","usuarioNumero","modeloSeleccionado"};
    private String[] registrosParametros;
    private View view;
    private Context context;

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.d("EnvioSentencia",s);
        Toast.makeText(view.getContext(),"Se metio el comentario wey",Toast.LENGTH_LONG).show();
        Intent iconIntent = new Intent (view.getContext(), ActivityComentariosDatos.class);
        iconIntent.putExtra("numeroIdentificadorModelo",Integer.parseInt(s));
        view.getContext().startActivity(iconIntent);
    }


    public GetHTTPUsuario(String nombreUsuarioR, String comentarioR, String fechaActualR, String numeroModeloR, View viewR, Context contextR){
        nombreUsuario=nombreUsuarioR;
        view=viewR;
        comentario=comentarioR;
        context=contextR;
        registrosParametros = new String[]{fechaActualR, comentarioR, "", numeroModeloR};
    }

    @Override
    protected String doInBackground(Void... voids) {
        String result = null;
        Log.d("EnvioSentencia","Llega a in do background");
        try {
            String ip = context.getString(R.string.ip);
            String wsURL = "http://"+ip+"/TFG/adacc.php?" +"nombreUsuario"+ "=" + nombreUsuario;
            Log.d("EnvioSentencia",wsURL+"");
            URL url = new URL(wsURL);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            result = leerResultado(in);
            if(result.contains("[]")){
                wsURL = "http://"+ip+"/TFG/adacc.php?" +"nombreUsuario2"+ "=" + nombreUsuario;
                Log.d("EnvioSentencia",wsURL);
                url = new URL(wsURL);
                Log.d("EnvioSentencia","Entro en if");
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream ins = new BufferedInputStream(urlConnection.getInputStream());
                Log.d("EnvioSentencia",result);
                wsURL = "http://"+ip+"/TFG/adacc.php?" +"nombreUsuario3"+ "=" + nombreUsuario;
                Log.d("EnvioSentencia",wsURL);
                url = new URL(wsURL);
                Log.d("EnvioSentencia","Entro en if");
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream ins2 = new BufferedInputStream(urlConnection.getInputStream());
                result = leerResultado(ins2);
                result=InterpretarDato1(result);
            }else{
                result=(InterpretarDato2(result));
                registrosParametros[2]= result;
            }
            Log.d("EnvioSentencia", result);
        } catch (Exception e) {
            Log.e("Erro0r", "Erro0r cacth IP 191.168.1");
        }
        try{
           registrosParametros[2]=result;
            String ip = context.getString(R.string.ip);
            String wsURL = "http://"+ip+"/TFG/adacc.php?" + nombreParametros[0] + "=" + registrosParametros[0]+"&"+ nombreParametros[1]+"="+registrosParametros[1]+"&"+nombreParametros[2]+"="+registrosParametros[2]+"&"+nombreParametros[3]+"="+registrosParametros[3];
            Log.d("EnvioSentencia",wsURL+"");
            URL url = new URL(wsURL);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            result = leerResultado(in);
            result=interpretarDatoInsertado(result);
        }catch (Exception e){
            Log.e("Erro0r", "Erro0r catch 2");
            e.printStackTrace();
        }
        return registrosParametros[3];
    }

    private String interpretarDatoInsertado(String s) {
        String idComentarioObtenido="";
        try {
            Log.d("InterpretarDatos", s);
            Log.d("InterpretarDatos", "Principio interpretar");
            JSONObject jsonObject = new JSONObject(URLDecoder.decode(s, "UTF-8"));
            JSONArray jsonArray = jsonObject.getJSONArray("");
            Log.d("InterpretarDatos", "Primer objeto del try");
            Log.d("InterpretarDatos", "Array del try");
            Log.d("InterpretarDatos", jsonArray + "");
            idComentarioObtenido= jsonArray.getJSONObject(0).getInt("id")+"";
        } catch(UnsupportedEncodingException | JSONException e){
            Log.e("InterpretarDatos", "Erro0r CATCH inputStream: "+e);
        }
        return idComentarioObtenido;
    }


    private String InterpretarDato2(String s) {
        String idUsuarioObtenido="";
        try {
            Log.d("InterpretarDatos", s);
            JSONObject jsonObject = new JSONObject(URLDecoder.decode(s, "UTF-8"));
            JSONArray jsonArray = jsonObject.getJSONArray("coches");
            Log.d("InterpretarDatos", "Primer objeto del try");
            idUsuarioObtenido= jsonArray.getJSONObject(0).getString("idUsuario");
            Log.d("EnvioSentencia","Usuario obtenido "+idUsuarioObtenido);
            //Aqui hace la insercción y devuelve el numero aunque probablemente no te haga falta dejalo como está
        } catch(UnsupportedEncodingException | JSONException e){
            Log.e("InterpretarDatos", "Erro0r CATCH inputStream");
        }
        return idUsuarioObtenido;
    }
    private String InterpretarDato1(String s) {
        String adevolver = null;
        try {
            JSONObject jsonObject = new JSONObject(URLDecoder.decode(s, "UTF-8" ));
            JSONArray jsonArray = jsonObject.getJSONArray("coches");
            Log.d("recogiendoRegistros", "Primer objeto del try");
            for (int it = 0; it < jsonArray.length(); it++) {
                String nombreModeloRecogido = jsonArray.getJSONObject(it).getString("idUsuario");
                Log.d("recogiendoRegistros", "recoge nombreApartado " + nombreModeloRecogido + "");
                 adevolver = nombreModeloRecogido;
            }
        } catch (JSONException e) {
            Log.d("recogiendoRegistros",e.toString());
        } catch (UnsupportedEncodingException e) {
            Log.d("recogiendoRegistros",e.toString());
            e.printStackTrace();
        }
        return adevolver;
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
     } catch (IOException e) {
       Log.e("InputStream", "Erro0r CATCH inputStream");
     }
        return answer.toString();
     }
}
