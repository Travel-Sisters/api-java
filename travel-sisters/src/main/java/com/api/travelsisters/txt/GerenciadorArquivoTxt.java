package com.api.travelsisters.txt;

import com.api.travelsisters.model.ViagemModel;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorArquivoTxt {
    public static void gravaRegistro(String registro, String nomeArq) {
        BufferedWriter saida = null;

        // Abre o arquivo
        try {
            saida = new BufferedWriter(new FileWriter(nomeArq, true));
        } catch (IOException erro) {
            System.out.println("Erro na abertura do arquivo");
        }

        // Grava o registro e fecha o arquivo
        try {
            saida.append(registro + "\n");
            saida.close();
        } catch (IOException erro) {
            System.out.println("Erro ao gravar o arquivo");
            erro.printStackTrace();
        }
    }

    public static void gravaArquivoTxt(List<ViagemModel> lista, String nomeArq) {
        int contaRegDados = 0;

        // Monta o registro de header
        String header = "00VIAGEM"; //Verificar documento de layout
        header += LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        header += "01";

        // Grava o registro de header
        gravaRegistro(header, nomeArq);

        // Grava os registros de dados (ou registros de corpo)
        for (ViagemModel v : lista) {
            String corpo = "02";
            corpo += String.format("%04d", v.getId());
            corpo += String.format("%-10s", v.getData());
            corpo += String.format("%-50s", v.getPontoEmbarque().getRua());
            corpo += String.format("%-50s", v.getPontoDesembarque().getRua());
            corpo += String.format("%-50s", v.getDescricao());
            corpo += String.format("%-5s", v.getHorario());
            corpo += String.format("%-6.2f", v.getValor());

            //Gravando corpo no arquivo:
            gravaRegistro(corpo, nomeArq);
            // Incrementa o contador de registros de dados gravados
            contaRegDados++;
        }

        // Monta e grava o registro de trailer
        String trailer = "01";
        trailer += String.format("%010d", contaRegDados);

        gravaRegistro(trailer, nomeArq);
    }

    public static void leArquivoTxt(String nomeArq) {
        BufferedReader entrada = null;
        String registro, tipoRegistro;
        int contaRegDadosLidos = 0;
        int qtdRegDadosGravados;


        // Cria uma lista para armazenar os objetos criados com
        // os dados lidos do arquivo txt
        List<ViagemModel> listaLida = new ArrayList<>();

        // Abre o arquivo

        try {
            entrada = new BufferedReader(new FileReader(nomeArq));
        } catch (IOException erro) {
            System.out.println("Erro na abertura do arquivo");
        }


        // Leitura do arquivo
        try {
            registro = entrada.readLine();

            while (registro != null) {
                // obtem os 2 primeiros caracteres do registro lido
                // 1o argumento do substring é o indice do que se quer obter, iniciando de zero
                // 2o argumento do substring é o indice final do que se deseja, MAIS UM

                // 012345
                // 00NOTA
                tipoRegistro = registro.substring(0, 2);

                if (tipoRegistro.equals("00")) {
                    System.out.println("É um registro de header");
                    //Exibir informações do header
                    System.out.println("Tipo do Arquivo: " + registro.substring(3,6));
                    System.out.println("Data e Hora: " + registro.substring(7,25));
                    System.out.println("Versão do layout: " + registro.substring(26,28));

                } else if (tipoRegistro.equals("01")) {

                    System.out.println("É um registro de trailer");

                    //Exibir quantidade de registros

                } else if (tipoRegistro.equals("02")) {
                    System.out.println("É um registro de corpo");

                    int id = Integer.valueOf(registro.substring(2,6).trim());
                    String data = registro.substring(6,16).trim();
                    String pontoEmb = registro.substring(16,66).trim();
                    String pontoDes = registro.substring(66, 106).trim();
                    String descricao = registro.substring(106, 156).trim();
                    String horario = registro.substring(156,161).trim();
                    double valor = Double.parseDouble(registro.substring(161,167).trim().replace(",", "."));

                    //Depois de guarda em variável, exiba:
                    System.out.println("id: " + id);
                    System.out.println("data"+ data);
                    System.out.println("pontoEmb: " + pontoEmb);
                    System.out.println("pontoDes: " + pontoDes);
                    System.out.println("descricao: " + descricao);
                    System.out.println("horario: "+ horario);
                    System.out.println("valor: "+ valor);


                    // Incrementa o contador de reg de dados lidos
                    contaRegDadosLidos++;

                    // Cria um objeto com os dados lidos do registro
                    ViagemModel a = new ViagemModel();
                    listaLida.add(a);

                    // Se estivesse conectado a um banco de dados,
                    // alunoRepository.save(a);

                } else {
                    System.out.println("Registro inválido");
                }
                registro = entrada.readLine();
            }  // fim do while

            // Fecha o arquivo
            entrada.close();
        } // fim do try

        catch (IOException erro) {
            System.out.println("Erro ao ler o arquivo");
            erro.printStackTrace();
        }
        // Exibe a lista lida
        System.out.println("\nLista lida do arquivo:");
        for (ViagemModel v : listaLida) {
            System.out.println(v);
        }

        // Aqui tb seria possível salvar a lista no BD
        //repository.saveAll(listaLida);

    }
}
