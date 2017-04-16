package com.example.rafaelmatucheski.street2androidproject;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.os.Bundle;
import android.view.View;
import com.facebook.AccessToken;


/**
 * Created by Rafael Matucheski on 09/04/2017.
 */

public class Onibus_Lista extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onibus__lista);

        if (AccessToken.getCurrentAccessToken() == null) {
            goLoginScreen();
        }

    }

    private void goLoginScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    //Direciona para a tela "Login"
    public void telaLogin(View v)
    {
        Intent tela = new Intent(this, MainActivity.class );
        startActivity(tela);
    }

}
