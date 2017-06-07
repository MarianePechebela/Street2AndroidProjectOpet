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

public class InsertOnibusActivity extends Activity {

    private EditText editMarcaOnibus;
    private EditText editModeloOnibus;
    private EditText editAnoOnibus;
    private EditText editChassiOnibus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_onibus);
        editMarcaOnibus = (EditText) findViewById(R.id.editMarcaOnibus);
        editModeloOnibus = (EditText) findViewById(R.id.editModeloOnibus);
        editAnoOnibus = (EditText) findViewById(R.id.editAnoOnibus);
        editChassiOnibus = (EditText) findViewById(R.id.editChassiOnibus);
    }

    public void cadastrarOnibus(View v){
        Onibus onibuss = new Onibus();
        onibuss.setMarca(editMarcaOnibus.getText().toString());
        onibuss.setModelo(editModeloOnibus.getText().toString());
        onibuss.setAno(editAnoOnibus.getText().toString());
        onibuss.setChassi(editChassiOnibus.getText().toString());
        if(isConnected())
            new UploadToMyAPI().execute(onibuss);
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
    private class UploadToMyAPI extends AsyncTask<Onibus, Void, String> {

        ProgressDialog progress;
        int serverResponseCode;
        String serverResponseMessage;

        @Override
        protected String doInBackground(Onibus... params) {
            HttpURLConnection urlConnection = null;
            try {
                URL url = new URL("http://street2.pe.hu/insertOnibus.php");
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setRequestProperty("Content-type", "application/json");
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);

                DataOutputStream outputStream = new DataOutputStream(urlConnection.getOutputStream());

                String result = Util.convertCadastroOnibustoJSON(params[0]);
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
            Intent listaOnibus = null;
            if(Util.getStatusFromJSON(serverResponseMessage).equals("1")) {
                Toast.makeText(InsertOnibusActivity.this, "Onibus registrado com sucesso no sistema!", Toast.LENGTH_SHORT).show();
                listaOnibus = new Intent(InsertOnibusActivity.this, ListOnibusActivity.class);
                startActivity(listaOnibus);
            }else{
                Toast.makeText(InsertOnibusActivity.this, "Falha ao cadastrar o Onibus.", Toast.LENGTH_SHORT).show();
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
