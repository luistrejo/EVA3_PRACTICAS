package com.luist.eva3_16_mediaplayerservicio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    Intent inMultimedia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inMultimedia = new Intent(this, ServicioMultimedia.class);
    }

    public void iniciar(View view) {
        startService(inMultimedia);
    }

    public void detener(View view) {
        stopService(inMultimedia);
    }
}
