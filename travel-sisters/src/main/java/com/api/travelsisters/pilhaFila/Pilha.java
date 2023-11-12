package com.api.travelsisters.pilhaFila;
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

public class Pilha {
    private int[] pilha;
    private int topo;

    public Pilha(int capacidade) {
        this.pilha = new int[capacidade];
        this.topo = -1;
    }

    public Boolean isEmpty() {
        return this.topo == -1 ? true : false;
    }

    public Boolean isFull() {
        return this.topo == this.pilha.length - 1 ? true : false;
    }

    public void push(int info) {
        if (this.isFull()) {
            throw new IllegalStateException("org.example.Pilha cheia!");
        } else {
            ++this.topo;
            this.pilha[this.topo] = info;
            //System.out.println("Elemento adicionado");
        }
    }

    public int pop() {
        if (this.isEmpty()) {
            System.out.println("Lista Vazia!");
            return -1;
        } else {
            return this.pilha[this.topo--];
        }
    }

    public int peek() {
        return this.isEmpty() ? -1 : this.pilha[this.topo];
    }

    public void exibe() {
        for(int i = this.topo; i < 0; --i) {
            System.out.println(i + this.pilha[i]);
        }

    }

    public int getTopo() {
        return this.topo;
    }
}

