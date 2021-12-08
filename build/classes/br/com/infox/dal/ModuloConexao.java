package br.com.infox.dal;

import java.sql.*;

public class ModuloConexao {

    // metodo responsavel por estabelecer a conexão com o banco
    // connection é um framework que veio de java.sql.*
    // conection é o nome do metodo
    public static Connection conector() { // metodo conector
        // criando uma variavel especial
        // conexao é o nome da variavel
        // java.sql é a biblioteca
        java.sql.Connection conexao = null;

        // a linha abaixo "chama" o driver que eu impotei da biblioteca
        // entre "" é o caminho
        String driver = "com.mysql.cj.jdbc.Driver";

        // Armazenando informações referente ao banco
        // 3 variaveis
        String url = "jdbc:mysql://localhost:3306/dbinfox";
        String user = "root";
        String password = "";
        // alt + shift + f (identação do codigo)

        // Estabelecendo a conxão com o banco de dados
        // try faz o tratamento das excessões
        try {
            Class.forName(driver);
            // a linha acima executa o arquivo do driver
            conexao = DriverManager.getConnection(url, user, password);
            return conexao;
        } catch (Exception e) {
            // A linha abaixo mostra o erro da conexão
            // System.out.println("ERRO DE CONEXÃO: "+ e);
            return null;
        }
        // Esse modulo de conexão, não possui um main, ou seja
        // é nescessario fazer a TelaLogin, chamar essa classe

    }
}