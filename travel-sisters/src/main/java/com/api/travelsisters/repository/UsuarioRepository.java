package com.api.travelsisters.repository;

import com.api.travelsisters.dto.LoginDTO;
import com.api.travelsisters.model.EmpresaModel;
import com.api.travelsisters.model.UsuarioModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Integer> {
    LoginDTO findByEmailAndSenha(String email, String senha);
    UsuarioModel findByEmail(String username);

    @Query("SELECT e FROM UsuarioModel e WHERE e.id = :id")
    UsuarioModel encontrarPorId(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query("UPDATE UsuarioModel u SET " +
            "u.nome = CASE WHEN :nome IS NOT NULL THEN :nome ELSE u.nome END, " +
            "u.email = CASE WHEN :email IS NOT NULL THEN :email ELSE u.email END, " +
            "u.senha = CASE WHEN :senha IS NOT NULL THEN :senha ELSE u.senha END " +
            "WHERE u.id = :id")
    int atualizarPerfilUsuario(
            @Param("id") Integer id,
            @Param("nome") String nome,
            @Param("email") String email,
            @Param("senha") String senha
    );
}

