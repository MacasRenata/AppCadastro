package com.example.macas.appcadastro;

//******************************************************
//Instituto Federal de São Paulo - Campus Sertãozinho
//Disciplina......: M4DADM
//Programação de Computadores e Dispositivos Móveis
//Aluno...........: Renata Maçãs
//******************************************************

public class Contato {

    private String nome;
    private String endereco;
    private String empresa;


    Contato (String nome, String endereco, String empresa) {
        this.nome = nome;
        this.endereco = nome;
        this.empresa = empresa;


    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }



    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }



    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }





}
