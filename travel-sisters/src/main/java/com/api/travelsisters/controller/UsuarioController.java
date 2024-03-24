package com.api.travelsisters.controller;


import com.api.travelsisters.dto.AlterarPerfilMotoristaDTO;
import com.api.travelsisters.dto.AlterarPerfilPassageiraDTO;
import com.api.travelsisters.dto.LoginDTO;
import com.api.travelsisters.model.MotoristaModel;
import com.api.travelsisters.repository.MotoristaRepository;
import com.api.travelsisters.repository.UsuarioRepository;
import com.api.travelsisters.model.UsuarioModel;
import com.api.travelsisters.service.UsuarioService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private MotoristaRepository repositoryMotorista;

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
    @PutMapping("/alterar/{idUsuario}")
    public ResponseEntity alterar
            (@RequestBody AlterarPerfilPassageiraDTO passageiraDTO,
             @PathVariable Integer idUsuario) {

        if(passageiraDTO.getNome() == null || passageiraDTO.getNome().isEmpty())
            passageiraDTO.setNome(null);

        if(passageiraDTO.getEmail() == null || passageiraDTO.getEmail().isEmpty())
            passageiraDTO.setEmail(null);

        if(passageiraDTO.getSenha() == null || passageiraDTO.getSenha().isEmpty())
            passageiraDTO.setSenha(null);

        repository.atualizarPerfilUsuario(
                idUsuario,
                passageiraDTO.getNome(),
                passageiraDTO.getEmail(),
                passageiraDTO.getSenha());

        return ResponseEntity.status(200).build();
    }

    @CrossOrigin
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity deletar(@Valid @PathVariable int id) {

        try {
            UsuarioModel cadastro = findByID(id).getBody();
            if (cadastro != null) {
                this.repository.delete(cadastro);
                return ResponseEntity.status(200).build();
            } else {
                return ResponseEntity.status(404).build();
            }
        } catch (Exception error) {
            return ResponseEntity.status(404).build();
        }
    }

    @PostMapping("/entrar")
    public ResponseEntity entrar(@RequestBody LoginDTO login) {

        ResponseEntity resposta = usuarioService.autenticar(login);

        return ResponseEntity.status(resposta.getStatusCode()).body(resposta.getBody());
    }

    @GetMapping("/verificar-perfil/{idUsuario}")
    public ResponseEntity<MotoristaModel> verificarPerfil(@PathVariable Integer idUsuario) {
        MotoristaModel motorista = repositoryMotorista.encontrarPorUsuarioId(idUsuario);

        if(motorista == null){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(motorista);
    }
}



