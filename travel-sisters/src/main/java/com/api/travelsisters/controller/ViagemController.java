package com.api.travelsisters.controller;

import com.api.travelsisters.csv.GerenciadorDeArquivo;
import com.api.travelsisters.csv.ListaObj;
import com.api.travelsisters.model.ViagemModel;
import com.api.travelsisters.repository.ViagemRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/cadastrar")
    public ResponseEntity<ViagemModel> cadastrar
            (@Valid @RequestBody ViagemModel cadastro) {

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

    @PostMapping("/csv")
    public ResponseEntity<ViagemModel> csv
            (@RequestBody ViagemModel viagem) {

        ListaObj<ViagemModel> listaViagem = new ListaObj<>(1);

        listaViagem.adiciona(new ViagemModel(viagem.getId(), viagem.getData(),
                viagem.getPontoEmbarque(), viagem.getPontoDesembarque(),
                viagem.getDescricao(), viagem.getHorario(), viagem.getValor()));

        GerenciadorDeArquivo.gravaArquivoCsv(listaViagem, "viagem");

        System.out.println("Arquivo gerado com sucesso !");
        return ResponseEntity.status(200).build();

    }
}