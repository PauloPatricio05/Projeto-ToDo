package br.upe.controller;

import br.upe.model.Tarefa;
import br.upe.model.TarefaTableModel;
import com.google.gson.Gson; //Ferramenta do google
import javax.swing.event.TableModelListener;
import java.util.ArrayList;
import java.util.List;
import java.io.*; //Ferramenta do arquivo, input/output

public class TarefaControlador {

    // Atributos
    private TarefaTableModel tarefaTableModel;

    //Construtor
    public TarefaControlador() {
        tarefaTableModel = new TarefaTableModel();
    }


    //Criando uma classe para auxiliar o arquivo Json, jutandos as duas listas antes de salvar
    //Isso vai fazer com que no arquivo json, seja criada uma estrutura organizada com duas chaves.
    private class BackupDados{
        //Crio os atributos que serão basicamente o titulo chave de cada compartimento do arquivo.
        List<Tarefa> ativas;
        List<Tarefa> finalizadas;

        //Construtor, onde eu irei receber as tarefas ativas, e as finalizadas.
        public BackupDados(List<Tarefa> ativas, List<Tarefa> finalizadas){
            this.ativas = ativas;
            this.finalizadas = finalizadas;
        }
    }

    /*
      Método responsável por salvar o estado atual das tarefas no disco.
      Deve ser chamado sempre que houver uma modificação, seja ela (Adicionar/Remover).
     */
    public void salvarDados(){

        // 1. Verificamos se as listas estão misturadas na tela
        boolean estaMisturado = tarefaTableModel.isExibirFinalizadas();

        // 2. Se estiverem misturadas, nós separamos elas forçadamente agora
        if (estaMisturado) {
            tarefaTableModel.setExibirFinalizadas(false);
        }

        // 3. Agora que garantimos que 'ativas' só tem ativas, criamos o backup
        BackupDados dados = new BackupDados(
                tarefaTableModel.getTarefasAtivas(),
                tarefaTableModel.getTarefasFinalizadas()
        );

        //Dando play no Gson
        Gson gson = new Gson();

        /*new FileWriter("dados_tarefas.json")
         Basicamente o FileWriter é uma classe no java que tem a função
         de escrever texto em arquivos.
         Se o arquivo não existir: O Windows cria um arquivo novo em branco e salva tudo que você
         escreveu.
         Se o arquivo JÁ existir: O Windows apaga tudo o que tinha nele e abre em branco e (sobrescreve).
         É assim que salva as atualizações: destruindo a versão velha e escrevendo a nova. */

        try (FileWriter writer = new FileWriter("dados_tarefas.json")){
            //O (.toJson) passa a ordem da ação, por exemplo: "Converta para o formato Json".
            //Primeiro parametro (dados): O gson vai pegar tudo que tem la e "empacotar"
            //Segundo parametro (writer): é basicamente o destino, conecta o trabalho que o gson
            //fez com o dados_tarefas.json, vai escrever tudo la
            gson.toJson(dados, writer); // Escreve as coisas que precisam ser escritas no disco
            System.out.println("Dados salvos com sucesso!");
        } catch (IOException e) { // Caso de erro
            System.out.println("Erro ao salvar: " + e.getMessage());
        }
    }

    public void carregarDados() {
        //Verifica se o arquivo existe. Se for a primeira vez, ele não existe, então paramos.
        //O File basicamente é uma classe que aborda o endereço do arquivo, podemos usar ela
        //para chegar no local do arquivo e verificar algo!
        File arquivo = new File("dados_tarefas.json");
        //Se o arquivo não existir, não faça nada...
        if (!arquivo.exists()) {
            return;
        }

        //Dando play no Gson
        Gson gson = new Gson();

        // Abre o arquivo para leitura (Reader)
        try (FileReader leitura = new FileReader(arquivo)) {

            // Traduz do json (texto) de volta para o Objeto Java (BackupDados)
            BackupDados dados = gson.fromJson(leitura, BackupDados.class);

            // Se a leitura funcionou, atualiza as listas do TableModel
            if (dados.ativas != null) {
                tarefaTableModel.setTarefasAtivas(dados.ativas);
            }
            if (dados.finalizadas != null) {
                tarefaTableModel.setTarefasFinalizadas(dados.finalizadas);
            }

            //Avisa a tela (JTable) que os dados mudaram!
            tarefaTableModel.fireTableDataChanged();
            System.out.println("Dados carregados com sucesso!");

        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }

    }



    //Metodos de negocio
    public void adicionarTarefaAtiva(Tarefa tarefa) {
        this.tarefaTableModel.getTarefasAtivas().add(tarefa);
        this.tarefaTableModel.fireTableDataChanged();
    }
    //Remover uma tarefa
    public void removerTarefa(Tarefa tarefa){
        this.tarefaTableModel.getTarefasAtivas().remove(tarefa);
        this.tarefaTableModel.getTarefasFinalizadas().remove(tarefa);
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
