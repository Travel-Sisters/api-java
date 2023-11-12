/*package com.api.travelsisters.txt;

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
        String header = "00NOTA20232"; //Verificar documento de layout
        header += LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        header += "01";

        // Grava o registro de header
        gravaRegistro(header, nomeArq);

        // Grava os registros de dados (ou registros de corpo)
        for (ViagemModel v : lista) {
            String corpo = "02";
            corpo += String.format("%4d", v.getId());
            corpo += String.format("%-10s", v.getData());
            corpo += String.format("%-170s", v.getPontoEmbarque());
            corpo += String.format("%-170s", v.getPontoDesembarque());
            corpo += String.format("%-50s", v.getDescricao());
            corpo += String.format("%10s", v.getHorario());
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
                    System.out.println("Tipo do Arquivo: " + registro.substring(2,6));
                    System.out.println("Ano/Semestre:" + registro.substring(6,11));
                    System.out.println("Data e Hora: " + registro.substring(11,30));
                    System.out.println("Versão do layout: " + registro.substring(30,32));

                } else if (tipoRegistro.equals("01")) {

                    System.out.println("É um registro de trailer");

                    //Exibir quantidade de registros

                } else if (tipoRegistro.equals("02")) {
                    System.out.println("É um registro de corpo");

                    String curso = registro.substring(2,7).trim();
                    String ra = registro.substring(7,15).trim();
                    String nome = registro.substring(15,65).trim();
                    String disciplina = registro.substring(65, 105).trim();
                    Double media = Double.valueOf(registro.substring(105,110).trim().replace(",","."));
                    int qtdFaltas = Integer.valueOf(registro.substring(110,113).trim());


                    //Depois de guarda em variável, exiba:
                    System.out.println("Curso: " + curso);
                    System.out.println("RA"+ ra);
                    System.out.println("Nome: " + nome);
                    System.out.println("Disciplina: " + disciplina);
                    System.out.println("Média: " + media);
                    System.out.println("Faltas: "+ qtdFaltas);


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
        for (Aluno a : listaLida) {
            System.out.println(a);
        }

        // Aqui tb seria possível salvar a lista no BD
        //repository.saveAll(listaLida);

    }
}*/
