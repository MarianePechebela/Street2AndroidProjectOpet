package com.example.rafaelmatucheski.street2androidproject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class InsertRotasActivity extends Activity {


    private EditText editNomeRotas;
    private EditText editEndinRotas;
    private EditText editEndfimRotas;
    private EditText editLinhaRotas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_rotas);
        editNomeRotas = (EditText) findViewById(R.id.editNomeRotas);
        editEndinRotas = (EditText) findViewById(R.id.editEndinRotas);
        editEndfimRotas = (EditText) findViewById(R.id.editEndfimRotas);
        editLinhaRotas = (EditText) findViewById(R.id.editLinhaRotas);
    }

    public void cadastrarRotas(View v){
        Rotas rotases = new Rotas();
        rotases.setNome(editNomeRotas.getText().toString());
        rotases.setEndin(editEndinRotas.getText().toString());
        rotases.setEndfim(editEndfimRotas.getText().toString());
        rotases.setLinha(editLinhaRotas.getText().toString());
        if(isConnected())
            new InsertRotasActivity.UploadToMyAPI().execute(rotases);
        else
            Toast.makeText(this,"Verifique a sua conexão.", Toast.LENGTH_SHORT).show();
    }

    private boolean isConnected(){
        ConnectivityManager cm =
                (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }
    private class UploadToMyAPI extends AsyncTask<Rotas, Void, String> {

        ProgressDialog progress;
        int serverResponseCode;
        String serverResponseMessage;

        @Override
        protected String doInBackground(Rotas... params) {
            HttpURLConnection urlConnection = null;
            try {
                URL url = new URL("http://street2.pe.hu/insertRotas.php");
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setRequestProperty("Content-type", "application/json");
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);

                DataOutputStream outputStream = new DataOutputStream(urlConnection.getOutputStream());

                String result = Util.convertCadastroRotastoJSON(params[0]);
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
            Intent listaRotas = null;
            if(Util.getStatusFromJSON(serverResponseMessage).equals("1")) {
                Toast.makeText(InsertRotasActivity.this, "Rota registrada com sucesso no sistema!", Toast.LENGTH_SHORT).show();
                listaRotas = new Intent(InsertRotasActivity.this, MenuuActivity.class);
                startActivity(listaRotas);
            }else{
                Toast.makeText(InsertRotasActivity.this, "Falha ao cadastrar essa rota.", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void selectMainOpetion(View view){
        Intent intent = null;
        switch (view.getId()){
            case R.id.btnVoltar:
                intent = new Intent(this,MenuuActivity.class);
                break;
        }
        if(intent != null){
            startActivity(intent);
        }
    }
}

