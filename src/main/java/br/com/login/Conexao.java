package br.com.login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String URL = "jdbc:mysql://localhost:3306/bd_login";
    private static final String USUARIO = "root";
    private static final String SENHA = "root123";

    public static Connection conectar() {
        try {
            Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);

            System.out.println("Conexao realizada com sucesso!");

            return conexao;

        } catch (SQLException erro) {
            System.out.println("Erro ao conectar: " + erro.getMessage());

            return null;
        }
    }
}