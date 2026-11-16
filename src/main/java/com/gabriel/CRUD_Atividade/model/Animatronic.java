
package com.gabriel.CRUD_Atividade.model;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

@Table("animatronics")
public class Animatronic extends Model {

    public Long getId() {
        return getLong("id");
    }

    public String getNome() {
        return getString("nome");
    }

    public Double getPeso() {
        return getDouble("peso");
    }

    public String getCor() {
        return getString("cor");
    }

    public Double getPreco() {
        return getDouble("preco");
    }

    public void setNome(String nome) {
        set("nome", nome);
    }

    public void setPeso(Double peso) {
        set("peso", peso);
    }

    public void setCor(String cor) {
        set("cor", cor);
    }

    public void setPreco(Double preco) {
        set("preco", preco);
    }
}
