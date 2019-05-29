package com.luist.eva3_11_banner_asynk_task;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
    ImageView imgVwBanner;
    SeekBar skBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgVwBanner = findViewById(R.id.imgVwBanner);
        skBar = findViewById(R.id.skBarSpeed);

        MiClaseAsinc mObjClaseAsin = new MiClaseAsinc();
        mObjClaseAsin.execute();
    }

    class MiClaseAsinc extends AsyncTask<Integer, Integer, Void> {
        int[] iImagenes = {
                android.R.drawable.alert_dark_frame,
                android.R.drawable.bottom_bar,
                android.R.drawable.divider_horizontal_bright,
                android.R.drawable.ic_btn_speak_now
        };

        //SE PUEDE INTERACTUAR CON LA UI
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        //SE PUEDE INTERACTUAR CON LA UI
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            if (values.length > 0) {
                imgVwBanner.setImageResource(iImagenes[values[0]]);
            }
        }

        //NO PUEDE INTERACTUAR CON LA UI
        //EQUIVALENTE DEL METODO RUN
        @Override
        protected Void doInBackground(Integer... integers) {
            int i = 0;
            while (true) {
                try {
                    Thread.sleep(skBar.getProgress());
                    publishProgress(i);
                    i++;
                    if (i == 4) {
                        i = 0;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
