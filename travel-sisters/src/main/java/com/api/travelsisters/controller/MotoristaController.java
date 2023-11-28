package com.api.travelsisters.controller;

import com.api.travelsisters.model.EmpresaModel;
import com.api.travelsisters.model.MotoristaModel;
import com.api.travelsisters.model.UsuarioModel;
import com.api.travelsisters.repository.EmpresaRepository;
import com.api.travelsisters.repository.MotoristaRepository;
import com.api.travelsisters.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/motoristas")
public class MotoristaController {
    @Autowired
    private MotoristaRepository repository;

    @Autowired
    private EmpresaRepository empresaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;


    @CrossOrigin
    @GetMapping("/listar")
    public ResponseEntity<List<MotoristaModel>> listar() {
        if (repository.findAll().isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(repository.findAll());
    }

    @CrossOrigin
    @GetMapping("/buscarPorId/{id}")
    public ResponseEntity<MotoristaModel> findByID(@Valid @PathVariable int id) {
        return ResponseEntity.of(repository.findById(id));
    }

    @CrossOrigin
    @PostMapping("/cadastrar/{idUsuario}")
    public ResponseEntity<String> cadastrar
            (@Valid @RequestBody MotoristaModel cadastro,
             @PathVariable Integer idUsuario) {

        EmpresaModel empresaModel = empresaRepository.encontrarPorId(cadastro.getFkEmpresa().getId());
        UsuarioModel usuarioModel = usuarioRepository.encontrarPorId(idUsuario);

        if (empresaModel == null) {
            if(usuarioModel != null) {
                usuarioRepository.delete(usuarioModel);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("fk da empresa não encontrado no banco de dados");

        } else if (usuarioModel == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("usuário não encontrados no banco de dados");
        }

        cadastro.setFkEmpresa(empresaModel);
        cadastro.setUsuario(usuarioModel);
        repository.save(cadastro);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @CrossOrigin
    @PutMapping("/alterar")
    public ResponseEntity<MotoristaModel> alterar
            (@Valid @RequestBody MotoristaModel cadastro) {
        return ResponseEntity.status(200).body(repository.save(cadastro));
    }

    @CrossOrigin
    @DeleteMapping("/deletar/{id}")
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


