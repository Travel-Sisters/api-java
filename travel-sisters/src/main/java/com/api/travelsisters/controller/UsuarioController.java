package com.api.travelsisters.controller;

import com.api.travelsisters.repository.UsuarioRepository;
import com.api.travelsisters.model.UsuarioModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository repository;

    @GetMapping("/")
    public ResponseEntity<List<UsuarioModel>> listar() {
        if (repository.findAll().isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioModel> findByID(@PathVariable int id) {
        if (repository.findById(id) != null) {
            return ResponseEntity.status(200).body(repository.findById(id));
        }
        return ResponseEntity.status(404).build();
    }

    @PostMapping("/")
    public ResponseEntity<UsuarioModel> cadastrar
            (@RequestBody UsuarioModel cadastro) {

        repository.save(cadastro);
        return ResponseEntity.status(201).body(cadastro);

    }


    @PutMapping("/")
    public ResponseEntity<UsuarioModel> alterar
            (@RequestBody UsuarioModel cadastro) {
        return ResponseEntity.status(200).body(repository.save(cadastro));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable int id) {

        try {
            UsuarioModel cadastro = findByID(id).getBody();
            assert cadastro != null;
            this.repository.delete(cadastro);
            return ResponseEntity.status(200).build();
        } catch (Exception error) {
            return ResponseEntity.status(404).build();
        }
    }
}


