
package com.gabriel.CRUD_Atividade.conf;

import org.javalite.activejdbc.Base;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializerUser implements CommandLineRunner {

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

            String sqlCreate =
                    "CREATE TABLE IF NOT EXISTS users (" +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            "name VARCHAR(100) NOT NULL, " +
                            "arroba VARCHAR(100) NOT NULL, " +
                            "cpf VARCHAR(14) NOT NULL" +
                            ")";

            Base.exec(sqlCreate);

            System.out.println("Tabela users verificada com sucesso!");
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
