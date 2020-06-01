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
    int numeroModelo;
    Context contexto;

public GetHTTPDatos(String fechaActualR, String comentarioR, String usuarioR, int numeroModeloR, Context applicationContext) {
        fechaActual=fechaActualR;
        comentario = comentarioR;
        usuario = usuarioR;
        numeroModelo = numeroModeloR;
        contexto = applicationContext;
        }
@Override
protected String doInBackground(Void... voids) {
        String result = null;
        try {
        String[] parametros = {"fechaComentario",fechaActual,"contenidoComentario",comentario,"modeloSeleccionado",usuario,"usuarioNumero",numeroModelo+""};
        String ip = contexto.getString(R.string.ip);
        //192.168.1.115/TFG/adacc.php?fechaComentario=VARIABLE&contenidoComentario=VARIABLE&idModeloFK=VARIABLE&idUsuarioFK=VARIABLE
        String wsURL = "http://"+ip+"/TFG/adacc.php?" + parametros[0] + "=" + parametros[1]+"&"+parametros[2]+"="+parametros[3]+"&"+parametros[4]+"="+parametros[5]+"&"+parametros[6]+"="+parametros[7]+";";
        URL url = new URL(wsURL);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        InputStream in = new BufferedInputStream(urlConnection.getInputStream());
        result = inputStreamToString(in);
        Log.d("DoInBackground", result);

        } catch (Exception e) {
        Log.e("Erro0r", "Erro0r cacth IP 191.168.1");
        e.printStackTrace();
        }
        return result;
        }
@Override
protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(contexto, "descargando", "Por favor, espera");
        }

@Override
protected void onPostExecute(String s) {
        super.onPostExecute(s);
        progressDialog.dismiss();
        Log.d("OnPostExecute", "Comienza el try");
        String line = "";
        StringBuilder responseStrBuilder = new StringBuilder();
        try {
        ///Error Sucede aqui salta a error cach json
        JSONObject jsonObject = new JSONObject(URLDecoder.decode(s, "UTF-8" ));
        JSONArray jsonArray = jsonObject.getJSONArray("coches");
        // JSONObject jsonObject = new JSONObject(responseStrBuilder.toString());
        Log.d("OnPostExecute", "Primer objeto del try");
        //JSONArray jsonArray = jsonObject.getJSONArray("coches");
        Log.d("OnPostExecute", "Array del try");
        Log.d("OnPostExecute", jsonArray+"");
        for (int it = 0; it < jsonArray.length(); it++) {
        Log.d("OnPostExecute", "Entro en for contador:"+it+"de"+jsonArray.length());
        //String nombreModelo = jsonArray.getJSONObject(it).getString("nombreModelo");
       // int idModelo = jsonArray.getJSONObject(it).getInt("id");
            int idModelo = jsonArray.getJSONObject(it).getInt("id");
        //if(nombreModelo.equals("null")){
        //nombreModelo="";
        //}
            Toast.makeText(contexto,idModelo+"",LENGTH_LONG).show();
        Log.d("OnPostExecute", "Registro Recibido:");
       // Entidad x = new Entidad(nombreSerie+" "+nombreModelo, urlModelo, idModelo);
        Log.d("OnPostExecute", "Objeto Recibido:"+idModelo+"");
        //this.httpList.add(x);
        }
        //httpAdapter = new AdaptadorAPI(this.httpList);
        //httpRecycler.setAdapter(this.httpAdapter);
        } catch (JSONException e) {
        Log.d("OnPostExecute",e.toString());
        } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
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

