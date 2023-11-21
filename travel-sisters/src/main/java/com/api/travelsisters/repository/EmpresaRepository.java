package com.api.travelsisters.repository;

import com.api.travelsisters.model.EmpresaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmpresaRepository extends JpaRepository<EmpresaModel, Integer> {
    @Query("SELECT e FROM EmpresaModel e WHERE e.id = :id")
    EmpresaModel encontrarPorId(@Param("id") Integer id);

}
