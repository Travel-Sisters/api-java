package com.api.travelsisters.controller;

import com.api.travelsisters.model.ChatModel;
import com.api.travelsisters.repository.ChatRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/chat")
public class ChatContrller {

    @Autowired
    ChatRepository repository;

    @GetMapping("/{idViagem}")
    public ResponseEntity<List<ChatModel>> getChat(@PathVariable Integer idViagem) {

        List lista = repository.findAllByViagem_Id(idViagem);

        return lista.isEmpty() ?
                ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(lista);

    }

    @PostMapping("/")
    public ResponseEntity publicarMensagem(@Valid @RequestBody ChatModel chat) {

        repository.save(chat);
        return ResponseEntity.status(200).body(chat);

    }

    @GetMapping("/{idViagem}/ultima")
    public ResponseEntity getUltimaMensagem(@Valid @RequestBody Integer idViagem) {

        ChatModel cm = repository.findTopByViagem_IdOrderByIdDesc(idViagem);
        return cm == null ?
                ResponseEntity.status(404).build() :
                ResponseEntity.status(200).body(cm  );
    }

}
