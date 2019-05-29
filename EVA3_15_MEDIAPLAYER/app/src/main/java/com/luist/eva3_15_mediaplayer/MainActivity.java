package com.luist.eva3_15_mediaplayer;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mpReproductor = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mpReproductor = MediaPlayer.create(this, R.raw.rolitachida);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mpReproductor != null){
            mpReproductor.stop();
            mpReproductor.release();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mpReproductor != null){
            mpReproductor.start();
        }
    }
}
