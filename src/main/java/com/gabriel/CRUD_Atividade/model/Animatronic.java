package com.gabriel.CRUD_Atividade.model;

public class Animatronic {

    private Long id;
    private String nome;
    private Double peso;
    private String cor;
    private Double preco;

    public Animatronic() {
    }

    public Animatronic(
            Long id,
            String nome,
            Double peso,
            String cor,
            Double preco) {

        this.id = id;
        this.nome = nome;
        this.peso = peso;
        this.cor = cor;
        this.preco = preco;
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

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
