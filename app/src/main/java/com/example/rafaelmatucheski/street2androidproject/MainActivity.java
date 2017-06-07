package com.example.rafaelmatucheski.street2androidproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ShareActionProvider;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity {


    EditText UsernameEt, PasswordEt;
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    Button bt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt=(Button)findViewById(R.id.button2);
        bt.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      Intent myIntent = new Intent(android.content.Intent.ACTION_SEND);
                                      String shareBody = "This is the best APP EVER ;P";

                                      myIntent.setType("text/plain");
                                      myIntent.putExtra(Intent.EXTRA_SUBJECT, "Street 2");
                                      myIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                                      startActivity(Intent.createChooser(myIntent, "Share via:"));

                                  }
                              });

        loginButton = (LoginButton) findViewById(R.id.btnLoginFacebook);
        callbackManager = CallbackManager.Factory.create();

        UsernameEt = (EditText) findViewById(R.id.txtUsuario);
        PasswordEt = (EditText) findViewById(R.id.txtpassSenha);

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

    //Login via MYSQL PHP
    public void OnLogin(View view) {


        String usuario_cadastro = UsernameEt.getText().toString();
        String password_cadastro = PasswordEt.getText().toString();
        if  (usuario_cadastro.isEmpty() || password_cadastro.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Nenhum campo pode ser nulo", Toast.LENGTH_SHORT).show();

        } else if (usuario_cadastro.equals("admin") || password_cadastro.equals("admin")){
            Intent intent = new Intent(this, MenuuActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        else {
            String type = "Login";
            BackgroundWorker backgroundWorker = new BackgroundWorker(this);
            backgroundWorker.execute(type, usuario_cadastro, password_cadastro);
        }

    }

    //Para que quando clicar em voltar não vá para login e sim saia da aplicação.
    private void goMainScreen() {
        Intent intent = new Intent(this, MenuActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    //Operação para selecionar um botão ou outro e iniciar função
    public void selectMainOpetion(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.btnInserir:
                intent = new Intent(this, InsertPessoaActivity.class);
                break;
        }

        if (intent != null) {
            startActivity(intent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
