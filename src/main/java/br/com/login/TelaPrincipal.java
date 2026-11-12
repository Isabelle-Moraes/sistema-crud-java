
package br.com.login;

import javax.swing.*;
import java.awt.*;

public class TelaPrincipal extends JFrame {

    private String usuario;

    public TelaPrincipal(String usuario) {

        this.usuario = usuario;

        setTitle("Sistema - Tela Principal");
        setSize(400, 330);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridLayout(5, 1, 10, 10));
        painel.setBorder(
            BorderFactory.createEmptyBorder(30, 30, 30, 30)
        );

        JLabel mensagem = new JLabel(
            "Bem-vindo ao sistema, " + usuario + "!",
            SwingConstants.CENTER
        );

        mensagem.setFont(new Font("Arial", Font.BOLD, 18));

        JButton botaoAlterar = new JButton("Alterar meus dados");
        JButton botaoExcluir = new JButton("Excluir usuário");
        JButton botaoSair = new JButton("Sair");

        // Abrir tela de alteração
        botaoAlterar.addActionListener(e -> {
            new TelaAlteracao(usuario).setVisible(true);
            dispose();
        });

        // Abrir tela de exclusão
        botaoExcluir.addActionListener(e -> {
            new TelaExclusao(usuario).setVisible(true);
            dispose();
        });

        // Voltar ao login
        botaoSair.addActionListener(e -> {
            new TelaLogin().setVisible(true);
            dispose();
        });

        painel.add(mensagem);

        painel.add(new JLabel(
            "Acesso realizado com sucesso!",
            SwingConstants.CENTER
        ));

        painel.add(botaoAlterar);
        painel.add(botaoExcluir);
        painel.add(botaoSair);

        add(painel);
    }
}
