package br.com.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.nio.charset.StandardCharsets;
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

    public String getUsuario() {
        return usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    private String gerarMD5(String texto) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(
                    texto.getBytes(StandardCharsets.UTF_8));

            StringBuilder resultado = new StringBuilder();

            for (byte b : bytes) {
                resultado.append(String.format("%02x", b));
            }

            return resultado.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean verificaUsuario(String usuario)
            throws SQLException {

        String sql = "SELECT usuario FROM usuario WHERE usuario = ?";

        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, usuario);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    public boolean cadastraUsuario() throws SQLException {

        if (verificaUsuario(usuario)) {
            return false;
        }

        String sql = "INSERT INTO usuario (nome, usuario, senha)"
                + " VALUES (?, ?, ?)";

        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nome);
            ps.setString(2, usuario);
            ps.setString(3, gerarMD5(senha));

            return ps.executeUpdate() == 1;
        }
    }

    public boolean validarLogin() throws SQLException {

        String sql = "SELECT usuario FROM usuario"
                + " WHERE usuario = ? AND senha = ?";

        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, usuario);
            ps.setString(2, gerarMD5(senha));

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    public boolean alteraUsuario() throws SQLException {

        String sql = "UPDATE usuario SET nome = ?, senha = ?"
                + " WHERE usuario = ?";

        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nome);
            ps.setString(2, gerarMD5(senha));
            ps.setString(3, usuario);

            return ps.executeUpdate() == 1;
        }
    }

    public boolean excluiUsuario() throws SQLException {

        String sql = "DELETE FROM usuario WHERE usuario = ?";

        try (Connection con = Conexao.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, usuario);

            return ps.executeUpdate() == 1;
        }
    }
}
