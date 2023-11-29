package com.api.travelsisters.repository;

import com.api.travelsisters.dto.AlterarPerfilMotoristaDTO;
import com.api.travelsisters.model.MotoristaModel;
import com.api.travelsisters.model.UsuarioModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

   @Modifying
   @Transactional
   @Query("UPDATE UsuarioModel u SET " +
           "u.nome = CASE WHEN :nome IS NOT NULL THEN :nome ELSE u.nome END, " +
           "u.email = CASE WHEN :email IS NOT NULL THEN :email ELSE u.email END, " +
           "u.senha = CASE WHEN :senha IS NOT NULL THEN :senha ELSE u.senha END " +
           "WHERE u.id = (SELECT m.usuario.id FROM MotoristaModel m WHERE m.id = :id)")
   void atualizarPerfilMotorista(
           @Param("id") Integer id,
           @Param("nome") String nome,
           @Param("email") String email,
           @Param("senha") String senha
   );

}

