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

        // --- MUDANÇA AQUI ---

        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Antes de morrer, o programa grita: "SALVA TUDO!"
                pnlMain.salvarAoFechar();

                // Agora sim, pode fechar com segurança
                System.exit(0);
            }
        });
    }
}