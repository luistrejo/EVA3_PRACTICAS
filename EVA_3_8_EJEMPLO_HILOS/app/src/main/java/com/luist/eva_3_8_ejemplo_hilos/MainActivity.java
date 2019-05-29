package com.luist.eva_3_8_ejemplo_hilos;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    ImageView imgVwMostrar;
    Bitmap bImagen;
    Handler hHandler = new Handler();
    Runnable rRunUI = new Runnable() {
        @Override
        public void run() {
            //AQUI VAMOS A MOSTRAR LA IMAGEN
            imgVwMostrar.setImageBitmap(bImagen);
        }
    };
    Thread tHilo = new Thread() {
        @Override
        public void run() {
            super.run();
            //AQUI VAMOS A CARGAR LA IMAGEN
            bImagen = cargarImagen("https://upload.wikimedia.org/wikipedia/commons/3/38/Leonardo_da_Vinci_-_presumed_self-portrait_-_WGA12798.jpg");
            hHandler.post(rRunUI);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgVwMostrar = findViewById(R.id.imgVwMostrar);
//        Bitmap bImagen = cargarImagen("https://upload.wikimedia.org/wikipedia/commons/3/38/Leonardo_da_Vinci_-_presumed_self-portrait_-_WGA12798.jpg");
//        imgVwMostrar.setImageBitmap(bImagen);
        tHilo.start();
    }

    private Bitmap cargarImagen(String url) {
        InputStream isImages = null;
        try {
            isImages = (InputStream) new URL(url).getContent();
            Bitmap bBitMap = BitmapFactory.decodeStream(isImages);
            return bBitMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
