package com.api.travelsisters.pilhaFila;

public class Fila {

    // Atributos
    private int tamanho;
    private String[] fila;

    // Construtor

    public Fila(int capacidade) {
        this.tamanho = 0;
        this.fila = new String[capacidade];
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
    public void insert(String item) {
        if (isFull()) {
            throw new IllegalStateException();
        } else {
            fila[tamanho] = item;
            tamanho++;
            System.out.println("Elemento adicionado");
        }

    }

    /* Método peek - retorna o primeiro elemento da fila, sem removê-lo */
    public String peek() {
        if (isEmpty()) {
            return null;
        }
        return fila[0];

    }

    /* Método poll - remove e retorna o primeiro elemento da fila, se a fila não estiver
       vazia. Quando um elemento é removido, a fila "anda", e tamanho é decrementado
       Depois que a fila andar, "limpar" o ex-último elemento da fila, atribuindo null
     */
    public String poll() {
        if (isEmpty()) {
            return null;
        }
        String aux = fila[0];
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

    /* Usado nos testes  - complete para que fique certo */
    public int getTamanho() {
        return tamanho;
    }
}
