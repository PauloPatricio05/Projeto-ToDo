package br.upe;

import br.upe.ui.TelaPrincipal;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
    public static void main(String[] args) {
        TelaPrincipal pnlMain = new TelaPrincipal();

        JFrame frame = new JFrame("ToDo");
        frame.setContentPane(pnlMain.getPnlMain());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Dizemos à janela: "Quando clicarem no X, NÃO feche o programa imediatamente."
        // Fazemos isso para ter tempo de salvar os dados antes do processo morrer.
        //O JFrame.DO_NOTHING_ON_CLOSE tem a função de não deixar o programa fechar ao clicar no (X).
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        // Esse método roda automaticamente quando alguém clica no "X"
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //Salva as tarefas no arquivo JSON antes de sair
                pnlMain.salvarAoFechar();

                //Encerra o programa de vez (o zero significa "sem erros")
                System.exit(0);
            }
        });
    }
}