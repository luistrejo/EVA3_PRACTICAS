package com.luist.eva3_17_broadcast_a;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MiServicio extends Service {

    Thread tHilo;
    Intent inMensaje;

    public MiServicio() {
    }

    @Override
    public void onStart(final Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.wtf("MISERVICE", "ONSTART");
        inMensaje = new Intent("MI_SERVICIO");
        inMensaje.putExtra("MENSAJE", "ONSTART");
        sendBroadcast(inMensaje);

        tHilo = new Thread() {
            @Override
            public void run() {
                super.run();
                while (true) {
                    try {
                        Thread.sleep(1000);
                        Intent intent1 = new Intent("MI_SERVICIO");
                        inMensaje.putExtra("MENSAJE", "ONSTART");
                        sendBroadcast(intent);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        inMensaje.putExtra("MENSAJE", "ONDESTROY");
        sendBroadcast(inMensaje);
        tHilo.interrupt();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
