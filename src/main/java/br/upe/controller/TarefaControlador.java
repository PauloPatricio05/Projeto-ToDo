package br.upe.controller;

import br.upe.model.Tarefa;
import br.upe.model.TarefaTableModel;

import javax.swing.event.TableModelListener;
import java.util.ArrayList;
import java.util.List;

public class TarefaControlador {

    // Atributos
    private TarefaTableModel tarefaTableModel;

    //Construtor
    public TarefaControlador() {
        tarefaTableModel = new TarefaTableModel();
    }

    //Metodos de negocio
    public void adicionarTarefaAtiva(Tarefa tarefa) {
        this.tarefaTableModel.getTarefasAtivas().add(tarefa);
    }
    //Remover uma tarefa
    public void removerTarefa(Tarefa tarefa){
        System.out.println("3. Controlador recebeu o pedido para remover.");
        boolean removeu = this.tarefaTableModel.getTarefasAtivas().remove(tarefa);
        System.out.println("4. Conseguiu remover da lista? " + removeu);

        this.tarefaTableModel.fireTableDataChanged();
        System.out.println("5. Tabela avisada."); // Aqui mandamos um "Aviso" para a tabela dizendo : Refaça tudo. (Obs: Esse método vem direto da class AbstractTableModel)
    }

    public void exibirFinalizadas(boolean exibir) {
        tarefaTableModel.setExibirFinalizadas(exibir);
    }


    //Getter e Setters

    public TarefaTableModel getTarefaTableModel() {
        return tarefaTableModel;
    }

    public void setTarefaTableModel(TarefaTableModel tarefaTableModel) {
        this.tarefaTableModel = tarefaTableModel;
    }

}
