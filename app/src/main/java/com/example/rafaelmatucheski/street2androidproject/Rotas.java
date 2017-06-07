package com.example.rafaelmatucheski.street2androidproject;

/**
 * Created by Rafael Matucheski on 05/06/2017.
 */

public class Rotas {
    private String nome;
    private String endin;
    private String endfim;
    private String linha;

    public Rotas(){}

    public Rotas(String nome, String endin, String endfim, String linha){
        this.nome = nome;
        this.endin = endin;
        this.endfim = endfim;
        this.linha = linha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndin() {
        return endin;
    }

    public void setEndin(String endin) {
        this.endin = endin;
    }

    public String getEndfim() {
        return endfim;
    }

    public void setEndfim(String endfim) {
        this.endfim = endfim;
    }

    public String getLinha() {
        return linha;
    }

    public void setLinha(String linha) {
        this.linha = linha;
    }
}
