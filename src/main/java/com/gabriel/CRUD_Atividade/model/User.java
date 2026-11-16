package com.gabriel.CRUD_Atividade.model;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

@Table("users")
public class User extends Model {

    public Long getId() {
        return getLong("id");
    }

    public String getName() {
        return getString("name");
    }

    public String getArroba() {
        return getString("arroba");
    }

    public String getCpf() {
        return getString("cpf");
    }

    public void setName(String name) {
        set("name", name);
    }

    public void setArroba(String arroba) {
        set("arroba", arroba);
    }

    public void setCpf(String cpf) {
        set("cpf", cpf);
    }
}