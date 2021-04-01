package com.jonatas.apiconceitual.dto;

import com.jonatas.apiconceitual.domain.model.Categoria;

public class CategoriaDTO {

    private Long id;
    private String nome;

    public CategoriaDTO(){}
    public CategoriaDTO(Categoria obj){
        id = obj.getId();
        nome = obj.getNome();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
