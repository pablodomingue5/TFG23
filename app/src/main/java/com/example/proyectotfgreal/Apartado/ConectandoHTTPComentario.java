package com.example.proyectotfgreal.Apartado;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

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
import java.net.URI;
import java.net.URLDecoder;
import java.util.List;

class ConectandoHTTPComentario extends AsyncTask<Void, Void,String> {

    private List<EntidadComentariosDatos> httpList;
    private RecyclerView httpRecycler;
    private RecyclerView.Adapter httpAdapter;
    private Context httpContext;
    ProgressDialog progressDialog;
    String numeroApartado;
    String ip;

    public ConectandoHTTPComentario(List<EntidadComentariosDatos> httpList, RecyclerView httpRecycler, RecyclerView.Adapter httpAdapter, String numeroRecibido, Context httpContext) {
        this.httpList = httpList;
        this.httpRecycler = httpRecycler;
        this.httpAdapter = httpAdapter;
        this.httpContext = httpContext;
        numeroApartado=numeroRecibido;
    }
    @Override
    protected String doInBackground(Void... voids) {
        String result = null;
        try {
            String[] parametros = {"idComentario",numeroApartado};
            ip = httpContext.getString(R.string.ip);
            String wsURL = "http://"+ip+"/TFG/adacc.php?" + parametros[0]+"="+parametros[1];
            Log.d("DoInBackground",wsURL);
            URI url = new URI(wsURL);
            // Create connection
            HttpURLConnection myConnection = (HttpURLConnection) url.toURL().openConnection();
            // Establecer método. Por defecto GET.
            myConnection.setRequestMethod("GET");
            InputStream in = new BufferedInputStream(myConnection.getInputStream());
            result = inputStreamToString(in);
            Log.d("DoInBackground", result);
        } catch (Exception e) {
            Log.e("Erro0r", "Erro0r cacth IP "+ip);
            Log.d("Erro0r", "Erro0r cacth IP "+ip+" "+e);
        }
        return result;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(httpContext, "descargando", "Por favor, espera");
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
                //fecha
                String fecha = jsonArray.getJSONObject(it).getString("Fecha");
                //nombreUsuario
                String contenidoComentario = jsonArray.getJSONObject(it).getString("Contenido");
                //contenido
                String usuario = jsonArray.getJSONObject(it).getString("Usuario");

                Log.d("OnPostExecute", "Registro Recibido:"+fecha+"-"+contenidoComentario+"-"+usuario);
                EntidadComentariosDatos x = new EntidadComentariosDatos(fecha,usuario, contenidoComentario);
                Log.d("OnPostExecute", "Objeto Recibido:"+x.getFecha()+"-"+ x.getNombreUsuario()+"-"+x.getContenidoComentario());
                this.httpList.add(x);
            }
            httpAdapter = new AdaptadorComentarioDatos(this.httpList);
            httpRecycler.setAdapter(this.httpAdapter);
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
