package com.luist.eva3_16_mediaplayerservicio;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class ServicioMultimedia extends Service {
    MediaPlayer mMultimedia = null;

    public ServicioMultimedia() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mMultimedia = MediaPlayer.create(getApplicationContext(), R.raw.rolitachida);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        if (mMultimedia != null) {
            mMultimedia.start();
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mMultimedia != null) {
            mMultimedia.stop();
            mMultimedia.release();
        }
    }
}
