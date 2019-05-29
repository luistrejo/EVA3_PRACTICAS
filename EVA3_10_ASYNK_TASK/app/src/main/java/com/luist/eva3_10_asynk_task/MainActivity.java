package com.luist.eva3_10_asynk_task;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView txtVwMensa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtVwMensa = findViewById(R.id.txtVwMensa);
        MiClaseAsinc mObjClaseAsin = new MiClaseAsinc();
        mObjClaseAsin.execute(1000, 2, 500);
    }

    class MiClaseAsinc extends AsyncTask<Integer, String, Void> {

        //SE PUEDE INTERACTUAR CON LA UI
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            txtVwMensa.setText("INICIANDO TAREA ASINCRONA!!");
        }

        //SE PUEDE INTERACTUAR CON LA UI
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        //SE PUEDE INTERACTUAR CON LA UI
        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            if (values.length > 0) {
                txtVwMensa.append(values[0]);
            }
        }

        //NO PUEDE INTERACTUAR CON LA UI
        //EQUIVALENTE DEL METODO RUN
        @Override
        protected Void doInBackground(Integer... integers) {
            int i = 0;
            while (i < 15) {
                try {
                    Thread.sleep(500);
                    publishProgress("Hola " + i);
                    i++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }
}
