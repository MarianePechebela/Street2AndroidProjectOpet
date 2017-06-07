package com.example.rafaelmatucheski.street2androidproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Rafael Matucheski on 30/05/2017.
 */

public class OnibusAdapter extends ArrayAdapter<Onibus> {
    private List<Onibus> onibuses;
    private int layout;

    public OnibusAdapter(Context context, int resource, List<Onibus> onibuses) {
        super(context, resource, onibuses);
        this.onibuses = onibuses;
        layout = resource;
    }

    @Override
    public View getView(int position, View contentView, ViewGroup parent) {
        View localView = contentView;

        if (localView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            localView = inflater.inflate(layout, null);
        }

        Onibus onibuss = onibuses.get(position);

        if (onibuss != null) {
            //TextView textIDD = (TextView) localView.findViewById(R.id.textIDD);
            TextView textMarca = (TextView) localView.findViewById(R.id.textMarca);
            TextView textModelo = (TextView) localView.findViewById(R.id.textModelo);
            TextView textAno = (TextView) localView.findViewById(R.id.textAno);
            TextView textChassi = (TextView) localView.findViewById(R.id.textChassi);

            //if (textIDD != null) {
              //  textIDD.setText(String.valueOf(onibuss.getID()));
            //}
            if (textMarca != null) {
                textMarca.setText(onibuss.getMarca());
            }
            if (textModelo != null) {
                textModelo.setText(onibuss.getModelo());
            }
            if (textAno != null) {
                textAno.setText(onibuss.getAno());
            }
            if (textChassi != null) {
                textChassi.setText(onibuss.getChassi());
            }
        }
        return localView;
    }
}
