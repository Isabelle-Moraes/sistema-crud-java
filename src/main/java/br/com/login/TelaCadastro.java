package br.com.login;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class TelaCadastro extends JFrame {

    private JTextField campoNome;
    private JTextField campoUsuario;
    private JPasswordField campoSenha;
    private JPasswordField campoConfirmar;

    public TelaCadastro() {

        setTitle("Cadastro de Usuário");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(5, 2, 10, 10));
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

        painel.add(new JLabel("Confirmar senha:"));
        campoConfirmar = new JPasswordField();
        painel.add(campoConfirmar);

        JButton botaoCadastrar = new JButton("Cadastrar");
        JButton botaoVoltar = new JButton("Voltar ao Login");

        painel.add(botaoVoltar);
        painel.add(botaoCadastrar);

        botaoCadastrar.addActionListener(e -> cadastrar());

        botaoVoltar.addActionListener(e -> {
            new TelaLogin().setVisible(true);
            dispose();
        });

        add(painel);
    }

    private void cadastrar() {

        String nome = campoNome.getText().trim();
        String usuario = campoUsuario.getText().trim();

        String senha = new String(campoSenha.getPassword());
        String confirmar = new String(campoConfirmar.getPassword());

        if (nome.isBlank() || usuario.isBlank()
                || senha.isBlank() || confirmar.isBlank()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Preencha todos os campos."
            );
            return;
        }

        if (!senha.equals(confirmar)) {

            JOptionPane.showMessageDialog(
                    this,
                    "As senhas não são iguais."
            );
            return;
        }

        try {

            Usuario novoUsuario = new Usuario(
                    nome,
                    usuario,
                    senha
            );

            boolean cadastrado = novoUsuario.cadastraUsuario();

            if (cadastrado) {

                JOptionPane.showMessageDialog(
                        this,
                        "Cadastro realizado com sucesso!"
                );

                new TelaLogin().setVisible(true);
                dispose();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Este usuário já está cadastrado."
                );
            }

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Erro no banco de dados: " + ex.getMessage()
            );
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            TelaCadastro tela = new TelaCadastro();
            tela.setVisible(true);
        });
    }
}
