package com.api.travelsisters.controller;


import com.api.travelsisters.dto.LoginDTO;
import com.api.travelsisters.repository.UsuarioRepository;
import com.api.travelsisters.model.UsuarioModel;
import com.api.travelsisters.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private UsuarioService usuarioService;

    private PasswordEncoder encoder;

    public UsuarioController(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @CrossOrigin
    @GetMapping("/listar")
    public ResponseEntity<List<UsuarioModel>> listar() {
        if (repository.findAll().isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(repository.findAll());
    }

    @CrossOrigin
    @GetMapping("/buscarPorId/{id}")
    public ResponseEntity<UsuarioModel> findByID(@Valid @PathVariable int id) {
        return ResponseEntity.of(repository.findById(id));
    }

    @CrossOrigin
    @PostMapping("/cadastrar")
    public ResponseEntity<UsuarioModel> cadastrar
            (@Valid @RequestBody UsuarioModel cadastro) {

        cadastro.setSenha(encoder.encode(cadastro.getSenha()));
        repository.save(cadastro);
        return ResponseEntity.status(201).body(cadastro);

    }

    @CrossOrigin
    @PutMapping("/alterar")
    public ResponseEntity<UsuarioModel> alterar
            (@Valid @RequestBody UsuarioModel cadastro) {
        return ResponseEntity.status(200).body(repository.save(cadastro));
    }

    @CrossOrigin
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletar(@Valid @PathVariable int id) {

        try {
            UsuarioModel cadastro = findByID(id).getBody();
            assert cadastro != null;
            this.repository.delete(cadastro);
            return ResponseEntity.status(200).build();
        } catch (Exception error) {
            return ResponseEntity.status(404).build();
        }
    }

    @PostMapping("/entrar")
    public ResponseEntity entrar(@RequestBody LoginDTO login) {

        ResponseEntity resposta = usuarioService.autenticar(login);

        return ResponseEntity.status(resposta.getStatusCode()).body(resposta.getBody());
    }
}



