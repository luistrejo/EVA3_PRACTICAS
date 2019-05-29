package com.luist.eva3_5_handler_post;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtMensaje;

    //HANDLER
    Handler hMiHandler = new Handler();
    //UN RUNNABLE QUE VA A INTERACTUAR CON LA INTERFAZ GRAFICA
    Runnable rRunInterfaz = new Runnable() {
        @Override
        public void run() {
            //AQUI SI PODEMOS INTERACTUAR CON LA UI
            txtMensaje.append("Hola Mundo");
        }
    };
    //UN HILO QUE HACE EL TRABAJO EN 2DO PLANO
    Thread tHiloPpal = new Thread() {
        @Override
        public void run() {
            super.run();
            while (true) {
                try {
                    Thread.sleep(1000);
                    hMiHandler.post(rRunInterfaz);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtMensaje = findViewById(R.id.txtPrincipal);
        tHiloPpal.run();
    }
}
