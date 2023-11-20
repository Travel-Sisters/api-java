package com.api.travelsisters.repository;

import com.api.travelsisters.dto.LoginDTO;
import com.api.travelsisters.model.EmpresaModel;
import com.api.travelsisters.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Integer> {
    LoginDTO findByEmailAndSenha(String email, String senha);
    UsuarioModel findByEmail(String username);

    @Query("SELECT e FROM UsuarioModel e WHERE e.id = :id")
    UsuarioModel encontrarPorId(@Param("id") Integer id);

}

