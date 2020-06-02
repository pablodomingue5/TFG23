package com.example.proyectotfgreal.Apartado;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectotfgreal.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;

import static android.widget.Toast.LENGTH_LONG;

public class GetHTTPDatos extends AsyncTask<Void,Void,String> {
    ProgressDialog progressDialog;
    String fechaActual;
    String comentario;
    String usuario;
    String numeroModelo;
    Context contexto;

public GetHTTPDatos(String fechaActualR, String comentarioR, String usuarioR, String numeroModeloR, Context contexto) {
        fechaActual=fechaActualR;
        comentario = comentarioR;
        usuario = usuarioR;
        numeroModelo = numeroModeloR;
        this.contexto = contexto;
        }
@Override
protected String doInBackground(Void... voids) {
        String result = null;
        Log.d("EnvioSentencia","Llega a in do background");
        try {
                String[] nombreParametros = {"fechaComentario","contenidoComentario","usuarioNumero","modeloSeleccionado"};
                String[] registrosParametros = {fechaActual,comentario,usuario,numeroModelo};
                String ip = contexto.getString(R.string.ip);
                //192.168.1.115/TFG/adacc.php?fechaComentario=VARIABLE&contenidoComentario=VARIABLE&idModeloFK=VARIABLE&idUsuarioFK=VARIABLE
                String wsURL = "http://"+ip+"/TFG/adacc.php?" + nombreParametros[0] + "=" + registrosParametros[0]+"&"+
                        nombreParametros[1]+"="+registrosParametros[1]+"&"+nombreParametros[2]+"="+registrosParametros[2]+"&"+nombreParametros[3]+"="+registrosParametros[3];
                Log.d("EnvioSentencia",wsURL+"");
                URL url = new URL(wsURL);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                result = inputStreamToString(in);
                Log.d("EnvioSentencia", result);
        } catch (Exception e) {
                Log.e("Erro0r", "Erro0r cacth IP 191.168.1");
                e.printStackTrace();
        }
        return result;
        }
@Override
protected void onPreExecute() {
        super.onPreExecute();
        //progressDialog = ProgressDialog.show(contexto, "Subiendo", "Por favor, espera");
        }

@Override
protected void onPostExecute(String s) {
        super.onPostExecute(s);
      // progressDialog.dismiss();
        Log.d("OnPostExecute", "Comienza el try");
        String line = "";
        StringBuilder responseStrBuilder = new StringBuilder();
                try {
                        ///Error Sucede aqui salta a error cach json
                        JSONObject jsonObject = new JSONObject(URLDecoder.decode(s, "UTF-8" ));
                        // JSONObject jsonObject = new JSONObject(responseStrBuilder.toString());
                        Log.d("OnPostExecute", "Primer objeto del try");
                        //JSONArray jsonArray = jsonObject.getJSONArray("coches");
                        Log.d("OnPostExecute", "Array del try");

                        //String nombreModelo = jsonArray.getJSONObject(it).getString("nombreModelo");
                       // int idModelo = jsonArray.getJSONObject(it).getInt("id")
                        int idModelo = jsonObject.getInt("id");
                        //if(nombreModelo.equals("null")){
                        //nombreModelo="";
                        Toast.makeText(contexto,idModelo+"",LENGTH_LONG).show();
                        Log.d("OnPostExecute", "Registro Recibido:");
                       // Entidad x = new Entidad(nombreSerie+" "+nombreModelo, urlModelo, idModelo);
                        Log.d("OnPostExecute", "Objeto Recibido:"+idModelo+"");
                } catch (JSONException ex) {
                        Log.d("OnPostExecute",ex.toString());
                } catch (UnsupportedEncodingException ex) {
                        ex.printStackTrace();
                }
        }


private String inputStreamToString(InputStream inputStream) {
        String rLine = "";
        Log.d("InputStream","Comienzo metodo");
        StringBuilder answer = new StringBuilder();
        Log.d("InputStream","Comienza inputStreamReader");
        InputStreamReader isr = new InputStreamReader(inputStream);
        Log.d("InputStream","Comienza Buffered reader");
        BufferedReader rd = new BufferedReader(isr);
        Log.d("InputStream","Comienza try y while");
        try {
        while ((rLine = rd.readLine()) != null) {
        answer.append(rLine);
        }
        Log.d("InputStream","Finaliza while");
        } catch (IOException e) {
        e.printStackTrace();
        Log.e("InputStream", "Erro0r CATCH inputStream");
        }
        return answer.toString();
        }
        }

