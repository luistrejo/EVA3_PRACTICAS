package com.luist.eva3_12_clima_json_hilos;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ClimaAdapter extends ArrayAdapter<Clima> {
    private Context cApp;
    private int iMiLayout;
    private List<Clima> acDatos;

    public ClimaAdapter(Context cApp, int iMiLayout, List<Clima> acDatos) {
        super(cApp, iMiLayout, acDatos);
        this.cApp = cApp;
        this.iMiLayout = iMiLayout;
        this.acDatos = acDatos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ImageView imgVwClima;
        TextView txtVwCd, txtVwTemp, txtVwClima, txtVwDesc;

        View vwMiLayout = convertView;
        if (vwMiLayout == null) { //No existe la fila, hay que crearla
            LayoutInflater layoutInflater = ((Activity) cApp).getLayoutInflater();
            vwMiLayout = layoutInflater.inflate(iMiLayout, parent, false);
        }
        imgVwClima = vwMiLayout.findViewById(R.id.iVClima);
        txtVwTemp = vwMiLayout.findViewById(R.id.txtTemp);
        txtVwCd = vwMiLayout.findViewById(R.id.txtCiudad);
        txtVwClima = vwMiLayout.findViewById(R.id.txtClima);
        txtVwDesc = vwMiLayout.findViewById(R.id.txtDesc);
        Clima cClimaCd = acDatos.get(position);
        imgVwClima.setImageResource(cClimaCd.getImgClima());
        txtVwTemp.setText(cClimaCd.getClima() + " ºF");
        txtVwCd.setText(cClimaCd.getCiudad());
        txtVwClima.setText(cClimaCd.getClima());
        txtVwDesc.setText(cClimaCd.getDesc_clima());
        return vwMiLayout;

    }
}
