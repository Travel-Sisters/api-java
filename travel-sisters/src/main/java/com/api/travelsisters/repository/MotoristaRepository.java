package com.api.travelsisters.repository;

import com.api.travelsisters.model.MotoristaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MotoristaRepository extends JpaRepository<MotoristaModel, Integer> {


}

