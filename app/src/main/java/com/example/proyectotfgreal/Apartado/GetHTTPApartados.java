package com.example.proyectotfgreal.Apartado;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

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
    public ArrayList<Entidad> httpList= new ArrayList<>();
    public ArrayList<EntidadSubApartado> listaFinal;
    public RecyclerView httpRecycler;
    public RecyclerView.Adapter httpAdapter;
    public Context httpContext;
    ProgressDialog progressDialog;
    ArrayList<EntidadApartado> arrayEntidadApartado = new ArrayList<>();
    public GetHTTPApartados(ArrayList<Entidad> httpList, RecyclerView httpRecycler, RecyclerView.Adapter httpAdapter, Context httpContext) {
        this.httpList = httpList;
        this.httpRecycler = httpRecycler;
        this.httpAdapter = httpAdapter;
        this.httpContext = httpContext;
    }
    @Override
    protected String doInBackground(Void... voids) {
        String result = null;
        try {
            String[] parametros = {"idPrueba"};
            String wsURL = "http://192.168.1.37/TFG/adacc.php?" + parametros[0];
            URI url = new URI(wsURL);
            // Create connection
            HttpURLConnection myConnection = (HttpURLConnection)
                    url.toURL().openConnection();
            // Establecer método. Por defecto GET.
            myConnection.setRequestMethod("GET");
            InputStream in = new BufferedInputStream(myConnection.getInputStream());
            result = inputStreamToString(in);
            Log.d("DoInBackground", result);
        } catch (Exception e) {
            Log.e("Erro0r", "Erro0r cacth IP 191.168.15");
            Log.d("Erro0r", "Erro0r cacth IP 191.168.15 " + e);
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
            ///Error Sucede aqui salta a error cach json
            JSONObject jsonObject = new JSONObject(URLDecoder.decode(s, "UTF-8" ));
            JSONArray jsonArray = jsonObject.getJSONArray("coches");
            // JSONObject jsonObject = new JSONObject(responseStrBuilder.toString());
            Log.d("recogiendoRegistros", "Primer objeto del try");
            Log.d("recogiendoRegistros", "Array del try");
            //String idModelo = jsonArray.getJSONObject(it).getString("idModelo");
            // Log.d("OnPostExecute", "recoge idModelo"+idModelo+"");
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
            //Si contiene recoges la lista de subapartados y la muestras como hicimoss con apartado
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

    //Me acabo de dar cuenta de un posible bug este ordenador solo funcionara bien si los registros estan alfabeticamente ordenado,
    //asi los registros de apartado seguiran un orden parecido a este:
    //Deportivos-Compacto deportivo
    //Deportivos-Deportivo
    //Deportivos-Descapotables
    //Deportivos-Gran Turismo
    //Deportivos-Muscle
    //Deportivos-Superdeportivo
    //Familiar
    //Minicompacto
    //Subcompacto
    //Todocaminos-SUV
    //Todocaminos-Todotorreno
    //Vehiculos de lujo-Berlina de Lujo
    //Vehiculos de lujo-Compacto premium
    //Vehiculos de lujo-Ejecutivos
    //Vehiculos de lujo-Ranchera
    //O nos seguremos que los subApartados esten ordenados respecto a su identificador como por potra pasa aqui:
    //Minicompacto
    //Subcompacto
    //Familiar
    //Vehiculos de lujo-Compacto premium
    //Vehiculos de lujo-Ejecutivos
    //Vehiculos de lujo-Berlina de Lujo
    //Vehiculos de lujo-Ranchera
    //Deportivos-Compacto deportivo
    //Deportivos-Deportivo
    //Deportivos-Gran Turismo
    //Deportivos-Superdeportivo
    //Deportivos-Muscle
    //Deportivos-Descapotables
    //Todocaminos-Todotorreno
    //Todocaminos-SUV
    //OSEA QUE PODEMOS O TENER CUIDADO CON LA BASE DE DATOS Y ANUNCIARLO COMO BUG EN CAJA NEGRA O EL ORDEN SER MUY CUTRE
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
                    //Revisar para posibles errores futuros con nuevos registros
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
            Entidad x = new Entidad(apartadoRecibido.getNombreApartado(),apartadoRecibido.getImagen());

            this.httpList.add(x);
        }
        Log.d("RecogiendoRegistros","Lista"+httpList.get(0).getTitulo());
        httpAdapter = new Adaptador(this.httpList);
        httpAdapter.entidadRecibida;
        httpRecycler.setAdapter(this.httpAdapter);
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
