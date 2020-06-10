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
import java.util.ArrayList;

public class GetHTTPApartados  extends AsyncTask<Void, Void,String> {
    private ArrayList<Entidad> httpList= new ArrayList<>();
    private RecyclerView httpRecycler;
    private RecyclerView.Adapter httpAdapter;
    private Context httpContext;
    private ProgressDialog progressDialog;
    private String ip;
    private ArrayList<EntidadApartado> arrayEntidadApartado = new ArrayList<>();
    public GetHTTPApartados(ArrayList<Entidad> httpListR, RecyclerView httpRecyclerR, RecyclerView.Adapter httpAdapterR, Context httpContextR) {
        httpList = httpListR;
        httpRecycler = httpRecyclerR;
        httpAdapter = httpAdapterR;
        httpContext = httpContextR;
    }
    @Override
    protected String doInBackground(Void... voids) {
        String result = null;
        try {
            String[] parametros = {"idPrueba"};
            ip = httpContext.getString(R.string.ip);
            String wsURL = "http://"+ip+"/TFG/adacc.php?" + parametros[0];
            Log.d("DoInBackground",wsURL);
            URI url = new URI(wsURL);
            HttpURLConnection myConnection = (HttpURLConnection) url.toURL().openConnection();
            myConnection.setRequestMethod("GET");
            InputStream in = new BufferedInputStream(myConnection.getInputStream());
            result = inputStreamToString(in);
            Log.d("DoInBackground", result);
        } catch (Exception e) {
            Log.d("Erro0r", "Erro0r cacth IP "+ip+" "+e);
        }
        return result;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.v("INICIO","INICIANDO");
        progressDialog = ProgressDialog.show(httpContext, "descargando", "Por favor, espera");
    }

    public ArrayList<EntidadApartado> metodoDefinitivoApartados(String s) {
        ArrayList<EntidadApartado> registrosDefinitivos=new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(URLDecoder.decode(s, "UTF-8" ));
            JSONArray jsonArray = jsonObject.getJSONArray("coches");
            Log.d("recogiendoRegistros", "Primer objeto del try");
            Log.d("recogiendoRegistros", "Array del try");
            ArrayList<EntidadApartado> registroIniciales=new ArrayList<EntidadApartado> ();
            for (int it = 0; it < jsonArray.length(); it++) {
                String nombreModeloRecogido = jsonArray.getJSONObject(it).getString("Nombre");
                Log.d("recogiendoRegistros", "recoge nombreApartado " + nombreModeloRecogido + "");
                String idApartadoRecogido = jsonArray.getJSONObject(it).getString("idApartado");
                Log.d("recogiendoRegistros", "recoge idApartado " + idApartadoRecogido + "");
                String imagenRecibida = jsonArray.getJSONObject(it).getString("Imagen");
                EntidadApartado recibido = new EntidadApartado(nombreModeloRecogido, idApartadoRecogido,imagenRecibida);
                registroIniciales.add(recibido);
            }
            //Registros definitivos tendra como esta definido en Apartado y SubApartado los correspondientes Apartados y
            // dentro tendran definida todos los subApartados que hay. SubApartado es un objeto que es lo que propio indica
            // Cada Apartado tiene un arrayList de subApartados solo que unos tienen algo y otros nada.
            //Recoges solo los Apartados (los registros usando getNombreApartado) y le pasas a la otra clase el objeto
            //Si esta no contiene subApartados en su array tirando para la siguiente donde hace una consulta tirando del identificadorApartado
            //Si contiene recoges la lista de subapartados y la muestras como hicimos con apartado
            //De esta forma podemos hasta parametrizar los switch asi que no hace falta definirlos :)
             registrosDefinitivos=metodoOrdenadorDefinitivoFinalDestruccion(registroIniciales);
        } catch (JSONException e) {
            Log.d("recogiendoRegistros",e.toString());
        } catch (UnsupportedEncodingException e) {
            Log.d("recogiendoRegistros",e.toString());
            e.printStackTrace();
        }
        return registrosDefinitivos;
    }

    public ArrayList<EntidadApartado> metodoOrdenadorDefinitivoFinalDestruccion(ArrayList<EntidadApartado> registrosDefinitivos) {
        //Primero recogemos el array a ordenar
        ArrayList<EntidadApartado> resutOrdenado=registrosDefinitivos;
        //Este array contendra una lista de los nombres apartados utilizados
        ArrayList<String>nombresApartados=new ArrayList<String> ();;
        Boolean seRepitio=false;
        boolean termino=false;
        String nombreSubApartado, identificadorSubApartado, imagenSubApartado;
        EntidadSubApartado subApartadoCreado;
        int contadorActual=0;
        do {
            String nombreRecibido=resutOrdenado.get(contadorActual).getNombreApartado();
            for (String registro: nombresApartados) {
                if(nombreRecibido.equals(registro)) {
                    nombreSubApartado = resutOrdenado.get(contadorActual).getListaSub().get(0).getNombreSubApartado();
                    identificadorSubApartado = resutOrdenado.get(contadorActual).getListaSub().get(0).getNumeroIdentificadorSubApartado();
                    imagenSubApartado = resutOrdenado.get(contadorActual).getListaSub().get(0).getImagenSubApartado();
                    subApartadoCreado = new EntidadSubApartado(nombreSubApartado,identificadorSubApartado,imagenSubApartado);
                    resutOrdenado.remove(contadorActual);
                    resutOrdenado.get(contadorActual-1).addSubApartadoLista(subApartadoCreado);
                    seRepitio=true;
                    try{
                        resutOrdenado.get(contadorActual+1);
                    }catch(Exception e){
                        termino=true;
                    }
                }
            }
            if(seRepitio==true) {
                seRepitio=false;
            }else{
                nombresApartados.add(nombreRecibido);
                contadorActual++;
            }
        }while(termino==false);
        Log.d("MetodoOrdenador","Termino");
        return resutOrdenado;
    }
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        progressDialog.dismiss();
        Log.d("OnPostExecute", "Comienza el try");
        arrayEntidadApartado = metodoDefinitivoApartados(s);
        for(int it=0;it<arrayEntidadApartado.size();it++){
            Log.d("RecogiendoRegistros",arrayEntidadApartado.get(it).getNombreApartado());
            EntidadApartado apartadoRecibido =arrayEntidadApartado.get(it) ;
            Entidad x = new Entidad(apartadoRecibido.getNombreApartado(),apartadoRecibido.getImagen(),arrayEntidadApartado);
            httpList.add(x);
        }
        Log.d("RecogiendoRegistros","Lista"+httpList.get(0).getTitulo());
        httpAdapter = new Adaptador(httpList);
        httpRecycler.setAdapter(httpAdapter);
    }

    public String inputStreamToString(InputStream inputStream) {
        String rLine;
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