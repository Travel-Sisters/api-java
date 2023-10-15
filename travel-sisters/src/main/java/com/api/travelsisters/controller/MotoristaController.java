package com.api.travelsisters.controller;

import com.api.travelsisters.model.MotoristaModel;
import com.api.travelsisters.model.UsuarioModel;
import com.api.travelsisters.repository.MotoristaRepository;
import com.api.travelsisters.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/motoristas")
public class MotoristaController {
    @Autowired
    private MotoristaRepository repository;
    @CrossOrigin
    @GetMapping("/")
    public ResponseEntity<List<MotoristaModel>> listar() {
        if (repository.findAll().isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(repository.findAll());
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<MotoristaModel> findByID(@Valid @PathVariable int id) {
        return ResponseEntity.of(repository.findById(id));
    }

    @CrossOrigin
    @PostMapping("/")
    public ResponseEntity<MotoristaModel> cadastrar
            (@Valid @RequestBody MotoristaModel cadastro) {

        repository.save(cadastro);
        return ResponseEntity.status(201).body(cadastro);

    }

    @CrossOrigin
    @PutMapping("/")
    public ResponseEntity<MotoristaModel> alterar
            (@Valid @RequestBody MotoristaModel cadastro) {
        return ResponseEntity.status(200).body(repository.save(cadastro));
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@Valid @PathVariable int id) {

        try {
            MotoristaModel cadastro = findByID(id).getBody();
            assert cadastro != null;
            this.repository.delete(cadastro);
            return ResponseEntity.status(200).build();
        } catch (Exception error) {
            return ResponseEntity.status(404).build();
        }
    }
}


