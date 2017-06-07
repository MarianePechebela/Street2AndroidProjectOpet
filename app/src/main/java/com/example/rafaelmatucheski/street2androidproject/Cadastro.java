package com.example.rafaelmatucheski.street2androidproject;

/**
 * Created by Rafael Matucheski on 30/04/2017.
 */

public class Cadastro {
    private long ID;
    private String nome;
    private String usuario;
    private String password;
    private String email;

    public Cadastro() {
    }

    public Cadastro(String nome, String usuario, String password, String email)
    {
        this.ID = ID;
        this.nome = nome;
        this.usuario = usuario;
        this.password = password;
        this.email = email;

    }

    public long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}