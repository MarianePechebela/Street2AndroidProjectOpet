package com.example.rafaelmatucheski.street2androidproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Rafael Matucheski on 30/04/2017.
 */

public class CadastroAdapter extends ArrayAdapter<Cadastro> {
    private List<Cadastro> cadastros;
    private int layout;

    public CadastroAdapter(Context context, int resource, List<Cadastro> cadastros) {
        super(context, resource, cadastros);
        this.cadastros = cadastros;
        layout = resource;
    }

    @Override
    public View getView(int position, View contentView, ViewGroup parent) {
        View localView = contentView;

        if (localView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            localView = inflater.inflate(layout, null);
        }

        Cadastro cadastro = cadastros.get(position);

        if(cadastro != null){
            TextView textID = (TextView) localView.findViewById(R.id.textID);
            TextView textNome = (TextView) localView.findViewById(R.id.textNome);
            TextView textUsuario = (TextView) localView.findViewById(R.id.textUsuario);
            TextView textSenha = (TextView) localView.findViewById(R.id.textSenha);

            if(textID != null){
                textID.setText(String.valueOf(cadastro.getID()));
            }
            if(textNome != null){
                textNome.setText(cadastro.getNome());
            }
            if(textUsuario != null){
                textUsuario.setText(cadastro.getUsuario());
            }
            if(textSenha != null){
                textSenha.setText(cadastro.getSenha());
            }
        }
        return localView;
    }
}
