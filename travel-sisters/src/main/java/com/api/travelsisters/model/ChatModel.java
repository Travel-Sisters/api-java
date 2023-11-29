package com.api.travelsisters.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "chat")
public class ChatModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "mensagem")
    private String mensagem;

    @NotNull
    @Column(name = "data")
    private LocalDate data;

    @NotNull
    @Column(name = "hora")
    private LocalTime hora;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "chat_viagem")
    private ChatViagemModel chatViagem;

    public ChatModel(Integer id, String mensagem, LocalDate data, LocalTime hora, ChatViagemModel chatViagem) {
        this.id = id;
        this.mensagem = mensagem;
        this.data = data;
        this.hora = hora;
        this.chatViagem = chatViagem;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public ChatViagemModel getChatViagem() {
        return chatViagem;
    }

    public void setChatViagem(ChatViagemModel chatViagem) {
        this.chatViagem = chatViagem;
    }

}
