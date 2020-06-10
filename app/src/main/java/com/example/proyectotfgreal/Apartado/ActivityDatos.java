package com.example.proyectotfgreal.Apartado;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.proyectotfgreal.R;

public class ActivityDatos  extends AppCompatActivity {
    ImageView imgFoto;
    TextView inModelo, inMarca, inAno, inHP, inMotor, inCombustible, inTraccion, inTransmision, inMarcha, inMax, inCC, inCuerpo, inVersion, lbVersion;
    Context contexto;
    Button btnComentarios;
    int numeroSeleccionado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);
        numeroSeleccionado = getIntent().getExtras().getInt("NumeroSeleccionado");
        imgFoto = findViewById(R.id.imageView);
        inModelo = findViewById(R.id.inNombre);
        inMarca = findViewById(R.id.inMarca);
        inAno = findViewById(R.id.inAno);
        inHP = findViewById(R.id.inHP);
        inMotor = findViewById(R.id.inMotor);
        inCombustible = findViewById(R.id.inCombustible);
        inTraccion = findViewById(R.id.inTraccion);
        inTransmision=findViewById(R.id.inTransmision);
        inMarcha = findViewById(R.id.inMarcha);
        inMax = findViewById(R.id.inMax);
        inCC = findViewById(R.id.inCC);
        inCuerpo = findViewById(R.id.inCuerpo);
        inVersion = findViewById(R.id.inVersion);
        lbVersion = findViewById(R.id.lbVersion);
        btnComentarios = findViewById(R.id.btn_mensaje);
        contexto = getApplicationContext();
        setTitle("Coche especifico");
        llamarHttpClass(numeroSeleccionado);
        btnComentarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iconIntent = new Intent (view.getContext(), ActivityComentariosDatos.class);
                iconIntent.putExtra("numeroIdentificadorModelo",numeroSeleccionado);
                view.getContext().startActivity(iconIntent);
            }
        });

    }
    private void llamarHttpClass(int numeroSeleccionadoD) {
        ConsultaVehiculoDatos claes= new ConsultaVehiculoDatos(imgFoto,inModelo,inMarca,inAno,inHP,inMotor,inCombustible,inTraccion,inTransmision,inMarcha,inMax,inCC,inCuerpo,inVersion,lbVersion, numeroSeleccionadoD, contexto);
        claes.execute();
    }
}
