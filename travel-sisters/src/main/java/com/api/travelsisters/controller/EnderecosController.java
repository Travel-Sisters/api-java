package com.api.travelsisters.controller;

import com.api.travelsisters.model.EnderecosModel;
import com.api.travelsisters.repository.EnderecosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/enderecos")
public class EnderecosController {

    @Autowired
    private EnderecosRepository repository;
    @CrossOrigin
    @GetMapping("/")
    public ResponseEntity<List<EnderecosModel>> get() {
        List enderecos = repository.findAll();
        if(enderecos.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(enderecos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecosModel> findById (@PathVariable Integer id) {
        return ResponseEntity.of(repository.findById(id));
    }

}
