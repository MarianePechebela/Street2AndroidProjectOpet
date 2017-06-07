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

    //Convert o cadastro para Json de Usuário
    public static String convertCadastrotoJSON(Cadastro cadastro) {
        JSONObject mainObject = new JSONObject();
        try {
            mainObject.put("nome_cadastro",cadastro.getNome());
            mainObject.put("usuario_cadastro",cadastro.getUsuario());
            mainObject.put("password_cadastro",cadastro.getPassword());
            mainObject.put("email_cadastro",cadastro.getEmail());

            return mainObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Convert o Json para Cadastro de usuário
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
                String password = localObj.getString("password_cadastro");
                String email = localObj.getString("email_cadastro");
                novoCadastro.setID(id);
                novoCadastro.setNome(nome);
                novoCadastro.setUsuario(usuario);
                novoCadastro.setPassword(password);
                novoCadastro.setEmail(email);
                cadastro.add(novoCadastro);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return cadastro;
    }

    public static String convertCadastroOnibustoJSON(Onibus onibuscads) {
        JSONObject mainObject = new JSONObject();
        try {
            mainObject.put("marca",onibuscads.getMarca());
            mainObject.put("modelo",onibuscads.getModelo());
            mainObject.put("ano",onibuscads.getAno());
            mainObject.put("chassi",onibuscads.getChassi());

            return mainObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Onibus> convertJSONtoCadastroOnibus(String jsonFile) {
        List<Onibus> onibuscad = new ArrayList<>();
        try {
            JSONArray mainObject = new JSONArray(jsonFile);

            for (int i = 0; i < mainObject.length(); i++) {

                Onibus novoOnibus = new Onibus();

                JSONObject localObj = mainObject.getJSONObject(i);
                //long id = localObj.getLong("id");
                String marca = localObj.getString("marca");
                String modelo = localObj.getString("modelo");
                String ano  = localObj.getString("ano");
                String chassi = localObj.getString("chassi");
                //novoOnibus.setID(id);

                novoOnibus.setMarca("Marca:  ".concat(marca));
                novoOnibus.setModelo("Modelo:  ".concat(modelo));
                novoOnibus.setAno("Ano:  ".concat(ano));
                novoOnibus.setChassi("Chassi:  ".concat(chassi));
                onibuscad.add(novoOnibus);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return onibuscad;
    }

    public static List<Rotas> convertJSONtoCadastroRotas(String jsonFile) {
        List<Rotas> rotases = new ArrayList<>();
        try {
            JSONArray mainObject = new JSONArray(jsonFile);

            for (int i = 0; i < mainObject.length(); i++) {

                Rotas novaRota = new Rotas();

                JSONObject localObj = mainObject.getJSONObject(i);
                //long id = localObj.getLong("id");
                String nome = localObj.getString("nome");
                String endin = localObj.getString("endin");
                String endfim  = localObj.getString("endfim");
                String linha = localObj.getString("linha");
                //novoOnibus.setID(id);

                novaRota.setNome("Nome:  ".concat(nome));
                novaRota.setEndin("Endereço Inicial:  ".concat(endin));
                novaRota.setEndfim("Endereço Final:  ".concat(endfim));
                novaRota.setLinha("Linha:  ".concat(linha));
                rotases.add(novaRota);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rotases;
    }
    public static String convertCadastroRotastoJSON(Rotas rotases) {
        JSONObject mainObject = new JSONObject();
        try {
            mainObject.put("nome",rotases.getNome());
            mainObject.put("endin",rotases.getEndin());
            mainObject.put("endfim",rotases.getEndfim());
            mainObject.put("linha",rotases.getLinha());

            return mainObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
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

