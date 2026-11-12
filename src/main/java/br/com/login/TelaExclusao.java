
package br.com.login;

import javax.swing.*;
import java.awt.*;

public class TelaExclusao extends JFrame {

    private JTextField campoUsuario;
    private String usuarioLogado;

    public TelaExclusao(String usuario) {

        this.usuarioLogado = usuario;

        setTitle("Excluir Usuário");
        setSize(380, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(3, 2, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        painel.add(new JLabel("Usuário:"));

        campoUsuario = new JTextField(usuarioLogado);
        painel.add(campoUsuario);

        JButton botaoVoltar = new JButton("Voltar");
        JButton botaoExcluir = new JButton("Excluir usuário");

        painel.add(botaoVoltar);
        painel.add(botaoExcluir);

        botaoVoltar.addActionListener(e -> {
            new TelaPrincipal(usuarioLogado).setVisible(true);
            dispose();
        });

        botaoExcluir.addActionListener(e -> excluir());

        add(painel);
    }

    private void excluir() {

        String usuario = campoUsuario.getText();

        if (usuario.isBlank()) {
            JOptionPane.showMessageDialog(this,
                    "Informe o usuário que deseja excluir!");
            return;
        }

        int confirmacao = JOptionPane.showConfirmDialog(
                this,
                "Tem certeza que deseja excluir este usuário?",
                "Confirmar exclusão",
                JOptionPane.YES_NO_OPTION
        );

        if (confirmacao == JOptionPane.YES_OPTION) {

            Usuario pessoa = new Usuario("", usuario, "");

            if (pessoa.excluiUsuario()) {
                JOptionPane.showMessageDialog(this,
                        "Usuário excluído com sucesso!");

                new TelaLogin().setVisible(true);
                dispose();

            } else {
                JOptionPane.showMessageDialog(this,
                        "Usuário não encontrado!");
            }
        }
    }
}
