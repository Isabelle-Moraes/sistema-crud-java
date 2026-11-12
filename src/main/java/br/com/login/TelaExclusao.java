
package br.com.login;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class TelaExclusao extends JFrame {

    private String usuarioLogado;

    public TelaExclusao(String usuario) {

        this.usuarioLogado = usuario;

        setTitle("Excluir Usuário");
        setSize(380, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(3, 1, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        painel.add(new JLabel(
                "Excluir conta de: " + usuarioLogado,
                SwingConstants.CENTER
        ));

        JButton botaoVoltar = new JButton("Voltar");
        JButton botaoExcluir = new JButton("Excluir usuário");

        JPanel painelBotoes = new JPanel(new FlowLayout());
        painelBotoes.add(botaoVoltar);
        painelBotoes.add(botaoExcluir);

        painel.add(painelBotoes);

        botaoVoltar.addActionListener(e -> {
            new TelaPrincipal(usuarioLogado).setVisible(true);
            dispose();
        });

        botaoExcluir.addActionListener(e -> excluir());

        add(painel);
    }

    private void excluir() {

        int confirmacao = JOptionPane.showConfirmDialog(
                this,
                "Tem certeza que deseja excluir sua conta?",
                "Confirmar exclusão",
                JOptionPane.YES_NO_OPTION
        );

        if (confirmacao != JOptionPane.YES_OPTION) {
            return;
        }

        try {

            Usuario pessoa = new Usuario("", usuarioLogado, "");

            boolean excluido = pessoa.excluiUsuario();

            if (excluido) {

                JOptionPane.showMessageDialog(
                        this,
                        "Usuário excluído com sucesso!"
                );

                new TelaLogin().setVisible(true);
                dispose();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Usuário não encontrado!"
                );
            }

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Erro no banco de dados: " + ex.getMessage()
            );
        }
    }
}
