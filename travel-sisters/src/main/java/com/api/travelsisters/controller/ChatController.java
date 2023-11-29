package com.api.travelsisters.controller;

import com.api.travelsisters.model.ChatModel;
import com.api.travelsisters.repository.ChatRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/chat")
@CrossOrigin("*")
public class ChatController {

    @Autowired
    ChatRepository repository;

    @GetMapping("/{idViagem}")
    public ResponseEntity<List<ChatModel>> getChat(@PathVariable Integer idViagem) {

        List lista = repository.findAllByViagem_Id(idViagem);

        return lista.isEmpty() ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                ResponseEntity.status(HttpStatus.OK).body(lista);

    }

    @PostMapping("/")
    public ResponseEntity publicarMensagem(@Valid @RequestBody ChatModel chat) {

        repository.save(chat);
        return ResponseEntity.status(HttpStatus.CREATED).body(chat);

    }

    @GetMapping("/{idViagem}/ultima")
    public ResponseEntity getUltimaMensagem(@Valid @RequestBody Integer idViagem) {

        ChatModel cm = repository.findTopByViagem_IdOrderByIdDesc(idViagem);
        return cm == null ?
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build() :
                ResponseEntity.status(HttpStatus.OK).body(cm);
    }

}
