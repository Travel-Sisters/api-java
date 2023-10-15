package com.api.travelsisters.csv;

import com.api.travelsisters.model.ViagemModel;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class GerenciadorDeArquivo {
    public static void gravaArquivoCsv(ListaObj<ViagemModel> lista, String nomeArq) {
        FileWriter arq = null;
        Formatter saida = null;
        Boolean deuRuim = false;

        nomeArq += ".csv";

        // Bloco try-catch para abrir o arquivo
        try {
            arq = new FileWriter(nomeArq);
            saida = new Formatter(arq);
        } catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo");
            System.exit(1);
        }

        // Bloco try-catch para gravar o arquivo
        try {
            for (int i = 0; i < lista.getTamanho(); i++) {
                ViagemModel viagemModel = lista.getElemento(i);
                saida.format("%d;%s;%s;%s;%s;%s;%.2f\n",
                        viagemModel.getId(), viagemModel.getData(),
                        viagemModel.getPontoEmbarque(), viagemModel.getPontoDesembarque(),
                        viagemModel.getDescricao(), viagemModel.getHorario(), viagemModel.getValor());
            }
        } catch (FormatterClosedException erro) {
            System.out.println("Erro ao gravar o arquivo");
            deuRuim = true;
        } finally {
            saida.close();
            try {
                arq.close();
            } catch (IOException erro) {
                System.out.println("Erro ao fechar o arquivo");
                deuRuim = true;
            }
            if (deuRuim) {
                System.exit(1);
            }
        }
    }

    public static void leArquivoCsv(String nomeArq) {
        FileReader arq = null;
        Scanner entrada = null;
        Boolean deuRuim = false;

        nomeArq += ".csv";

        // Bloco try-catch para abrir o arquivo
        try {
            arq = new FileReader(nomeArq);
            entrada = new Scanner(arq).useDelimiter(";|\\n");
        } catch (FileNotFoundException erro) {
            System.out.println("Arquivo nao encontrado");
            System.exit(1);
        }

        // Bloco try-catch para ler o arquivo
        try {
            // Print Cabeçalho:
            System.out.printf("%-4s %10s %30s %30s %50s %15s %-8s\n",
                    "id", "data", "pontoEmb", "pontoDes", "descricao", "horario", "valor");
            while (entrada.hasNext()) {
                //Print Corpo:
                int id = entrada.nextInt();
                String data = entrada.next();
                String pontoEmb = entrada.next();
                String pontoDes = entrada.next();
                String descricao = entrada.next();
                String horario = entrada.next();
                double valor = entrada.nextDouble();
                System.out.printf("%-4d %10s %30s %30s %50s %15s %-8.2f\n",
                        id, data, pontoEmb, pontoDes, descricao, horario, valor);
            }
        } catch (NoSuchElementException erro) {
            System.out.println("Arquivo com problemas");
            deuRuim = true;
        } catch (IllegalStateException erro) {
            System.out.println("Erro na leitura do arquivo");
            deuRuim = true;
        } finally {
            entrada.close();
            try {
                arq.close();
            } catch (IOException erro) {
                System.out.println("Erro ao fechar o arquivo");
                deuRuim = true;
            }
            if (deuRuim) {
                System.exit(1);
            }
        }
    }
}

