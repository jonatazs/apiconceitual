package com.jonatas.apiconceitual.domain.model.enums;

public enum TipoCliente {
    PESSOAFISICA (1, "Pessoa Física"),
    PESSOAJURICIA(2, "Pessoa jurídica" );


    private int cod;
    private String descricao;

    TipoCliente() {

    }

    TipoCliente(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }



    public int getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }


    public static TipoCliente toEnum(Integer cod){
        if (cod == null){
            return null;
        }

        for (TipoCliente x : TipoCliente.values()){
            if(cod.equals(x.getCod())){
                return x;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + cod);
    }

}
