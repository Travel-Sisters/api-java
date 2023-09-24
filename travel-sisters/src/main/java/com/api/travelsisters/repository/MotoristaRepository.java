package com.api.travelsisters.repository;

import com.api.travelsisters.model.MotoristaModel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MotoristaRepository extends CrudRepository<MotoristaModel, Integer> {

    List<MotoristaModel> findAll();

    MotoristaModel findById(int id);

    void delete(MotoristaModel cadastro);

    <CadMod extends MotoristaModel> CadMod save(CadMod cadastro);

}

