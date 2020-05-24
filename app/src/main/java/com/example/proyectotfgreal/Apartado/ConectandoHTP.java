package com.example.proyectotfgreal.Apartado;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.proyectotfgreal.R;
import com.squareup.picasso.Picasso;
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

public class ConectandoHTP extends AsyncTask<Void, Void,String> {
    private EntidadDatos x;
    public int numeroSeleccionado;
    private ImageView imgFoto;
    private TextView inModelo, inMarca, inAno, inHP, inMotor, inCombustible, inTraccion,inTransmision, inMarcha, inMax, inCC, inCuerpo, inVersion, lbVersion;
    private Context contexto;
    private Handler handlerFoto;

    ConectandoHTP(ImageView imgFotoR, TextView inModeloR, TextView inMarcaR, TextView inAnoR, TextView inHPR,
                  TextView inMotorR, TextView inCombustibleR, TextView inTraccionR,
                  TextView inTransmisionR, TextView inMarchaR, TextView inMaxR, TextView inCCR,
                  TextView inCuerpoR, TextView inVersionR, TextView lbVersionR, int numeroSeleccionadoR, Context contextoR) {
        numeroSeleccionado=numeroSeleccionadoR;
        imgFoto = imgFotoR;
        inModelo = inModeloR;
        inMarca = inMarcaR;
        inAno = inAnoR;
        inHP = inHPR;
        inMotor = inMotorR;
        inCombustible = inCombustibleR;
        inTraccion = inTraccionR;
        inTransmision=inTransmisionR;
        inMarcha = inMarchaR;
        inMax = inMaxR;
        inCC = inCCR;
        inCuerpo = inCuerpoR;
        inVersion = inVersionR;
        lbVersion = lbVersionR;
        contexto =  contextoR;
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


    @Override
    protected String doInBackground(Void... voids) {
        String result = null;
        try {
            String[] parametros = {"idModelo", numeroSeleccionado+""};
            String ip = contexto.getString(R.string.ip);

            String wsURL = "http://"+ip+"/TFG/adacc.php?" + parametros[0] + "=" + parametros[1];
            URI url = new URI(wsURL);
            // Create connection
            HttpURLConnection myConnection = (HttpURLConnection)
                    url.toURL().openConnection();
            // Establecer método. Por defecto GET.
            myConnection.setRequestMethod("GET");
            InputStream in = new BufferedInputStream(myConnection.getInputStream());
            result = inputStreamToString(in);
            Log.d("DoInBackground", result);
            recogiendoRegistros(result);

        } catch (Exception e) {
            Log.e("Erro0r", "Erro0r cacth IP 191.168.15");
            Log.d("Erro0r", "Erro0r cacth IP 191.168.15 " + e);
        }
        return result;
    }
    private void recogiendoRegistros(String s) {
        Log.d("recogiendoRegistros", "Comienza el try");
        try {
            ///Error Sucede aqui salta a error cach json
            JSONObject jsonObject = new JSONObject(URLDecoder.decode(s, "UTF-8" ));
            JSONArray jsonArray = jsonObject.getJSONArray("coches");
            // JSONObject jsonObject = new JSONObject(responseStrBuilder.toString());
            Log.d("recogiendoRegistros", "Primer objeto del try");
            //JSONArray jsonArray = jsonObject.getJSONArray("coches");
            Log.d("recogiendoRegistros", "Array del try");
            //String idModelo = jsonArray.getJSONObject(it).getString("idModelo");
            // Log.d("OnPostExecute", "recoge idModelo"+idModelo+"");
            String nombreModelo =  jsonArray.getJSONObject(0).getString("Nombre");
            Log.d("recogiendoRegistros", "recoge nombreModelo "+nombreModelo+"");
            String nombreSerie =  jsonArray.getJSONObject(0).getString("Serie");
            Log.d("recogiendoRegistros", "recoge nombreSerie "+nombreSerie+"");
            String nombreMarca =  jsonArray.getJSONObject(0).getString("Marca");
            Log.d("recogiendoRegistros", "recoge nombreMarca "+nombreMarca+"");
            String fecha =  jsonArray.getJSONObject(0).getString("Fecha Lanzamiento");
            Log.d("recogiendoRegistros", "recoge nombreMarca "+fecha+"");
            String hp = jsonArray.getJSONObject(0).getString("HP");
            String nombreMotor =  jsonArray.getJSONObject(0).getString("Motor");
            Log.d("recogiendoRegistros", "recoge nombreMotor "+nombreMotor+"");
            String combustible =  jsonArray.getJSONObject(0).getString("Combustible");
            Log.d("recogiendoRegistros", "recoge combustible "+combustible+"");
            String traccion =  jsonArray.getJSONObject(0).getString("Traccion");
            Log.d("recogiendoRegistros", "recoge traccion "+traccion+"");
            String transmisionCompleta =  jsonArray.getJSONObject(0).getString("Transmision");
            Log.d("recogiendoRegistros", "recoge transmisionCompleta "+transmisionCompleta+"");
            String velocidadMaxima =  jsonArray.getJSONObject(0).getString("VelocidadMax");
            Log.d("recogiendoRegistros", "recoge velocidadMaxima "+velocidadMaxima+"");
            String cilindrada =  jsonArray.getJSONObject(0).getString("Cilindrada");
            Log.d("recogiendoRegistros", "recoge cilindrada "+cilindrada+"");
            String cuerpo =  jsonArray.getJSONObject(0).getString("Cuerpo");
            Log.d("recogiendoRegistros", "recoge cuerpo "+cuerpo+"");
            String urlModelo =  jsonArray.getJSONObject(0).getString("Imagen");
            String version;
            try {
                version = jsonArray.getJSONObject(0).getString("Version");
            }catch(JSONException e){
                Log.d("recogiendoRegistros",e.toString());
                version=null;
            }
            Log.d("recogiendoRegistros", "Registro Recibido:"+nombreModelo+"-"+urlModelo);
            x = new EntidadDatos(urlModelo, nombreModelo,nombreSerie,nombreMarca,
                    fecha,hp,nombreMotor,combustible,traccion,transmisionCompleta,
                    velocidadMaxima,cilindrada,cuerpo,version);
            Log.d("recogiendoRegistros", "Entrado en entidad credada "+x.getNombreModelo());
            colocaEntidad(x);
        }
        catch (JSONException e) {
            Log.d("recogiendoRegistros",e.toString());
        } catch (UnsupportedEncodingException e) {
            Log.d("recogiendoRegistros",e.toString());
            e.printStackTrace();
        }
    }
    private void colocaEntidad(final EntidadDatos x){
        Log.d("colocaEntidad","Nombre Modelo recogido: "+x.getNombreModelo());
        inModelo.setText(x.getNombreModelo());
        Log.d("colocaEntidad","Nombre Marca recogido: "+x.getMarca());
        inMarca.setText(x.getMarca());
        Log.d("colocaEntidad","Nombre Ano recogido: "+x.getAno());
        inAno.setText(x.getAno());
        inHP.setText(x.getHp());
        inMotor.setText(x.getMotor());
        inCombustible.setText(x.getTipoCombustible());
        inTraccion.setText(x.getTraccion());
        inTransmision.setText(x.getTransmision());
        inMarcha.setText(x.getMarchas());
        inMax.setText(x.getVelocidadMaxima());
        inCC.setText(x.getCilindrada());
        inCuerpo.setText(x.getCuerpo());
        if((x.getVersion().equals(""))||(x.getVersion()==null)){
            lbVersion.setVisibility(View.INVISIBLE);
            inVersion.setVisibility(View.INVISIBLE);
        }else{
            inVersion.setText(x.getVersion());
        }
        Log.d("colocaEntidad","Nombre img recogido: "+x.getImgFoto());
        try {
            handlerFoto= new Handler(Looper.getMainLooper());
            handlerFoto.post(new Runnable() {
                @Override
                public void run() {
                    Picasso.with(contexto).load(x.getImgFoto()).fit().into(imgFoto);
                }
            });
        }
        catch (Exception e){
            Log.d("colocaEntidad","Error picasso: "+e);
        }
        Log.d("colocaEntidad","Coloca imagen");
    }
}
