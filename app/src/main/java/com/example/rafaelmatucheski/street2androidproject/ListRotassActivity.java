package com.example.rafaelmatucheski.street2androidproject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class ListRotassActivity extends AppCompatActivity {

    private List<Rotas> rotases;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_rotass);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                Intent cadastroRotas = new Intent(ListRotassActivity.this, MenuActivity.class);
                startActivity(cadastroRotas);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        loadRotas();
    }
    public void loadRotas() {
        if (isConnected())
            new ListRotassActivity.DownloadFromMyAPI().execute();
        else
            Toast.makeText(this, "Verifique a sua conexão com a internet ...", Toast.LENGTH_SHORT).show();
    }
    private boolean isConnected() {
        ConnectivityManager cm =
                (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }
    private class DownloadFromMyAPI extends AsyncTask<Void, Void, String> {
        ProgressDialog progress;

        @Override
        protected void onPreExecute() {

            progress = new ProgressDialog(ListRotassActivity.this);
            progress.setMessage("Fazendo download dos dados ...");
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress.setProgress(0);
            progress.show();

        }

        @Override
        protected String doInBackground(Void... params) {
            HttpURLConnection urlConnection = null;
            try {
                URL url = new URL("http://street2.pe.hu/selectRotas.php");
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
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            List<Rotas> rotases = Util.convertJSONtoCadastroRotas(s);
            if (rotases != null) {
                ArrayAdapter<Rotas> rotasAdapter = new RotasAdapter(ListRotassActivity.this, R.layout.rotas_item, rotases);
                ListView listaRotas = (ListView) findViewById(R.id.listaRotass);
                listaRotas.setAdapter(rotasAdapter);
            }
            progress.dismiss();
        }
    }
}