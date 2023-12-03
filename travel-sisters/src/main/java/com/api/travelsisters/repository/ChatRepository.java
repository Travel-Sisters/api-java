package com.api.travelsisters.repository;


import com.api.travelsisters.model.ChatModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ChatRepository extends JpaRepository<ChatModel, Integer> {

    ChatModel findTopByViagem_IdOrderByIdDesc(Integer idViagem);

    List<ChatModel> findAllByViagem_Id(Integer idViagem);

    @Modifying
    @Transactional
    @Query("UPDATE ChatModel c SET c.dtChegada = :novaData WHERE c.id = :id")
    void updateDtChegadaById(@Param("novaData")LocalDate chegada, @Param("id") Integer id);


}
