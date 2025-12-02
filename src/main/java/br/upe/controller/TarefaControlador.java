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
        this.tarefaTableModel.fireTableDataChanged();
    }
    //Remover uma tarefa
    public void removerTarefa(Tarefa tarefa){
        this.tarefaTableModel.getTarefasAtivas().remove(tarefa);
        this.tarefaTableModel.fireTableDataChanged(); // Aqui mandamos um "Aviso" para a tabela dizendo : Refaça tudo. (Obs: Esse método vem direto da class AbstractTableModel)
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
