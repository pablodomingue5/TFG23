package com.example.proyectotfgreal.Apartado;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectotfgreal.R;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ActivityComentariosDatos extends Activity {
    int numeroRegistro;
    public List<EntidadComentariosDatos> entidadComentario=new ArrayList() ;
    private RecyclerView recycler;
    public AdaptadorComentarioDatos adaptador;
    private RecyclerView.LayoutManager iManager;
    public Button btnEnvio;
    public TextView textoInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentarios);
        numeroRegistro =getIntent().getExtras().getInt("numeroIdentificadorModelo");
        recycler= (RecyclerView) findViewById(R.id.ReciclerView);
        recycler.setHasFixedSize(true);
        iManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(iManager);
        //metele el Lisener del boton hueoN
        btnEnvio=findViewById(R.id.btnInsertarComentario);
        textoInput=findViewById(R.id.inComentario);
        btnEnvio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comentario;
                String usuario = 2+"";
                comentario=textoInput.getText().toString();
                if(comentario.equals("")){
                    //no hacer nada
                }else{
                    //Mandar comentario
                    envioComentario(comentario, usuario);
                    //Refrescar llamarHTTp
                }
            }
        });
        llamarHttpClass();
    }



    private void llamarHttpClass() {
        ConectandoHTTPComentario claes= new ConectandoHTTPComentario(entidadComentario,recycler, adaptador, numeroRegistro+"",ActivityComentariosDatos.this);
        claes.execute();
    }
    private void envioComentario(String comentario, String usuario) {
        MandandoCosa xs = new MandandoCosa(usuario,comentario,numeroRegistro);
        GetHTTPDatos cas =  new GetHTTPDatos(xs.getFechaActual(),xs.getComentario(),xs.getUsuario(),xs.getNumeroModelo(),getApplicationContext());
        cas.execute();
    }

}
