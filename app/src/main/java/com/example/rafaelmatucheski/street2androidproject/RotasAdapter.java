package com.example.rafaelmatucheski.street2androidproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import static com.example.rafaelmatucheski.street2androidproject.R.id.textEndin;
import static com.example.rafaelmatucheski.street2androidproject.R.id.textNome;

/**
 * Created by Suzana Skroch on 05/06/2017.
 */

public class RotasAdapter extends ArrayAdapter<Rotas> {
    private List<Rotas> rotases;
    private int layout;

    public RotasAdapter(Context context, int resource, List<Rotas> rotases) {
        super(context, resource, rotases);
        this.rotases = rotases;
        layout = resource;
    }

    @Override
    public View getView(int position, View contentView, ViewGroup parent) {
        View localView = contentView;

        if (localView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            localView = inflater.inflate(layout, null);
        }

        Rotas rotas = rotases.get(position);

        if (rotases != null) {
            //TextView textIDD = (TextView) localView.findViewById(R.id.textIDD);
            TextView textNome = (TextView) localView.findViewById(R.id.textNome);
            TextView textEndin = (TextView) localView.findViewById(R.id.textEndin);
            TextView textEndfim = (TextView) localView.findViewById(R.id.textEndfin);
            TextView textLinha = (TextView) localView.findViewById(R.id.textLinha);

            //if (textIDD != null) {
            //  textIDD.setText(String.valueOf(onibuss.getID()));
            //}
            if (textNome != null) {
                textNome.setText(rotas.getNome());
            }
            if (textEndin != null) {
                textEndin.setText(rotas.getEndin());
            }
            if (textEndfim != null) {
                textEndfim.setText(rotas.getEndfim());
            }
            if (textLinha != null) {
                textLinha.setText(rotas.getLinha());
            }
        }
        return localView;
    }
}

