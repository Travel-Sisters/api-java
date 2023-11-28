package com.api.travelsisters.repository;


import com.api.travelsisters.model.ChatModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<ChatModel, Integer> {

    List<ChatModel> findAllByViagem_Id(Integer viagem);

    ChatModel findTopByViagem_IdOrderByIdDesc(Integer viagem);

}
