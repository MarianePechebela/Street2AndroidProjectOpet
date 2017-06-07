package com.example.rafaelmatucheski.street2androidproject;

/**
 * Created by Rafael Matucheski on 30/05/2017.
 */

public class Onibus {
    //private long ID;
    private String marca;
    private String modelo;
    private String ano;
    private String chassi;

    public Onibus(){

    }

    public Onibus(String marca, String modelo, String ano, String chassi){
        //this.ID = ID;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.chassi = chassi;
    }

    //public long getID() {
    //    return ID;
    //}

    //public void setID(long ID) {
      //  this.ID = ID;
    //}

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getChassi() {
        return chassi;
    }

    public void setChassi(String chassi) {
        this.chassi = chassi;
    }
}
