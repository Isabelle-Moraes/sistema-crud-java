
package br.com.login;

import javax.swing.*;
import java.awt.*;

public class TelaCadastro extends JFrame {

    private JTextField campoNome;
    private JTextField campoUsuario;
    private JPasswordField campoSenha;

    public TelaCadastro() {

        setTitle("Cadastro de Usuário");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(4, 2, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        painel.add(new JLabel("Nome completo:"));
        campoNome = new JTextField();
        painel.add(campoNome);

        painel.add(new JLabel("Usuário:"));
        campoUsuario = new JTextField();
        painel.add(campoUsuario);

        painel.add(new JLabel("Senha:"));
        campoSenha = new JPasswordField();
        painel.add(campoSenha);

        JButton botaoCadastrar = new JButton("Cadastrar");
        JButton botaoVoltar = new JButton("Voltar ao Login");

        JPanel painelBotoes = new JPanel(new FlowLayout());
        painelBotoes.add(botaoVoltar);
        painelBotoes.add(botaoCadastrar);

        painel.add(new JLabel());
        painel.add(painelBotoes);

        botaoCadastrar.addActionListener(e -> cadastrar());

        botaoVoltar.addActionListener(e -> {
            new TelaLogin().setVisible(true);
            dispose();
        });

        add(painel);
    }

    private void cadastrar() {

        String nome = campoNome.getText();
        String usuario = campoUsuario.getText();
        String senha = new String(campoSenha.getPassword());

        if (nome.isBlank() || usuario.isBlank() || senha.isBlank()) {
            JOptionPane.showMessageDialog(this,
                    "Preencha todos os campos!");
            return;
        }

        Usuario novoUsuario = new Usuario(nome, usuario, senha);

        if (novoUsuario.cadastraUsuario()) {
            JOptionPane.showMessageDialog(this,
                    "Usuário cadastrado com sucesso!");

            campoNome.setText("");
            campoUsuario.setText("");
            campoSenha.setText("");

        } else {
            JOptionPane.showMessageDialog(this,
                    "Não foi possível cadastrar. Verifique se o usuário já existe.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaCadastro tela = new TelaCadastro();
            tela.setVisible(true);
        });
    }
}
