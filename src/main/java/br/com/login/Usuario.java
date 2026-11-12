package br.com.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Usuario {

    private String nome;
    private String usuario;
    private String senha;

    public Usuario(String nome, String usuario, String senha) {
        this.nome = nome;
        this.usuario = usuario;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    // Verifica se o usuario ja esta cadastrado
    public static boolean verificaUsuario(String usuario) {

        String sql = "SELECT usuario FROM usuario WHERE usuario = ?";

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setString(1, usuario);

            ResultSet resultado = comando.executeQuery();

            return resultado.next();

        } catch (SQLException erro) {
            System.out.println("Erro ao verificar usuario: " + erro.getMessage());
            return false;
        }
    }

  
    private String gerarMD5(String senha) {

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] resumo = md.digest(senha.getBytes());

            StringBuilder resultado = new StringBuilder();

            for (byte b : resumo) {
                resultado.append(String.format("%02x", b));
            }

            return resultado.toString();

        } catch (NoSuchAlgorithmException erro) {
            throw new RuntimeException("Erro ao gerar MD5", erro);
        }
    }

    // Cadastra o usuario no banco de dados
    public boolean cadastraUsuario() {

        String sql = "INSERT INTO usuario (usuario, nome, senha) VALUES (?, ?, ?)";

        if (verificaUsuario(this.usuario)) {
            System.out.println("Este usuario ja esta cadastrado!");
            return false;
        }

        try (Connection conexao = Conexao.conectar();
             PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setString(1, this.usuario);
            comando.setString(2, this.nome);
            comando.setString(3, gerarMD5(this.senha));

            comando.executeUpdate();

            System.out.println("Usuario cadastrado com sucesso!");
            return true;

        } catch (SQLException erro) {
            System.out.println("Erro ao cadastrar usuario: " + erro.getMessage());
            return false;
        }
    }
    // Verifica se o usuario e a senha estao corretos
public boolean validarLogin() {

    String sql = "SELECT * FROM usuario WHERE usuario = ? AND senha = ?";

    try (Connection conexao = Conexao.conectar();
         PreparedStatement comando = conexao.prepareStatement(sql)) {

        comando.setString(1, this.usuario);
        comando.setString(2, gerarMD5(this.senha));

        ResultSet resultado = comando.executeQuery();

        return resultado.next();

    } catch (SQLException erro) {
        System.out.println("Erro ao validar login: " + erro.getMessage());
        return false;
    }
}
// Altera os dados do usuario no banco de dados
public boolean alteraUsuario() {

    String sql = "UPDATE usuario SET nome = ?, senha = ? WHERE usuario = ?";

    try (Connection conexao = Conexao.conectar();
         PreparedStatement comando = conexao.prepareStatement(sql)) {

        comando.setString(1, this.nome);
        comando.setString(2, gerarMD5(this.senha));
        comando.setString(3, this.usuario);

        int linhasAlteradas = comando.executeUpdate();

        if (linhasAlteradas > 0) {
            System.out.println("Usuario alterado com sucesso!");
            return true;
        }

        System.out.println("Usuario nao encontrado!");
        return false;

    } catch (SQLException erro) {
        System.out.println("Erro ao alterar usuario: " + erro.getMessage());
        return false;
    }
}
// Exclui o usuario do banco de dados
public boolean excluiUsuario() {

    String sql = "DELETE FROM usuario WHERE usuario = ?";

    try (Connection conexao = Conexao.conectar();
         PreparedStatement comando = conexao.prepareStatement(sql)) {

        comando.setString(1, this.usuario);

        int linhasExcluidas = comando.executeUpdate();

        if (linhasExcluidas > 0) {
            System.out.println("Usuario excluido com sucesso!");
            return true;
        }

        System.out.println("Usuario nao encontrado!");
        return false;

    } catch (SQLException erro) {
        System.out.println("Erro ao excluir usuario: " + erro.getMessage());
        return false;
    }
}
}
