package com.api.travelsisters.repository;

import com.api.travelsisters.model.ViagemModel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ViagemRepository extends CrudRepository<ViagemModel, Integer> {

    List<ViagemModel> findAll();
    ViagemModel findById(int id);
    void delete(ViagemModel cadastro);
    <CadMod extends ViagemModel> CadMod save(CadMod cadastro);

}

