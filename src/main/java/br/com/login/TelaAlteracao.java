
package br.com.login;

import javax.swing.*;
import java.awt.*;

public class TelaAlteracao extends JFrame {

    private JTextField campoUsuario;
    private JTextField campoNome;
    private JPasswordField campoSenha;

    private String usuarioLogado;

    public TelaAlteracao(String usuario) {

        this.usuarioLogado = usuario;

        setTitle("Alteração de Usuário");
        setSize(400, 320);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(5, 2, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        painel.add(new JLabel("Usuário:"));
        campoUsuario = new JTextField();
        painel.add(campoUsuario);

        painel.add(new JLabel("Novo nome:"));
        campoNome = new JTextField();
        painel.add(campoNome);

        painel.add(new JLabel("Nova senha:"));
        campoSenha = new JPasswordField();
        painel.add(campoSenha);

        JButton botaoVoltar = new JButton("Voltar");
        JButton botaoAlterar = new JButton("Salvar alterações");

        painel.add(botaoVoltar);
        painel.add(botaoAlterar);

        botaoAlterar.addActionListener(e -> alterar());

        botaoVoltar.addActionListener(e -> {
            new TelaPrincipal(usuarioLogado).setVisible(true);
            dispose();
        });

        add(painel);
    }

    private void alterar() {

        String usuario = campoUsuario.getText();
        String nome = campoNome.getText();
        String senha = new String(campoSenha.getPassword());

        if (usuario.isBlank() || nome.isBlank() || senha.isBlank()) {
            JOptionPane.showMessageDialog(this,
                    "Preencha todos os campos!");
            return;
        }

        Usuario pessoa = new Usuario(nome, usuario, senha);

        if (pessoa.alteraUsuario()) {
            JOptionPane.showMessageDialog(this,
                    "Dados alterados com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this,
                    "Não foi possível alterar os dados. Verifique o usuário.");
        }
    }
}
