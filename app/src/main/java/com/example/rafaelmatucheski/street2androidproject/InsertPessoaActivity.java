package com.example.rafaelmatucheski.street2androidproject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Rafael Matucheski on 30/04/2017.
 */

public class InsertPessoaActivity extends Activity {

    private EditText editNomePessoa;
    private EditText editUsuarioPessoa;
    private EditText editSenhaUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_pessoa);
        editNomePessoa = (EditText) findViewById(R.id.editNomePessoa);
        editUsuarioPessoa = (EditText) findViewById(R.id.editUsuarioPessoa);
        editSenhaUsuario = (EditText) findViewById(R.id.editSenhaPessoa);
    }
    public void cadastrarUsuario(View v){
        Cadastro cadastro = new Cadastro();
        cadastro.setNome(editNomePessoa.getText().toString());
        cadastro.setUsuario(editUsuarioPessoa.getText().toString());
        cadastro.setSenha(editSenhaUsuario.getText().toString());
        new UploadToMyAPI().execute(cadastro);

    }

    private class UploadToMyAPI extends AsyncTask<Cadastro, Void, String> {

        boolean isConnected = false;
        ProgressDialog progress;
        int serverResponseCode;
        String serverResponseMessage;
        @Override
        protected void onPreExecute(){
            ConnectivityManager cm =
                    (ConnectivityManager)InsertPessoaActivity.this.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            isConnected = activeNetwork != null &&
                    activeNetwork.isConnectedOrConnecting();

            if(!isConnected) {
                Toast.makeText(InsertPessoaActivity.this, "Verifique a conexão com a internet...", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected String doInBackground(Cadastro... params) {
            HttpURLConnection urlConnection = null;
            try {
                URL url = new URL("http://street2.pe.hu/insert.php");
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setRequestProperty("Content-type", "application/json");
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);

                DataOutputStream outputStream = new DataOutputStream(urlConnection.getOutputStream());

                String result = Util.convertCadastrotoJSON(params[0]);
                outputStream.writeBytes(result);

                serverResponseCode = urlConnection.getResponseCode();
                serverResponseMessage = Util.webToString(urlConnection.getInputStream());

                outputStream.flush();
                outputStream.close();

                return result;
            } catch (Exception e) {
                Log.e("Error", "Error ", e);
                return null;
            } finally{
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(isConnected)
            {
                Intent listaCadastro = null;
                if(Util.getStatusFromJSON(serverResponseMessage).equals("1")) {
                    Toast.makeText(InsertPessoaActivity.this, "Usuario Cadastrado!", Toast.LENGTH_SHORT).show();
                    listaCadastro = new Intent(InsertPessoaActivity.this, ListCadastroActivity.class);
                    startActivity(listaCadastro);
                }else{
                    Toast.makeText(InsertPessoaActivity.this, "Falha ao cadastrar usuário.", Toast.LENGTH_SHORT).show();
                }
            }

        }
    }
}