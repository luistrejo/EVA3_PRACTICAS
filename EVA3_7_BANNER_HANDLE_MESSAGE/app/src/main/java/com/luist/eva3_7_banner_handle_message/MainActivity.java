package com.luist.eva3_7_banner_handle_message;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtVwMensaje;
    SeekBar skBarSpeed;
    int iVal = 0;
    int iImagen = 0;
    int[] iImagenes = {
            android.R.drawable.alert_dark_frame,
            android.R.drawable.bottom_bar,
            android.R.drawable.divider_horizontal_bright,
            android.R.drawable.ic_btn_speak_now
    };

    Handler hMiHandler = new Handler() {
        //PERTENECE AL HILO PRINCIPAL
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //PUEDEN INTERACTUAR CON LA INTERFAZ GRAFICA
            int iVal = (int) msg.obj;
            txtVwMensaje.append("HILO = " + msg.what + ", " + "contador = " + iVal);
        }
    };

    Runnable rHilo = new Runnable() {
        @Override
        public void run() {
            int iCont = 0;
            while (true) {
                Message msgMensa = hMiHandler.obtainMessage();
                hMiHandler.sendMessage(msgMensa);

                int iProg = (10 - skBarSpeed)
                iCont++;
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
        skBarSpeed = findViewById(R.id.skBarSpeed);
    }
}
