package com.api.travelsisters.csv;

import com.api.travelsisters.model.EnderecosModel;
import com.api.travelsisters.model.MotoristaModel;
import com.api.travelsisters.model.UsuarioModel;
import com.api.travelsisters.model.ViagemModel;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class Teste {
    public static void main(String[] args) {
        ListaObj<ViagemModel> viagens = new ListaObj<>(5);

        viagens.adiciona(new ViagemModel(1, LocalDate.of(2023, 1, 12),
                new EnderecosModel(), new EnderecosModel(), "a para b",
                LocalTime.parse("14:00:00"), 200.00,
                new UsuarioModel(), new MotoristaModel()));

        viagens.adiciona(new ViagemModel(1, LocalDate.of(2024, 8, 12),
                new EnderecosModel(), new EnderecosModel(), "a para b",
                LocalTime.parse("14:00:00"), 300.00,
                new UsuarioModel(), new MotoristaModel()));


        int opcaoEscolhida;

        do {
            Scanner leitor = new Scanner(System.in);
            Scanner leitorPesqBinario = new Scanner(System.in);

            System.out.println("""
                    Escolha uma opção:
                    1) Gravar Arquivo
                    2) Ler Arquivo
                    3) Ordenar por mês
                    4) Pesquisa Binária
                    0) Sair
                    """);
            opcaoEscolhida = leitor.nextInt();

            switch (opcaoEscolhida) {

                case 1 -> {
                    GerenciadorDeArquivo.gravaArquivoCsv(viagens, "viagens");
                    System.out.println("Arquivo guardado!\n");
                }
                case 2 -> GerenciadorDeArquivo.leArquivoCsv("viagens");
                case 3 -> {
                    System.out.println("Ordenando arquivo!\n");
                    for (int i = 0; i < viagens.getTamanho() - 1; i++) {
                        for (int j = 0; j < viagens.getTamanho() - i - 1; j++) {

                            if (viagens.getElemento(j).getData().getYear() >
                                    viagens.getElemento(j + 1).getData().getYear()) {

                                ViagemModel x = viagens.getElemento(j);
                                viagens.setElemento(j, viagens.getElemento(j + 1));
                                viagens.setElemento(j + 1, x);
                            }
                        }

                    }
                    for (int x = 0; x < viagens.getTamanho(); x++) {
                        System.out.println(viagens.getElemento(x));
                    }
                }
                case 4 -> {
                    System.out.println("Digite o número que deseja pesquisar:");
                    int x = leitorPesqBinario.nextInt();
                    
                    System.out.println( viagens.buscarPorAno(x));
                }
            }
        }
        while (opcaoEscolhida != 0);
    }
}
