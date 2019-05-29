package com.luist.eva3_3_sincronizacion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView txtVwMensaje;
    Thread tHilo = new Thread() {
        @Override
        public void run() {
            super.run();
            while (true) {
                txtVwMensaje.setText("MODIFICADO POR EL HILO");
                try {
                    Thread.sleep(1000);
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
        txtVwMensaje = findViewById(R.id.txtMensa);
        txtVwMensaje.setText("AQUI NO HAY FALLA");
        tHilo.run();
    }
}
