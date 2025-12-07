package br.upe.model;

import java.time.LocalDate;

public class Tarefa {

    private String descricao;
    private Boolean finalizada;
    private int ordem;

    public Tarefa(String texto, int ordem) {
        this.descricao = texto;
        this.ordem = ordem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean isFinalizada() {
        return finalizada;
    }

    public void setFinalizada(Boolean finalizada) {
        this.finalizada = finalizada;
    }

    public int getOrdem() {
        return ordem;
    }

    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }
}
