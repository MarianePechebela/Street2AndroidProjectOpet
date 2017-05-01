package com.example.rafaelmatucheski.street2androidproject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.support.design.widget.FloatingActionButton;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by Rafael Matucheski on 30/04/2017.
 */

public class ListCadastroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar__cadastro);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                Intent cadastroPessoa = new Intent(ListCadastroActivity.this,InsertPessoaActivity.class);
                startActivity(cadastroPessoa);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        loadPessoa();

    }
    public void loadPessoa(){
        new DownloadFromMyAPI().execute();
    }

    private class DownloadFromMyAPI extends AsyncTask<Void, Void, String> {

        boolean isConnected = false;
        ProgressDialog progress;

        @Override
        protected void onPreExecute() {
            ConnectivityManager cm =
                    (ConnectivityManager)ListCadastroActivity.this.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            isConnected = activeNetwork != null &&
                    activeNetwork.isConnectedOrConnecting();
            if(isConnected) {
                progress = new ProgressDialog(ListCadastroActivity.this);
                progress.setMessage("Aguarde o Download dos Dados");
                progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progress.setProgress(0);
                progress.show();
            }
            else{
                Toast.makeText(ListCadastroActivity.this, "Verifique a conexão com a internet...", Toast.LENGTH_SHORT).show();
            }
        }
        @Override
        protected String doInBackground(Void... params) {
            HttpURLConnection urlConnection = null;
            try {
                URL url = new URL("http://street2.pe.hu/selectAll.php");
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setReadTimeout(10000);
                urlConnection.setConnectTimeout(15000);
                urlConnection.setRequestMethod("GET");
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);

                int test = urlConnection.getResponseCode();

                String result = Util.webToString(urlConnection.getInputStream());

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
                List<Cadastro> cadastros = Util.convertJSONtoCadastro(s);
                if(cadastros != null){
                    ArrayAdapter<Cadastro> pessoaArrayAdapter = new CadastroAdapter(ListCadastroActivity.this,R.layout.cadastro_item,cadastros);
                    ListView listaCadastros = (ListView) findViewById(R.id.listCadastros);
                    listaCadastros.setAdapter(pessoaArrayAdapter);
                }
                progress.dismiss();
            }

        }
    }

}
