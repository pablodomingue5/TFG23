package com.example.proyectotfgreal.Apartado;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.proyectotfgreal.R;



public class DialogLogin extends AppCompatDialogFragment {
    TextView inputUsuario;
    TextView inputIp;
    AlertDialog.Builder builder;
    LayoutInflater inflater;
//Completamente innecesario entonces
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener=(ExampleDialogListener)context;
        } catch (Exception e) {
            throw  new ClassCastException(context.toString()+"must implement ExampleDialogListener");
        }
    }

    View view;
    String numeroUsuario;
    String ipSeleccionada;
    private ExampleDialogListener listener;
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        builder = new AlertDialog.Builder(getActivity());
        inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.layout_dialog_login,null);
        inputUsuario = view.findViewById(R.id.loginUsuarioIn);
        inputIp = view.findViewById(R.id.loginIpIn);
        builder.setView(view).setTitle("Logeo")
                .setNegativeButton("Borrar Datos", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                inputIp.setText("");
                                inputUsuario.setText("");
                            }
                        })
                        .setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String nombreUsuario=inputUsuario.getText().toString();
                        ipSeleccionada=inputIp.getText().toString();
                        if(nombreUsuario.equals("")){
                            Toast.makeText(getContext(), "Escriba un nombre de usuario", Toast.LENGTH_SHORT).show();
                        }else if(!ipSeleccionada.contains(".")){
                            Toast.makeText(getContext(), "Escriba un numero de ipCorrecto", Toast.LENGTH_SHORT).show();
                        }else{
                            //Lo que tienes que usar particularmente edl código es exto el contexto lo recibe de la original, pero verdaderamente no hace nada asi
                            //lo puedes modificar
                            GetHTTPUsuario get = new GetHTTPUsuario(nombreUsuario,getContext());
                            get.execute();
                            try {
                                Thread.sleep(10*1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            numeroUsuario=get.getNumeroResultante();
                            Log.d("EnvioSentencia","Se acabo antes "+numeroUsuario);
                            listener.applyTexts(numeroUsuario,ipSeleccionada);
                        }
                    }
                });
        return builder.create();
    }
    public interface ExampleDialogListener{
        void applyTexts(String usuario, String ip);
    }
}
