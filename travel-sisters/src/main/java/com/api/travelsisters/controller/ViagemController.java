package com.api.travelsisters.controller;

import com.api.travelsisters.csv.GerenciadorDeArquivo;
import com.api.travelsisters.csv.ListaObj;
import com.api.travelsisters.model.*;
import com.api.travelsisters.pilhaFila.Fila;
import com.api.travelsisters.pilhaFila.Pilha;
import com.api.travelsisters.repository.MotoristaRepository;
import com.api.travelsisters.repository.UsuarioRepository;
import com.api.travelsisters.repository.UsuarioViagemRepository;
import com.api.travelsisters.repository.ViagemRepository;
import com.api.travelsisters.txt.GerenciadorArquivoTxt;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/viagens")
@CrossOrigin("*")
public class ViagemController {
    @Autowired
    private ViagemRepository repository;

    @Autowired
    private UsuarioViagemRepository repositoryUsuarioViagem;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private MotoristaRepository motoristaRepository;

    @GetMapping("/listar")
    public ResponseEntity<List<ViagemModel>> listar() {
        if (repository.findAll().isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(repository.findAll());
    }

    @GetMapping("buscarPorId/{id}")
    public ResponseEntity<ViagemModel> findByID(@Valid @PathVariable int id) {
        return ResponseEntity.of(repository.findById(id));
    }

    @CrossOrigin
    @PostMapping("/cadastrarUsuarioViagem/{idViagem}/{idUsuario}")
    public ResponseEntity<UsuarioViagemModel> cadastrarNaTabelaAssociativa (@PathVariable Integer idUsuario,
                                                        @PathVariable Integer idViagem) {
        UsuarioModel usuario = usuarioRepository.encontrarPorId(idUsuario);
        ViagemModel viagem = repository.encontrarPorId(idViagem);
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        if (viagem == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        UsuarioViagemModel associativa = new UsuarioViagemModel(usuario,viagem);
        repositoryUsuarioViagem.save(associativa);
        System.out.println(associativa);
        return ResponseEntity.status(204).body(associativa);
    }

    @CrossOrigin
    @PostMapping("/cadastrar/{idMotorista}")
    public ResponseEntity<ViagemModel> cadastrar
            (@Valid @RequestBody ViagemModel cadastro,
             @PathVariable Integer idMotorista) {
        MotoristaModel motorista = motoristaRepository.encontrarPorId(idMotorista);

        if (motorista == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        cadastro.setMotorista(motorista);
        cadastro.setStatusViagem("em progresso");
        repository.save(cadastro);
        return ResponseEntity.status(201).body(cadastro);
    }

    @PutMapping("/alterar")
    public ResponseEntity<ViagemModel> alterar
            (@Valid @RequestBody ViagemModel cadastro) {
        return ResponseEntity.status(200).body(repository.save(cadastro));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletar(@Valid @PathVariable int id) {
        try {
            ViagemModel cadastro = findByID(id).getBody();
            assert cadastro != null;
            this.repository.delete(cadastro);
            return ResponseEntity.status(200).build();
        } catch (Exception error) {
            return ResponseEntity.status(404).build();
        }
    }

    @GetMapping("/csv/{idMotorista}")
    public ResponseEntity<String> csv(@PathVariable Integer idMotorista) {
        List<ViagemModel> lista = repository.findByMotoristaId(idMotorista);
        ListaObj<ViagemModel> listaViagem = new ListaObj<>(lista.size());

        for (ViagemModel viagem : lista) {
            listaViagem.adiciona(viagem);
        }

        listaViagem.ordenar(listaViagem);
        listaViagem.buscarPorAno(2023);
        GerenciadorDeArquivo.gravaArquivoCsv(listaViagem, "viagens");
        GerenciadorDeArquivo.leArquivoCsv("viagens");

        return ResponseEntity.status(201).body("Arquivo csv gerado com sucesso");
    }

    @GetMapping("/listarPorId/{idMotorista}")
    public ResponseEntity<List<ViagemModel>> listarPorId(@PathVariable Integer idMotorista) {
        List<ViagemModel> listaViagem = repository.findByMotoristaId(idMotorista);
        System.out.println(listaViagem);
        if(listaViagem.isEmpty())
            return ResponseEntity.status(404).build();

        return ResponseEntity.status(201).body(listaViagem);
    }

    @GetMapping("/listarPorIdUsuario/{idUsuario}")
    public ResponseEntity<List<ViagemModel>> listarPorIdUsuario(@PathVariable Integer idUsuario) {
        List<ViagemModel> listaViagem = repositoryUsuarioViagem.findByUsuarioViagemId(idUsuario);
        System.out.println(listaViagem);
        if(listaViagem.isEmpty())
            return ResponseEntity.status(404).build();

        return ResponseEntity.status(201).body(listaViagem);
    }

    @GetMapping("/txt/{idMotorista}")
    public ResponseEntity<String> txt(@PathVariable Integer idMotorista) {
        List<ViagemModel> listaViagem = repository.findByMotoristaId(idMotorista);

        GerenciadorArquivoTxt.gravaArquivoTxt(listaViagem, "viagensTxt");
        GerenciadorArquivoTxt.leArquivoTxt("viagensTxt");

        return ResponseEntity.status(201).body("Arquivo txt gerado com sucesso");

    }

    @CrossOrigin
    @GetMapping("/pilha/{idMotorista}")
    public ResponseEntity<String> desfazerViagem(@PathVariable Integer idMotorista) {
        List<ViagemModel> listaViagem = repository.findByMotoristaId(idMotorista);
        Pilha pilha = new Pilha(listaViagem.size());
        for (ViagemModel viagem : listaViagem) {
            pilha.push(viagem);
        }
        deletar(listaViagem.get(pilha.getTopo()).getId());
        pilha.pop();
        pilha.exibe();
        return ResponseEntity.status(201).body("Viagem removida da pilha com sucesso");
    }

    @GetMapping("/fila/{idMotorista}")
    public ResponseEntity<List<ViagemModel>> terminarViagem(@PathVariable Integer idMotorista) {
        List<ViagemModel> listaViagem = listar().getBody();
        if (listaViagem != null && !listaViagem.isEmpty()) {
            Fila fila = new Fila(listaViagem.size());
            for (ViagemModel viagem : listaViagem) {
                fila.insert(viagem);
            }
            System.out.println("Fila");
            fila.ordenar();
            fila.exibe();
            while (fila.getTamanho() > 3) {
                fila.poll();
            }
            System.out.println("Fila após refatorar");
            fila.exibe();
            return ResponseEntity.status(200).body(listaViagem);
        }
        return ResponseEntity.status(404).build();

    }
}