package com.api.travelsisters.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Optional;

@Entity
@Table(name = "chat_viagem")
public class ChatViagemModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat")
    private Integer chat;

    @Nullable
    @Column(name = "dt_chegada")
    private LocalDate dtChegada;

    @Column(name = "usuario")
    private Optional<UsuarioModel> usuario;

    @Column(name = "viagem")
    private Optional<ViagemModel> viagem;

    @Nullable
    @Column(name = "hist_avaliacao")
    private Optional<HistoricoAvaliacaoModel> historicoAvaliacao;

    public ChatViagemModel(Integer chat, LocalDate dtChegada, Optional<UsuarioModel> usuario, Optional<ViagemModel> viagem, Optional<HistoricoAvaliacaoModel> historicoAvaliacao) {
        this.chat = chat;
        this.dtChegada = dtChegada;
        this.usuario = usuario;
        this.viagem = viagem;
        this.historicoAvaliacao = historicoAvaliacao;
    }

    public Integer getChat() {
        return chat;
    }

    public void setChat(Integer chat) {
        this.chat = chat;
    }

    public LocalDate getDtChegada() {
        return dtChegada;
    }

    public void setDtChegada(LocalDate dtChegada) {
        this.dtChegada = dtChegada;
    }

    public Optional<UsuarioModel> getUsuario() {
        return usuario;
    }

    public void setUsuario(Optional<UsuarioModel> usuario) {
        this.usuario = usuario;
    }

    public Optional<ViagemModel> getViagem() {
        return viagem;
    }

    public void setViagem(Optional<ViagemModel> viagem) {
        this.viagem = viagem;
    }

    public Optional<HistoricoAvaliacaoModel> getHistoricoAvaliacao() {
        return historicoAvaliacao;
    }

    public void setHistoricoAvaliacao(Optional<HistoricoAvaliacaoModel> historicoAvaliacao) {
        this.historicoAvaliacao = historicoAvaliacao;
    }
}
