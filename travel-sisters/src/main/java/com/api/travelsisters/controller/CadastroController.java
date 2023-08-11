package com.api.travelsisters.controller;

import com.api.travelsisters.crud.CadastroCRUD;
import com.api.travelsisters.model.CadastroModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CadastroController {
    //instanciar o crud
    @Autowired
    private CadastroCRUD metodo;

    @GetMapping("/listarCadastros")
    public @ResponseBody List<CadastroModel> listar() {
        return metodo.findAll();
    }

    @PostMapping("/cadastrar")
    public @ResponseBody CadastroModel cadastrar
            (@RequestBody CadastroModel cadastro) {
        return metodo.save(cadastro);
    }
}


