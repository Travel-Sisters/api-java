package com.api.travelsisters.repository;

import com.api.travelsisters.csv.ListaObj;
import com.api.travelsisters.dto.LoginDTO;
import com.api.travelsisters.model.MotoristaModel;
import com.api.travelsisters.model.ViagemModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface ViagemRepository extends JpaRepository<ViagemModel, Integer> {

    @Query("SELECT v FROM ViagemModel v WHERE v.motorista.id = :motoristaId")
    List<ViagemModel> findByMotoristaId(@Param("motoristaId") int motoristaId);

    @Modifying
    @Transactional
    @Query("UPDATE ViagemModel v SET v.statusViagem = :status WHERE v.id = :id")
    void atualizarCampoByMotoristaId(@Param("id") int id, @Param("status") String status);

    @Query("SELECT v FROM ViagemModel v WHERE v.id = :id")
    ViagemModel encontrarPorId(int id);

}

