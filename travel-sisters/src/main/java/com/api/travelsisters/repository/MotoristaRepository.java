package com.api.travelsisters.repository;

import com.api.travelsisters.model.MotoristaModel;
import com.api.travelsisters.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MotoristaRepository extends JpaRepository<MotoristaModel, Integer> {

   MotoristaModel findByUsuario_Id(Integer idUsuario);
   @Query("SELECT e FROM MotoristaModel e WHERE e.id = :id")
   MotoristaModel encontrarPorId(@Param("id") Integer id);

   @Query("SELECT m FROM MotoristaModel m WHERE m.usuario.id = :idUsuario")
   MotoristaModel encontrarPorUsuarioId(@Param("idUsuario") Integer idUsuario);

}

