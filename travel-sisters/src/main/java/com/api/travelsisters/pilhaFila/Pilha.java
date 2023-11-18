package com.api.travelsisters.pilhaFila;
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import com.api.travelsisters.model.ViagemModel;

import java.util.Arrays;

public class Pilha {
    private ViagemModel[] pilha;
    private int topo;

    public Pilha(int capacidade) {
        this.pilha = new ViagemModel[capacidade];
        this.topo = -1;
    }

    public Boolean isEmpty() {
        return this.topo == -1 ? true : false;
    }

    public Boolean isFull() {
        return this.topo == this.pilha.length - 1 ? true : false;
    }

    public void push(ViagemModel viagem) {
        if (this.isFull()) {
            throw new IllegalStateException("org.example.Pilha cheia!");
        } else {
            ++this.topo;
            this.pilha[this.topo] = viagem;
            //System.out.println("Elemento adicionado");
        }
    }

    //retorna o elemento do topo e deleta
    public String pop() {
        if (this.isEmpty()) {
            return "Lista Vazia!";
        } else {
            System.out.println(this.pilha[this.topo--]);
            return "Item desempilhado com sucesso";

        }
    }

    //retorna o elemento do topo
    public String peek() {
        if(isEmpty()){
            return  "Lista vazia!";
        }else {
            System.out.println( this.pilha[this.topo]);

        return "Último item";
        }
    }

    public void exibe() {
        for (int i = this.topo; i >= 0; --i) {
            System.out.println(this.pilha[i]);
        }

    }

    public int getTopo() {
        return this.topo;
    }

    @Override
    public String toString() {
        return "Pilha{" +
                "pilha=" + Arrays.toString(pilha) +
                ", topo=" + topo +
                '}';
    }
}

