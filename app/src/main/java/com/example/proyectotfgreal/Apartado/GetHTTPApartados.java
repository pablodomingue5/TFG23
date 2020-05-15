package com.example.proyectotfgreal.Apartado;

import android.os.AsyncTask;
import android.util.Log;

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
    @Override
    protected String doInBackground(Void... voids) {
        String result = null;
        try {
            String[] parametros = {"idModelo", "3"};
            String wsURL = "http://192.168.1.115/TFG/adacc.php?" + parametros[0] + "=" + parametros[1];
            URI url = new URI(wsURL);
            // Create connection
            HttpURLConnection myConnection = (HttpURLConnection)
                    url.toURL().openConnection();
            // Establecer método. Por defecto GET.
            myConnection.setRequestMethod("GET");
            InputStream in = new BufferedInputStream(myConnection.getInputStream());
            result = inputStreamToString(in);
            Log.d("DoInBackground", result);
            metodoDefinitivoApartados(result);

        } catch (Exception e) {
            Log.e("Erro0r", "Erro0r cacth IP 191.168.15");
            Log.d("Erro0r", "Erro0r cacth IP 191.168.15 " + e);
        }
        return result;
    }

    private void metodoDefinitivoApartados(String s) {
        try {
            ///Error Sucede aqui salta a error cach json
            JSONObject jsonObject = new JSONObject(URLDecoder.decode(s, "UTF-8" ));
            JSONArray jsonArray = jsonObject.getJSONArray("apartados");
            // JSONObject jsonObject = new JSONObject(responseStrBuilder.toString());
            Log.d("recogiendoRegistros", "Primer objeto del try");
            Log.d("recogiendoRegistros", "Array del try");
            //String idModelo = jsonArray.getJSONObject(it).getString("idModelo");
            // Log.d("OnPostExecute", "recoge idModelo"+idModelo+"");
            ArrayList<EntidadApartado> registroIniciales=new ArrayList<EntidadApartado> ();
            for (int it = 0; it < jsonArray.length(); it++) {
                String nombreModeloRecogido = jsonArray.getJSONObject(it).getString("Nombre");
                Log.d("recogiendoRegistros", "recoge nombreApartado " + nombreModeloRecogido + "");
                String idApartadoRecogido = jsonArray.getJSONObject(it).getString("id");
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
           ArrayList<EntidadApartado> registrosDefinitivos=metodoOrdenadorDefinitivoFinalDestruccion(registroIniciales);

        } catch (JSONException e) {
            Log.d("recogiendoRegistros",e.toString());
        } catch (UnsupportedEncodingException e) {
            Log.d("recogiendoRegistros",e.toString());
            e.printStackTrace();
        }
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
    private ArrayList<EntidadApartado> metodoOrdenadorDefinitivoFinalDestruccion(ArrayList<EntidadApartado> registrosDefinitivos) {
        //Primero recogemos el array a ordenar
        ArrayList<EntidadApartado> resutOrdenado=registrosDefinitivos;
        //Este array contendra una lista de los nombres apartados utilizados
        ArrayList<String>nombreApartado=new ArrayList<String> ();;
        Boolean seRepitio=false;
        //Nos aseguramos de cuantos objetos Apartado tiene
        int numeroResult=resutOrdenado.size();
        //hacemos un for para ver cada registro y consecuentemente ordenarlo
        for(int i=0; i<numeroResult;i++){
            //Recogemos el nombre que vamos a comprobar o añadir
            String nombreRecibido=registrosDefinitivos.get(i).getNombreApartado();
            //Buscamos si se repite
            for (String a:nombreApartado ) {
                //Solo nos interesa comprobar si este nombre se encuentra en la lista
                //y para eso solo comprobaremos si se repitio al final (Se que continuaría si se probase pero me da pereza pensar mas)
                if(a==nombreRecibido){
                    //Recogemos los registros del subApartado del Apartado segundo  que coincide en nombre()
                    String numeroIdentificadorAsignadoSub = resutOrdenado.get(i).getListaSub().get(0).getNumeroIdentificadorSubApartado();
                    String nombreSubApartado = resutOrdenado.get(i).getListaSub().get(0).getNombreSubApartado();
                    String imagen = resutOrdenado.get(i).getListaSub().get(0).getImagenSubApartado();
                    //Creamos un subApartado con los datos que recogimos del unico SubApartado que tiene este apartado repetido
                    EntidadSubApartado subApartadoAñadir = new EntidadSubApartado(nombreSubApartado,numeroIdentificadorAsignadoSub,imagen);
                    //Eliminamos el registro repetido
                    registrosDefinitivos.remove(i);
                    //tiramos para el anterior registro que probablemente sea el original
                    --i;
                    //Recogemos el objeto entidad
                    EntidadApartado apartadoArellenar =registrosDefinitivos.get(i);
                    //Le añadimos a la lista del Apartado creado el subApartado
                    apartadoArellenar.addSubApartadoLista(subApartadoAñadir);
                    //eliminamos el registro incompleto
                    registrosDefinitivos.remove(i);
                    //Añadimos el registro creado que esta completo
                    registrosDefinitivos.add(apartadoArellenar);
                    //Confirmamos su repeticion para no añadir este nombre
                    seRepitio=true;
                }
            }
            if(seRepitio==false){
                //En caso que en to-do el ciclo no llegase a entrar en el if simplemente añadiremos el nombre a la lista
                nombreApartado.add(nombreRecibido);
                //y como la variable sigue como el origen  podemos dejarla igual
            }else {
                //Aqui presupongo que ha entrado con lo que reseteamos el boolean
                seRepitio=false;
            }
        }
        return resutOrdenado;
    }

    private String inputStreamToString(InputStream inputStream) {
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
