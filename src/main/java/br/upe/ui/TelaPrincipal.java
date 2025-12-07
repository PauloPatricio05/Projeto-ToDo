package br.upe.ui;

import br.upe.controller.TarefaControlador;
import br.upe.model.Tarefa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TelaPrincipal {
    private JPanel pnlMain;
    private JTextField txtDescricaoTarefa;
    private JButton btnAdicionarTarefa;
    private JPanel pnlAdicionar;
    private JTable tblTarefas;
    private JCheckBox chkExibirFinalizadas;
    private JButton remove;

    private List<Tarefa> tarefas;

    private TarefaControlador controlador;

    public TelaPrincipal() {
        super();
        tarefas = new ArrayList<>();
        btnAdicionarTarefa.addActionListener(e -> {
            adicionarTarefa(txtDescricaoTarefa.getText());
            txtDescricaoTarefa.setText("");
        });
        txtDescricaoTarefa.addActionListener(e1 -> {
            adicionarTarefa(txtDescricaoTarefa.getText());
            txtDescricaoTarefa.setText("");
        });
        chkExibirFinalizadas.addActionListener(e -> {
            boolean selecionado = ((JCheckBox) e.getSource()).isSelected();
            controlador.exibirFinalizadas(selecionado);
        });

        remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int linhaSelecionada = tblTarefas.getSelectedRow();
                System.out.println("Botão selecionado:" + linhaSelecionada);

                //Convenção do java Swing utilizar o -1.
                if (linhaSelecionada != -1) {
                    Tarefa tarefa = controlador.getTarefaTableModel().getTarefasAtivas().get(linhaSelecionada);
                    controlador.removerTarefa(tarefa);
                }
                else{
                    JOptionPane.showMessageDialog(null,"Não há tarefa selecionada para ser removida!","ERROR!",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    private void adicionarTarefa(String texto) {
        String novoTexto = texto.trim();
        if (novoTexto.equals("")){
            JOptionPane.showMessageDialog(null,"Nenhuma tarefa atribuída!","ERROR!",JOptionPane.ERROR_MESSAGE);
        } else {
        Tarefa tarefa = new Tarefa(texto, tarefas.size());
        controlador.adicionarTarefaAtiva(tarefa);
        tblTarefas.revalidate();
        tblTarefas.repaint();
        //Instancia para salvar no Json todas as tarefas adicionadas
        controlador.salvarDados();

        }
    }
    public JPanel getPnlMain() {
        return this.pnlMain;
    }
    private void createUIComponents() {
        controlador = new TarefaControlador();
        // --- LINHA NOVA: Carrega os dados assim que a tela é criada ---
        controlador.carregarDados();
        // --------------------------------------------------------------
        tblTarefas = new JTable(controlador.getTarefaTableModel());
        tblTarefas.getColumnModel().getColumn(0).setMaxWidth(20);
    }

    public void salvarAoFechar() {
        controlador.salvarDados();
    }
}
