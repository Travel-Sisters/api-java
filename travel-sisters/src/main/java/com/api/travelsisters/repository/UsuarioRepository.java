package com.api.travelsisters.repository;

import com.api.travelsisters.model.UsuarioModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<UsuarioModel, Integer> {

    List<UsuarioModel> findAll();
    UsuarioModel findById(int id);
    void delete(UsuarioModel cadastro);
    <CadMod extends UsuarioModel> CadMod save(CadMod cadastro);
    @Query(value = "select * from usuario where email = :email and senha = :senha", nativeQuery = true)
    String findByEmailAndSenha(@Param("email") String email, @Param("senha") String senha);

}

