package com.luist.eva3_12_clima_json_hilos;

import android.app.Dialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ListView.OnItemClickListener {
    List<Clima> lstCiudades = new ArrayList<>();
    ListView llCiudades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        llCiudades = findViewById(R.id.lstCiudades);
        //llCiudades.setAdapter(new ClimaAdapter(this, R.layout.layout_clima, acClimaCd));
        //llCiudades.setOnItemClickListener(this);

        ConexionClima ccClimaCiudad = new ConexionClima();
        ccClimaCiudad.execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Toast.makeText(this, acClimaCd[position].getDesc_clima(), Toast.LENGTH_LONG).show();
        final Dialog dlgMiDialogo;
        dlgMiDialogo = new Dialog(this);
        //El layput
        dlgMiDialogo.setContentView(R.layout.layout_clima);
        ImageView imgVwClima;
        TextView txtVwCd, txtVwTemp, txtVwClima, txtVwDesc;
        Button btnCerrar = dlgMiDialogo.findViewById(R.id.btnCerrar);
        imgVwClima = dlgMiDialogo.findViewById(R.id.iVClima);
        txtVwCd = dlgMiDialogo.findViewById(R.id.txtCiudad);
        txtVwClima = dlgMiDialogo.findViewById(R.id.txtClima);
        txtVwDesc = dlgMiDialogo.findViewById(R.id.txtDesc);


        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlgMiDialogo.cancel();
            }
        });
        dlgMiDialogo.show();
    }

    class ConexionClima extends AsyncTask<Void, Void, String> {

        //CONEXION, RECIBIR LOS DATOS COMO CADENA EN FORMATO JSON
        @Override
        protected String doInBackground(Void... voids) {
            String sUrl = "https://samples.openweathermap.org/data/2.5/find?lat=55.5&lon=37.5&cnt=10&appid=b6907d289e10d714a6e88b30761fae22";
            String sRes = "";

            //Conexion
            try {
                URL url = new URL(sUrl);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    //Buffered Reader
                    //InputStreamReader
                    BufferedReader brDatosJson = new BufferedReader(
                            new InputStreamReader(
                                    httpURLConnection.getInputStream()
                            )
                    );
                    sRes = brDatosJson.readLine();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return sRes;
        }

        //
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (!s.equals("")) { //RECIBIMOS DATOS
                try {
                    JSONObject jsonDatos = new JSONObject(s);
                    JSONArray jsnCiuades = jsonDatos.getJSONArray("list");
                    for (int x = 0; x < jsnCiuades.length(); x++) {
                        JSONObject jsActual = jsnCiuades.getJSONObject(x);
                        Clima cCiudad = new Clima();

                        cCiudad.setCiudad(jsActual.getString("name"));
                        JSONObject jsnMain = jsActual.getJSONObject("main");
                        cCiudad.setTemp(jsnMain.getInt("temp"));

                        JSONArray jsnClimas = jsActual.getJSONArray("weather");
                        JSONObject jsClima = jsnClimas.getJSONObject(0);
                        cCiudad.setClima(jsClima.getString("main"));
                        cCiudad.setDesc_clima(jsClima.getString("description"));

                        int iId = jsClima.getInt("id");
                        if (iId < 300) { //TORMENTAS
                            cCiudad.setImgClima(R.drawable.thunderstorm);
                        } else if (iId < 400) { //LLUVIA LIGERA
                            cCiudad.setImgClima(R.drawable.light_rain);
                        } else if (iId < 600) { //LLUVIA INTENSA
                            cCiudad.setImgClima(R.drawable.rainy);
                        } else if (iId < 700) { //NIEVE
                            cCiudad.setImgClima(R.drawable.snow);
                        } else if (iId < 800) { //FENOMENO ATMOSFERICO
                            cCiudad.setImgClima(R.drawable.atmospher);
                        } else if (iId < 801) { //DESPEJADO
                            cCiudad.setImgClima(R.drawable.sunny);
                        } else if (iId < 900) { //NUBLADO
                            cCiudad.setImgClima(R.drawable.cloudy);
                        } else {
                            cCiudad.setImgClima(R.drawable.tornado);
                        }
                        lstCiudades.add(cCiudad);
                    }
                    llCiudades.setAdapter(new ClimaAdapter(MainActivity.this, R.layout.layout_clima, lstCiudades));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
