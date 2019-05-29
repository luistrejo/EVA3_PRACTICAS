package com.luist.eva_3_6_banner;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    ImageView imgVwBanner;
    SeekBar skBrSpeed;
    int iVal = 0;
    int iImagen = 0;
    int[] iImagenes = {
            android.R.drawable.alert_dark_frame,
            android.R.drawable.bottom_bar,
            android.R.drawable.divider_horizontal_bright,
            android.R.drawable.ic_btn_speak_now
    };

    Handler hnHandler = new Handler();
    Runnable rnBanner = new Runnable() {
        @Override
        public void run() {
            imgVwBanner.setImageResource(iImagenes[iImagen]);
            if (iImagen == 3) {
                iImagen = 0;
            } else {
                iImagen++;
            }
        }
    };
    Thread rHilo = new Thread() {
        @Override
        public void run() {
            super.run();
            while (true) {
                try {
                    Thread.sleep(iVal);
                    hnHandler.post(rnBanner);
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

        imgVwBanner = findViewById(R.id.imgVwBanner);
        skBrSpeed = findViewById(R.id.skBarSpeed);
        skBrSpeed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                iVal = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        rHilo.run();
    }
}
