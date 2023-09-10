package com.api.travelsisters.repository;

import com.api.travelsisters.model.PassageiraModel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PassageiraRepository extends CrudRepository<PassageiraModel, Integer> {

    List<PassageiraModel> findAll();
    PassageiraModel findById(int id);
    void delete(PassageiraModel cadastro);
    <CadMod extends PassageiraModel> CadMod save(CadMod cadastro);

}

