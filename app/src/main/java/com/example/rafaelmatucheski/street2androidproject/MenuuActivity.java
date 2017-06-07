package com.example.rafaelmatucheski.street2androidproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.facebook.login.LoginManager;

public class MenuuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuu);
    }
    public void onLogout(View view) {

        LoginManager.getInstance().logOut();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("Exit", true);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
    public void selectMainOpetion(View view){
        Intent intent = null;
        switch (view.getId()){
            case R.id.btnOnibus:
                intent = new Intent(this, ListOnibussActivity.class);
                break;
            case R.id.btnTesteCadastroOnibus:
                intent = new Intent(this, InsertOnibussActivity.class);
                break;
            case R.id.btnCadastrarRotas:
                intent = new Intent(this, InsertRotasActivity.class);
                break;
            case R.id.btnRotas:
                intent = new Intent(this, ListRotasActivity.class);
        }
        if(intent != null){
            startActivity(intent);
        }
    }
}

