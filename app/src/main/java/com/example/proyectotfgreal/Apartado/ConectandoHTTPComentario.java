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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

class ConectandoHTTPComentario extends AsyncTask<Void, Void,String> {

    private List<EntidadComentariosDatos> httpList;
    private RecyclerView httpRecycler;
    private RecyclerView.Adapter httpAdapter;
    private Context httpContext;
    ProgressDialog progressDialog;
    String numeroApartado;
    String ip;

    public ConectandoHTTPComentario(List<EntidadComentariosDatos> httpListR, RecyclerView httpRecyclerR, RecyclerView.Adapter httpAdapterR, String numeroRecibido, Context httpContextR) {
        httpList = httpListR;
        httpRecycler = httpRecyclerR;
        httpAdapter = httpAdapterR;
        httpContext = httpContextR;
        numeroApartado=numeroRecibido;
    }
    @Override
    protected String doInBackground(Void... voids) {
        String result = null;
        try {
            String[] parametros = {"idModeloComentarios",numeroApartado};
            ip = httpContext.getString(R.string.ip);
            String wsURL = "http://"+ip+"/TFG/adacc.php?" + parametros[0]+"="+parametros[1];
            Log.d("Linea",wsURL);
            URI url = new URI(wsURL);
            // Create connection
            Log.d("linea", "continua url");
            HttpURLConnection myConnection = (HttpURLConnection) url.toURL().openConnection();
            Log.d("linea", "abre conexion");
            myConnection.setRequestMethod("GET");
            InputStream in = new BufferedInputStream(myConnection.getInputStream());
            Log.d("linea", "abre input");

            result = inputStreamToString(in);
            Log.d("linea", result);
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
        try {
            JSONObject jsonObject = new JSONObject(URLDecoder.decode(s, "UTF-8" ));
            JSONArray jsonArray = jsonObject.getJSONArray("coches");
            Log.d("OnPostExecute", "Primer objeto del try");
            Log.d("OnPostExecute", jsonArray+"");
            for (int it = 0; it < jsonArray.length(); it++) {
                Log.d("OnPostExecute", "Entro en for contador:"+it+"de"+jsonArray.length());
                String fecha = fechaModificador(jsonArray.getJSONObject(it).getString("Fecha"));
                String contenidoComentario = jsonArray.getJSONObject(it).getString("Contenido");
                String usuario = jsonArray.getJSONObject(it).getString("Usuario");
                Log.d("OnPostExecute", "Registro Recibido:"+fecha+"-"+contenidoComentario+"-"+usuario);
                EntidadComentariosDatos x = new EntidadComentariosDatos(fecha,usuario, contenidoComentario);
                Log.d("OnPostExecute", "Objeto Recibido:"+x.getFecha()+"-"+ x.getNombreUsuario()+"-"+x.getContenidoComentario());
                httpList.add(x);
            }
            httpAdapter = new AdaptadorComentarioDatos(httpList);
            httpRecycler.setAdapter(httpAdapter);
        } catch (JSONException e) {
            Log.d("OnPostExecute",e.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public static String diferencia (Date inicio, Date fin){
        long diffInMs = fin.getTime() - inicio.getTime();
        long diffInSec = TimeUnit.MILLISECONDS.toSeconds(diffInMs);
        long hora = diffInSec/3600;
        int dias = (int) (hora/24);
        if (hora<=24){
            if(hora==0){
                return "Hace escasos minutos";
            }else{
                return "Hace "+String.valueOf(hora)+" horas";
            }
        }
        else{
            return "Hace "+String.valueOf(dias)+" dias";
        }
    }
    private String fechaModificador(String fecha) throws ParseException {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date i = simpleDateFormat.parse(fecha);
        Date f = new Date();
        return diferencia(i,f);
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
