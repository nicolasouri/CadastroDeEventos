package br.senai.sc.cadastrodeeventos.modelo;

import java.io.Serializable;

public class Local implements Serializable {

    private int id;
    private String nomeLocal;
    private String bairro;
    private String cidade;
    private int capacidadePublico;

    public Local(int id, String nomeLocal, String bairro, String cidade, int capacidadePublico) {
        this.id = id;
        this.nomeLocal = nomeLocal;
        this.bairro = bairro;
        this.cidade = cidade;
        this.capacidadePublico = capacidadePublico;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeLocal() {
        return nomeLocal;
    }

    public void setNomeLocal(String nomeLocal) {
        this.nomeLocal = nomeLocal;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public int getCapacidadePublico() {
        return capacidadePublico;
    }

    public void setCapacidadePublico(int capacidadePublico) {
        this.capacidadePublico = capacidadePublico;
    }

    @Override
    public String toString() {
        return nomeLocal + ", " +
                bairro + ", " +
                cidade + ", " +
                "Maximo de pessoas: " + capacidadePublico;
    }
}

