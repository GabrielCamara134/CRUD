
package com.gabriel.CRUD_Atividade.model;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

@Table("pizzarias")
public class Pizzaria extends Model {

    public Long getId() {
        return getLong("id");
    }

    public String getNome() {
        return getString("nome");
    }

    public String getCnpj() {
        return getString("cnpj");
    }

    public String getTelefone() {
        return getString("telefone");
    }

    public String getEmail() {
        return getString("email");
    }

    public String getEndereco() {
        return getString("endereco");
    }

    public void setNome(String nome) {
        set("nome", nome);
    }

    public void setCnpj(String cnpj) {
        set("cnpj", cnpj);
    }

    public void setTelefone(String telefone) {
        set("telefone", telefone);
    }

    public void setEmail(String email) {
        set("email", email);
    }

    public void setEndereco(String endereco) {
        set("endereco", endereco);
    }
}
