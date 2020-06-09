package com.example.proyectotfgreal.Apartado;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectotfgreal.R;

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
    public TextView textoUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentarios);
        Log.d("Linea","Llego a ejecutar la cosa");
        numeroRegistro =getIntent().getExtras().getInt("numeroIdentificadorModelo");
        recycler= (RecyclerView) findViewById(R.id.ReciclerView);
        recycler.setHasFixedSize(true);
        iManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(iManager);
        //metele el Lisener del boton hueoN
        btnEnvio=findViewById(R.id.btnInsertarComentario);
        textoInput=findViewById(R.id.inComentario2);
        textoUsuario=findViewById(R.id.inComentario);
        btnEnvio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comentario1;
                String usuario = textoUsuario.getText().toString();
                comentario1=textoInput.getText().toString();
                if(comentario1.equals("")){
                    Toast.makeText(getApplicationContext(),"Debes poner un comentario",Toast.LENGTH_SHORT).show();
                }else if(usuario.equals("")){

                }else
                    {
                    //Mandar comentario
                   // envioComentario(comentario, usuario
                    MandandoCosa xs = new MandandoCosa(comentario1,numeroRegistro+"");
                    String fecha =xs.getFechaActual();
                    String numeroModelo=xs.getNumeroModelo();
                    String comentario = xs.getComentario();
                    GetHTTPUsuario get = new GetHTTPUsuario(usuario,comentario, fecha, numeroModelo,v,getApplicationContext());
                    get.execute();
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
    /*
    private void envioComentario(String comentario, String usuario) {
        MandandoCosa xs = new MandandoCosa(usuario,comentario,numeroRegistro);
        String fecha =xs.getFechaActual();
        Log.d("Linea","La fecha es:"+fecha);
        String comentarioR=xs.getComentario();
        Log.d("Linea","La contenido es:"+comentarioR);
        String usuarioNumero =xs.getUsuario();
        Log.d("Linea","La usuario es:"+usuarioNumero);
        String numeroModelo =xs.getNumeroModelo()+"";
        Log.d("Linea","La numeroModelo es:"+numeroModelo);
        Context contectoR =getApplicationContext();
        GetHTTPInsercionComentario cas =  new GetHTTPInsercionComentario(fecha,comentarioR,usuarioNumero,numeroModelo,contectoR);
        cas.execute();
        finish();
        Intent iconIntent = new Intent(getApplicationContext(), ActivityComentariosDatos.class);
        iconIntent.putExtra("numeroIdentificadorModelo",xs.getNumeroModelo());
        getApplicationContext().startActivity(iconIntent);
    }
    */
}
