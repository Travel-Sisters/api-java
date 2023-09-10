package com.api.travelsisters.repository;

import com.api.travelsisters.model.UsuarioModel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UsuarioRepository extends CrudRepository<UsuarioModel, Integer> {

    List<UsuarioModel> findAll();
    UsuarioModel findById(int id);
    void delete(UsuarioModel cadastro);
    <CadMod extends UsuarioModel> CadMod save(CadMod cadastro);

}

