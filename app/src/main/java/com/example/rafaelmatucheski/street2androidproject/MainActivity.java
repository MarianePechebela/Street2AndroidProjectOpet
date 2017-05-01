package com.example.rafaelmatucheski.street2androidproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;


public class MainActivity extends Activity {

    private LoginButton loginButton;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton = (LoginButton) findViewById(R.id.btnLoginFacebook);
        callbackManager = CallbackManager.Factory.create();

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {


            @Override
            public void onSuccess(LoginResult loginResult) {
                goMainScreen();
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), R.string.cancel_login, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(), R.string.error_login, Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Para que quando clicar em voltar não vá para login e sim saia da aplicação.
    private void goMainScreen() {
        Intent intent = new Intent(this, Onibus_Lista.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void selectMainOpetion(View view){
        Intent intent = null;
        switch (view.getId()){
            case R.id.btnInserir:
                intent = new Intent(this,InsertPessoaActivity.class);
                break;
           // case R.id.btnListar:
               // intent = new Intent(this,ListCadastroActivity.class);
               // break;
        }
        if(intent != null){
            startActivity(intent);
        }
    }



    public void telaCadastro(View v)
    {
        Intent tela = new Intent(this, Criar_Cadastro.class );
        startActivity(tela);
    }

    public void telaOnibusLista(View v)
    {
        Intent tela = new Intent(this, Onibus_Lista.class );
        startActivity(tela);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);


    }

}
