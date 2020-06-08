package com.example.proyectotfgreal.Apartado;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.proyectotfgreal.R;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements  DialogLogin.ExampleDialogListener{
    public ArrayList<Entidad> entidad=new ArrayList() ;
    private Adaptador adaptador;
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager iManager;
    SharedPreferences sharedPreferences;
    String ipSeleccionada;
    String numeroUsuarioSeleccionado;
    //
    Boolean activoDialog=false;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Tipos de Coches");
        //shared preferences son las preferencias puedes quitarlo
        sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        String valorUsuario=sharedPreferences.getString("UsuarioConectado","");
        if(valorUsuario==""){
            //Aqui se llama al codigo de opendialog, junto al activoDialog son innecesdairos
            OpenDialog();
            activoDialog=true;
        }
        //
        setContentView(R.layout.activity_main);
        recycler= (RecyclerView) findViewById(R.id.ReciclerView);
        recycler.setHasFixedSize(true);
        iManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(iManager);
        llamarHttpClass();
    }
    private void llamarHttpClass() {
        GetHTTPApartados claes= new GetHTTPApartados(entidad,recycler, adapter,MainActivity.this);
        claes.execute();
    }
    /*
    Esto habre el dialog
     */
    public void OpenDialog(){
        Log.d("OpenDialog","Llego a el metodo");
        DialogLogin exampleDialog = new DialogLogin();
        Log.d("OpenDialog","Creo example");
        exampleDialog.show(getSupportFragmentManager(),"ExampleDialog");
        Log.d("OpenDialog","Supuestamente lo mostró");
    }

    /*
    apply texts forma parte del dialog
     */
    @Override
    public void applyTexts(String usuario, String ip) {
        numeroUsuarioSeleccionado=usuario;
        ipSeleccionada=ip;
        activoDialog=false;
        Toast.makeText(getApplicationContext(), "El numero de el usuario es "+numeroUsuarioSeleccionado+" y la ip es "+ipSeleccionada, Toast.LENGTH_LONG).show();
        editor=sharedPreferences.edit();
        editor.putString(getString(R.string.usuario), numeroUsuarioSeleccionado);
        editor.putString(getString(R.string.ip),ipSeleccionada);
        editor.commit();
    }
}