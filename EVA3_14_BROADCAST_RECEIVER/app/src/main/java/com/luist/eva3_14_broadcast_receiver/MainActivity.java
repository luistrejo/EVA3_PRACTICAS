package com.luist.eva3_14_broadcast_receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Intent miServicio;
    TextView txtVwDatos;
    BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        miServicio = new Intent(this, MiServicio.class);
        txtVwDatos = findViewById(R.id.txtVwDatos);

        //CREAR EL BROADCAST
        broadcastReceiver = new MiBroadCast();
        //DECIRLE AL BROADCAST QUE SERVICIO DEBE ESCUCHAR
        IntentFilter ifMiServicio = new IntentFilter("MI_SERVICIO");
        registerReceiver(broadcastReceiver, ifMiServicio);

    }

    //INICIAR SERVICIO
    public void onClickIni(View v) {
        startService(miServicio);
    }

    //DETENER SERVICIO
    public void onClickFin(View v) {
        stopService(miServicio);
    }

    class MiBroadCast extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            //AQUI INTERPRETAMOS LOS DATOS
            String sCade = intent.getStringExtra("MENSAJE");
            txtVwDatos.append(sCade);
        }
    }
}
