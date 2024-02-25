package com.api.travelsisters.repository;

import com.api.travelsisters.model.UsuarioViagemModel;
import com.api.travelsisters.model.ViagemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsuarioViagemRepository extends JpaRepository<UsuarioViagemModel, Integer> {
    @Query("SELECT v FROM ViagemModel v " +
            "JOIN UsuarioViagemModel uv ON v.id = uv.viagem.id " +
            "JOIN UsuarioModel u ON uv.usuario.id = u.id " +
            "WHERE u.id = :usuarioId")
    List<ViagemModel> findByUsuarioViagemId(@Param("usuarioId") Integer usuarioId);
}
