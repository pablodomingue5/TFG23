package com.example.proyectotfgreal.Apartado;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectotfgreal.Apartado.Adaptadores.AdaptadorComentarioDatos;
import com.example.proyectotfgreal.Apartado.Entidades.EntidadComentariosDatos;
import com.example.proyectotfgreal.Apartado.Entidades.MandandoCosa;
import com.example.proyectotfgreal.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

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
        recycler= findViewById(R.id.ReciclerView);
        recycler.setHasFixedSize(true);
        iManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(iManager);
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
                    Toast.makeText(getApplicationContext(),"Debes poner un usuario",Toast.LENGTH_SHORT).show();
                }else {
                    MandandoCosa xs = new MandandoCosa(comentario1,numeroRegistro+"");
                    InserccionComentario get = new InserccionComentario(usuario,xs.getComentario(), xs.getFechaActual(), xs.getNumeroModelo(),v,getApplicationContext());
                    get.execute();
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            finish();
                        }
                    }, 1500);

                }
            }
        });
        llamarHttpClass();
    }
    private void llamarHttpClass() {
        ConsultaComentarios claes= new ConsultaComentarios(entidadComentario,recycler, adaptador, numeroRegistro+"",ActivityComentariosDatos.this);
        claes.execute();
    }
}
