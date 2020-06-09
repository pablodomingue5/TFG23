package com.example.proyectotfgreal.Apartado;

import android.app.ProgressDialog;
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
import java.util.ArrayList;

class GetHTTPUsuario extends AsyncTask<Void,Void,String> {
    ProgressDialog progressDialog;
    public String getNumeroResultante() {
        return numeroResultante;
    }

   /*
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(contexto, "Subiendo", "Por favor, espera");
    }
*/
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.d("EnvioSentencia",s);
        //progressDialog.dismiss();
        Toast.makeText(view.getContext(),"Se metio el comentario wey",Toast.LENGTH_LONG).show();
        Intent iconIntent = new Intent (view.getContext(), ActivityComentariosDatos.class);
        iconIntent.putExtra("numeroIdentificadorModelo",Integer.parseInt(s));
        view.getContext().startActivity(iconIntent);
    }

    public String numeroResultante;
    public String nombreUsuario;
    public String comentario;
    String[] nombreParametros = {"fechaComentario","contenidoComentario","usuarioNumero","modeloSeleccionado"};
    String[] registrosParametros;
    public View view;
    Context context;
    GetHTTPUsuario(String nombreUsuarioR, String comentarioR, String fechaActualR, String numeroModeloR, View viewR, Context contextR){
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
            //192.168.1.115/TFG/adacc.php?fechaComentario=VARIABLE&contenidoComentario=VARIABLE&idModeloFK=VARIABLE&idUsuarioFK=VARIABLE
            String wsURL = "http://"+ip+"/TFG/adacc.php?" +"nombreUsuario"+ "=" + nombreUsuario;
            Log.d("EnvioSentencia",wsURL+"");
            URL url = new URL(wsURL);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            result = leerResultado(in);
            if(result.contains("[]")){
                wsURL = "http://"+ip+"/TFG/adacc.php?" +"nombreUsuario3"+ "=" + nombreUsuario;
                Log.d("EnvioSentencia",wsURL);
                url = new URL(wsURL);
                Log.d("EnvioSentencia","Entro en if");
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream ins = new BufferedInputStream(urlConnection.getInputStream());
                Log.d("EnvioSentencia",result);
                result = leerResultado(ins);
                result=InterpretarDato1(result);
            }else{
                //Recoger
                result=(InterpretarDato2(result));
            }
            Log.d("EnvioSentencia", result);
        } catch (Exception e) {
            Log.e("Erro0r", "Erro0r cacth IP 191.168.1");
            e.printStackTrace();
        }
        try{
           registrosParametros[2]=result;
            String ip = context.getString(R.string.ip);
            //192.168.1.115/TFG/adacc.php?fechaComentario=VARIABLE&contenidoComentario=VARIABLE&idModeloFK=VARIABLE&idUsuarioFK=VARIABLE
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
            // JSONObject jsonObject = new JSONObject(responseStrBuilder.toString());
            Log.d("InterpretarDatos", "Primer objeto del try");
            //JSONArray jsonArray = jsonObject.getJSONArray("coches");
            Log.d("InterpretarDatos", "Array del try");
            Log.d("InterpretarDatos", jsonArray + "");
            idComentarioObtenido= jsonArray.getJSONObject(0).getInt("id")+"";
        } catch(UnsupportedEncodingException | JSONException e){
            Log.e("InterpretarDatos", "Erro0r CATCH inputStream: "+e);
        }
        return idComentarioObtenido;
    }

/*
    private String InterpretarDato1(String s){
        String idUsuarioObtenido="";
        try {
            Log.d("InterpretarDatos", s);
            Log.d("InterpretarDatos", "Principio interpretar");
            JSONObject jsonObject = new JSONObject(URLDecoder.decode(s, "UTF-8"));
            //JSONArray jsonArray = jsonObject.getJSONArray("");
            // JSONObject jsonObject = new JSONObject(responseStrBuilder.toString());
            Log.d("InterpretarDatos", "Primer objeto del try");
            JSONArray jsonArray = jsonObject.getJSONArray("coches");
            Log.d("InterpretarDatos", "Array del try");
            Log.d("InterpretarDatos", jsonArray + "");
            idUsuarioObtenido= jsonArray.getJSONObject(0).getInt("id")+"";
            Log.d("InterpretarDatos",idUsuarioObtenido);
        } catch(UnsupportedEncodingException | JSONException e){
            Log.e("InterpretarDatos", "Erro0r CATCH inputStream: "+e);
        }
        return idUsuarioObtenido;
    }
*/
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
    private String InterpretarDato1(String s) {
        ArrayList<EntidadApartado> registrosDefinitivos=new ArrayList<>();
        String adevolver = null;
        try {
            ///Error Sucede aqui salta a error cach json
            JSONObject jsonObject = new JSONObject(URLDecoder.decode(s, "UTF-8" ));
            JSONArray jsonArray = jsonObject.getJSONArray("coches");
            // JSONObject jsonObject = new JSONObject(responseStrBuilder.toString());
            Log.d("recogiendoRegistros", "Primer objeto del try");
            Log.d("recogiendoRegistros", "Array del try");
            //String idModelo = jsonArray.getJSONObject(it).getString("idModelo");
            // Log.d("OnPostExecute", "recoge idModelo"+idModelo+"");

            for (int it = 0; it < jsonArray.length(); it++) {
                String nombreModeloRecogido = jsonArray.getJSONObject(it).getString("idUsuario");
                Log.d("recogiendoRegistros", "recoge nombreApartado " + nombreModeloRecogido + "");
                //String idApartadoRecogido = jsonArray.getJSONObject(it).getString("idApartado");
              //  Log.d("recogiendoRegistros", "recoge idApartado " + idApartadoRecogido + "");
               // String imagenRecibida = jsonArray.getJSONObject(it).getString("Imagen");
                //EntidadApartado recibido = new EntidadApartado(nombreModeloRecogido, idApartadoRecogido,imagenRecibida);
               // registroIniciales.add(recibido);
                 adevolver = nombreModeloRecogido;
            }
            //Registros definitivos tendra como esta definido en Apartado y SubApartado los correspondientes Apartados y
            // dentro tendran definida todos los subApartados que hay. SubApartado es un objeto que es lo que propio indica
            // Cada Apartado tiene un arrayList de subApartados solo que unos tienen algo y otros nada.
            //Recoges solo los Apartados (los registros usando getNombreApartado) y le pasas a la otra clase el objeto
            //Si esta no contiene subApartados en su array tirando para la siguiente donde hace una consulta tirando del identificadorApartado
            //Si contiene recoges la lista de subapartados y la muestras como hicimoss con apartado
            //De esta forma podemos hasta parametrizar los switch asi que no hace falta definirlos :)

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
    } catch (
    IOException e) {
        e.printStackTrace();
        Log.e("InputStream", "Erro0r CATCH inputStream");
    }
        return answer.toString();
    }
}
