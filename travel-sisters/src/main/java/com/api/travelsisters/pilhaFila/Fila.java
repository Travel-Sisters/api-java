package com.api.travelsisters.pilhaFila;

import com.api.travelsisters.csv.ListaObj;
import com.api.travelsisters.model.ViagemModel;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import java.time.chrono.ChronoLocalDate;
import java.util.List;

public class Fila {

    // Atributos
    private int tamanho;
    private ViagemModel[] fila;

    // Construtor

    public Fila(int capacidade) {
        this.tamanho = 0;
        this.fila = new ViagemModel[capacidade];
    }

    // Métodos

    /* Método isEmpty() - retorna true se a fila está vazia e false caso contrário */
    public boolean isEmpty() {
        if (tamanho == 0) {
            return true;
        }
        return false;

    }

    /* Método isFull() - retorna true se a fila está cheia e false caso contrário */
    public boolean isFull() {
        if (fila.length == tamanho) {
            return true;
        }
        return false;
    }

    /* Método insert - recebe um elemento e insere esse elemento na fila
                       no índice tamanho, e incrementa tamanho
                       Lançar IllegalStateException caso a fila esteja cheia
     */
    public void insert(ViagemModel item) {
        if (isFull()) {
            throw new IllegalStateException();
        } else {
            fila[tamanho] = item;
            tamanho++;
            System.out.println("Elemento adicionado");
        }

    }

    /* Método peek - retorna o primeiro elemento da fila, sem removê-lo */
    public ViagemModel peek() {
        if (isEmpty()) {
            return null;
        }
        return fila[0];

    }

    /* Método poll - remove e retorna o primeiro elemento da fila, se a fila não estiver
       vazia. Quando um elemento é removido, a fila "anda", e tamanho é decrementado
       Depois que a fila andar, "limpar" o ex-último elemento da fila, atribuindo null
     */
    public ViagemModel poll() {
        if (isEmpty()) {
            return null;
        }
        ViagemModel aux = fila[0];
        tamanho--;
        for (int i = 0; i < this.tamanho - 1; i++) {
            this.fila[i] = this.fila[i + 1];
        }
        return aux;
    }

    /* Método exibe() - exibe o conteúdo da fila */
    public void exibe() {
        for (int i = 0; i < tamanho; i++) {
            System.out.println(fila[i]);
        }
    }

    public void ordenar() {
        for (int i = 0; i < tamanho - 1; i++) {
            int indiceMenor = i;
            for (int j = i + 1; j < tamanho; j++) {
                int comparacaoData = fila[j].getData().compareTo(fila[indiceMenor].getData());
                if (comparacaoData < 0 || (comparacaoData == 0 && fila[j].getHorario()
                        .compareTo(fila[indiceMenor].getHorario()) < 0)) {
                    indiceMenor = j;
                }
            }

            // Troca os elementos
            ViagemModel temp = fila[indiceMenor];
            fila[indiceMenor] = fila[i];
            fila[i] = temp;
        }

        for (ViagemModel viagem : fila){
            if(viagem.getStatusViagem().equalsIgnoreCase("concluído")){
                poll();
            }
        }

       /* LocalDate dataAgora = LocalDate.now();
        LocalTime horaAgora = LocalTime.now();
        LocalTime horaFutura = horaAgora.plusHours(4);

        LocalDate dataDoElemento = fila[0].getData();
        int resultado = dataAgora.compareTo(dataDoElemento);
        if (resultado >= 0) {
            if (horaFutura.isAfter(fila[0].getHorario())) {
                System.out.println("true");
                return true;
            }
        }*/

        System.out.println("Fila ordenada por data e hora");
    }

    /* Usado nos testes  - complete para que fique certo */
    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public ViagemModel[] getFila() {
        return fila;
    }

    public void setFila(ViagemModel[] fila) {
        this.fila = fila;
    }

}
