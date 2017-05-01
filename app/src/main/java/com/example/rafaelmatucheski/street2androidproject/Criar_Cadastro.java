package com.example.rafaelmatucheski.street2androidproject;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Rafael Matucheski on 16/04/2017.
 */

public class Criar_Cadastro extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar__cadastro);
    }
    //Direciona para a tela "Login"
    public void telaLogin(View v)
    {
        Intent tela = new Intent(this, MainActivity.class );
        startActivity(tela);
    }

    //Direciona para a página "Onibus Disponivel"
    public void telaonibusDisponivel(View v)
    {
        Intent tela = new Intent(this, Onibus_Lista.class );
        startActivity(tela);
    }
}


