package com.api.travelsisters.controller;

import com.api.travelsisters.model.ChatViagemModel;
import com.api.travelsisters.model.HistoricoAvaliacaoModel;
import com.api.travelsisters.model.UsuarioModel;
import com.api.travelsisters.model.ViagemModel;
import com.api.travelsisters.repository.ChatViagemRepository;
import com.api.travelsisters.repository.HistoricoAvaliacaoRepository;
import com.api.travelsisters.repository.UsuarioRepository;
import com.api.travelsisters.repository.ViagemRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/chat-viagem")
@CrossOrigin("*")
public class ChatViagemController {

    @Autowired
    ChatViagemRepository repository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ViagemRepository viagemRepository;

    @Autowired
    HistoricoAvaliacaoRepository historicoAvaliacaoRepository;

    @GetMapping("/")
    public ResponseEntity<List<ChatViagemModel>> getChatViagem() {
        List<ChatViagemModel> lista = repository.findAll();

        return lista.isEmpty() ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                ResponseEntity.status(HttpStatus.OK).body(lista);
    }

    @PostMapping("/{idUsuario}/{idViagem}/{idHistAvaliacao}")
    public ResponseEntity<?> postChatViagem(@PathVariable Integer idUsuario,
                                                          @PathVariable Integer idViagem,
                                                          @PathVariable Integer idHistAvaliacao,
                                                          @Valid @RequestBody ChatViagemModel corpo) {
        Optional<UsuarioModel> usuario = usuarioRepository.findById(idUsuario);
        Optional<ViagemModel> viagem = viagemRepository.findById(idViagem);
        Optional<HistoricoAvaliacaoModel> historicoAvaliacao = historicoAvaliacaoRepository.findById(idHistAvaliacao);
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("viagem not found in db");
        }
        if (viagem == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("usuario not found in db");
        }

        corpo.setUsuario(usuario);
        corpo.setViagem(viagem);
        if (historicoAvaliacao != null) {
            corpo.setHistoricoAvaliacao(historicoAvaliacao);
        }
        repository.save(corpo);
        return ResponseEntity.status(HttpStatus.CREATED).body(corpo);
    }


}
