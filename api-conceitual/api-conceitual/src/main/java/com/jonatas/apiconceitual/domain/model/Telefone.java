package com.jonatas.apiconceitual.domain.model;

import javax.persistence.Entity;

@Entity
public class Telefone {

    private String numero;

    public Telefone(){

    }

    public Telefone(String numero) {
        this.numero = numero;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }


}
