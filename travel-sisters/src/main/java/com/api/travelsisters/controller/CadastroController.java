package com.api.travelsisters.controller;

import com.api.travelsisters.crud.CadastroCRUD;
import com.api.travelsisters.model.CadastroModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cadastro")
public class CadastroController {
    @Autowired
    private CadastroCRUD metodo;

    @GetMapping("/listar")
    public @ResponseBody List<CadastroModel> listar() {
        return metodo.findAll();
    }

    @PostMapping("/cadastrar")
    public @ResponseBody CadastroModel cadastrar
            (@RequestBody CadastroModel cadastro) {
        return metodo.save(cadastro);
    }

    @GetMapping("/buscar/{id}")
    public @ResponseBody CadastroModel findByID
            (@PathVariable int id) {
        return metodo.findById(id);
    }

    @PutMapping("/alterar")
    public @ResponseBody CadastroModel alterar
            (@RequestBody CadastroModel cadastro) {
        return metodo.save(cadastro);
    }

    @DeleteMapping("/deletar/{id}")
    public @ResponseBody String deletar(@PathVariable int id) {

        try {
            CadastroModel cadastro = findByID(id);
            this.metodo.delete(cadastro);
            return "Cadastro deletado com sucesso".formatted();
        } catch (Exception erro) {
            return "Erro ao deletar cadastro: " + erro.getMessage();
        }
    }
}


