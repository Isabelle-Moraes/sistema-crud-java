
package br.com.login;

import javax.swing.*;
import java.awt.*;

public class TelaLogin extends JFrame {

    private JTextField campoUsuario;
    private JPasswordField campoSenha;

    public TelaLogin() {

        setTitle("Login do Sistema");
        setSize(350, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(3, 2, 10, 10));
        painel.setBorder(
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        );

        painel.add(new JLabel("Usuário:"));

        campoUsuario = new JTextField();
        painel.add(campoUsuario);

        painel.add(new JLabel("Senha:"));

        campoSenha = new JPasswordField();
        painel.add(campoSenha);

        JButton botaoEntrar = new JButton("Entrar");
        JButton botaoCadastrar = new JButton("Cadastrar");

        painel.add(botaoEntrar);
        painel.add(botaoCadastrar);

        botaoEntrar.addActionListener(e -> entrar());

        botaoCadastrar.addActionListener(e -> {
            new TelaCadastro().setVisible(true);
            dispose();
        });

        add(painel);
    }

    private void entrar() {

        String usuario = campoUsuario.getText().trim();
        String senha = new String(campoSenha.getPassword());

        if (usuario.isBlank() || senha.isBlank()) {
            JOptionPane.showMessageDialog(this,
                    "Preencha o usuário e a senha!");
            return;
        }

        Usuario pessoa = new Usuario("", usuario, senha);

        if (pessoa.validarLogin()) {

            JOptionPane.showMessageDialog(this,
                    "Login realizado com sucesso!");

            new TelaPrincipal(usuario).setVisible(true);
            dispose();

        } else {

            JOptionPane.showMessageDialog(this,
                    "Usuário ou senha incorretos!");
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            TelaLogin tela = new TelaLogin();
            tela.setVisible(true);
        });
    }
}
