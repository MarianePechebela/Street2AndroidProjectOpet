package com.example.rafaelmatucheski.street2androidproject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rafael Matucheski on 30/04/2017.
 */

public class Util {

    public static String webToString(InputStream inputStream) {
        InputStream localStream = inputStream;
        String localString = "";
        Writer writer = new StringWriter();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(localStream, "UTF-8"));
            String line = reader.readLine();
            while (line != null) {
                writer.write(line);
                line = reader.readLine();
            }
            localString = writer.toString();
            writer.close();
            reader.close();
            localStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return localString;
    }

    public static String convertCadastrotoJSON(Cadastro cadastro) {
        JSONObject mainObject = new JSONObject();
        try {
            mainObject.put("nome_cadastro",cadastro.getNome());
            mainObject.put("usuario_cadastro",cadastro.getUsuario());
            mainObject.put("senha_cadastro",cadastro.getSenha());

            return mainObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Cadastro> convertJSONtoCadastro(String jsonFile) {
        List<Cadastro> cadastro = new ArrayList<>();
        try {
            JSONArray mainObject = new JSONArray(jsonFile);

            for (int i = 0; i < mainObject.length(); i++) {
                Cadastro novoCadastro = new Cadastro();
                JSONObject localObj = mainObject.getJSONObject(i);
                long id = localObj.getLong("id_cadastro");
                String nome = localObj.getString("nome_cadastro");
                String usuario = localObj.getString("usuario_cadastro");
                String senha = localObj.getString("senha_cadastro");
                novoCadastro.setID(id);
                novoCadastro.setNome(nome);
                novoCadastro.setUsuario(usuario);
                novoCadastro.setSenha(senha);
                cadastro.add(novoCadastro);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return cadastro;
    }

    public static String getStatusFromJSON(String json) {
        try {
            return new JSONObject(json).getString("status");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;

    }
}
