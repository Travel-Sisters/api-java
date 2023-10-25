package com.api.travelsisters.csv;

import com.api.travelsisters.model.ViagemModel;

import java.util.Arrays;
import java.util.List;

public class ListaObj<T> {
    private T[] vetor;
    private int nroElem;

    public ListaObj(int tamanho) {
        vetor = (T[]) new Object[tamanho];
        nroElem = 0;
    }

    public void adiciona(T elemento) {
        if (nroElem >= vetor.length) {
            System.out.println("Lista estÃ¡ cheia");
        } else {
            vetor[nroElem++] = elemento;
        }
    }

    public void exibe() {
        if (nroElem == 0) {
            System.out.println("\nA lista estÃ¡ vazia.");
        } else {
            System.out.println("\nElementos da lista:");
            for (int i = 0; i < nroElem; i++) {
                System.out.println(vetor[i]);
            }
        }
    }

    public int busca(T elementoBuscado) {
        for (int i = 0; i < nroElem; i++) {
            if (vetor[i].equals(elementoBuscado)) {
                return i;
            }
        }
        return -1;
    }

    public boolean removePeloIndice(int indice) {
        if (indice < 0 || indice >= nroElem) {
            System.out.println("\nIndice invalido!");
            return false;
        }

        for (int i = indice; i < nroElem - 1; i++) {
            vetor[i] = vetor[i + 1];
        }

        nroElem--;
        return true;
    }

    public boolean removeElemento(T elementoARemover) {
        return removePeloIndice(busca(elementoARemover));
    }

    public int getTamanho() {
        return nroElem;
    }

    public T getElemento(int indice) {
        if (indice < 0 || indice >= nroElem) {
            return null;
        } else {
            return vetor[indice];
        }
    }

    public void setElemento(int ind, T elemento) {
        vetor[ind] = elemento;
    }

    public void limpa() {
        nroElem = 0;
    }

    public int buscarPorAno(int ano){
        int inf, mid, sup;
        inf = 0;
        sup = vetor.length - 1;

        while(inf <= sup){
            mid = (inf + sup) / 2;
            ViagemModel current = (ViagemModel) vetor[mid];
            if(current.getData().getYear() == ano){
                return mid;
            } else if(ano > current.getData().getYear()){
                sup = mid - 1;
            } else {
                inf = mid + 1;
            }
        }

        return -1;
    }

    @Override
    public String toString() {
        return "ListaObj{" +
                "vetor=" + Arrays.toString(vetor) +
                ", nroElem=" + nroElem +
                '}';
    }
}


