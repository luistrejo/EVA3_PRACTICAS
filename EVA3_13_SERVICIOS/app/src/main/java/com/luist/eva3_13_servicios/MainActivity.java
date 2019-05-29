package com.luist.eva3_13_servicios;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Intent miServicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        miServicio = new Intent(this, MiServicio.class);
    }

    //INICIAR SERVICIO
    public void onClickIni(View v) {
        startService(miServicio);
    }

    //DETENER SERVICIO
    public void onClickFin(View v) {
        stopService(miServicio);
    }
}
