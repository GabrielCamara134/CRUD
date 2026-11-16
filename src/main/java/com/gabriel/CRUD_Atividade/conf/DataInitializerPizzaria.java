
package com.gabriel.CRUD_Atividade.conf;

import org.javalite.activejdbc.Base;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializerPizzaria implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Conectando ao banco SQLite...");

        try {
            Base.open(
                    "org.sqlite.JDBC",
                    "jdbc:sqlite:meu_app.db",
                    "",
                    ""
            );

            // Cria a tabela caso ela ainda não exista
            String sqlCreate =
                    "CREATE TABLE IF NOT EXISTS pizzarias (" +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            "nome VARCHAR(100) NOT NULL, " +
                            "cnpj VARCHAR(18) NOT NULL, " +
                            "telefone VARCHAR(20) NOT NULL, " +
                            "email VARCHAR(150) NOT NULL, " +
                            "endereco VARCHAR(255) NOT NULL" +
                            ")";

            Base.exec(sqlCreate);

            System.out.println("Tabela pizzarias verificada com sucesso!");
            System.out.println("Banco de dados conectado!");

        } catch (Exception e) {

            System.err.println("Erro ao inicializar banco: " + e.getMessage());
            throw new RuntimeException(e);

        } finally {

            if (Base.hasConnection()) {
                Base.close();
            }

            System.out.println("Conexão encerrada.");
        }
    }
}
