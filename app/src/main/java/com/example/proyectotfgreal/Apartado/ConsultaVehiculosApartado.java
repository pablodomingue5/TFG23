package com.example.proyectotfgreal.Apartado;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectotfgreal.Apartado.Adaptadores.AdaptadorAPI;
import com.example.proyectotfgreal.Apartado.Entidades.Entidad;
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
import java.util.List;

public class ConsultaVehiculosApartado extends AsyncTask<Void,Void,String> {
    private List<Entidad> httpList;
    private RecyclerView httpRecycler;
    private RecyclerView.Adapter httpAdapter;
    private Context httpContext;
    private ProgressDialog progressDialog;
    private String numeroApartado;

    public ConsultaVehiculosApartado(List<Entidad> httpListR, RecyclerView httpRecyclerR, RecyclerView.Adapter httpAdapterR, String numeroRecibido, Context httpContextR) {
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
            String[] parametros = {"idApartadoSeleccionado", numeroApartado};
            String ip = httpContext.getString(R.string.ip);
            String wsURL = "http://"+ip+"/TFG/adacc.php?" + parametros[0] + "=" + parametros[1];
            URL url = new URL(wsURL);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            result = inputStreamToString(in);
            Log.d("DoInBackground", result);
        } catch (Exception e) {
            Log.e("Erro0r", "Erro0r cacth IP 191.168.1");
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
        try {
            ///Error Sucede aqui salta a error cach json
            JSONObject jsonObject = new JSONObject(URLDecoder.decode(s, "UTF-8" ));
            JSONArray jsonArray = jsonObject.getJSONArray("coches");
            Log.d("OnPostExecute", jsonArray+"");
            for (int it = 0; it < jsonArray.length(); it++) {
                Log.d("OnPostExecute", "Entro en for contador:"+it+"de"+jsonArray.length());
                String nombreModelo = jsonArray.getJSONObject(it).getString("nombreModelo");
                String urlModelo = jsonArray.getJSONObject(it).getString("urlModelo");
                String nombreSerie = jsonArray.getJSONObject(it).getString("nombreSerie");
                int idModelo = jsonArray.getJSONObject(it).getInt("idModelo");
                if(nombreModelo.equals("null")){
                    nombreModelo="";
                }
                Log.d("OnPostExecute", "Registro Recibido:"+nombreModelo+"-"+urlModelo);
                Entidad x = new Entidad(nombreSerie+" "+nombreModelo, urlModelo, idModelo);
                Log.d("OnPostExecute", "Objeto Recibido:"+x.getTitulo()+"-"+ x.getUrlImagen());
                httpList.add(x);
            }
            httpAdapter = new AdaptadorAPI(httpList);
            httpRecycler.setAdapter(httpAdapter);
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

