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
    @JoinColumn(name = "viagem")
    private ViagemModel viagem;

    @Column(name = "dt_chegada")
    private LocalDate dtChegada;

    public ChatModel(Integer id, String mensagem, LocalDate data, LocalTime hora, ViagemModel viagem) {
        this.id = id;
        this.mensagem = mensagem;
        this.data = data;
        this.hora = hora;
        this.viagem = viagem;
    }

    public ChatModel(Integer id, String mensagem, LocalDate data, LocalTime hora, ViagemModel viagem, LocalDate dtChegada) {
        this.id = id;
        this.mensagem = mensagem;
        this.data = data;
        this.hora = hora;
        this.viagem = viagem;
        this.dtChegada = dtChegada;
    }

    public ChatModel() {
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

    public void setViagem(ViagemModel viagem) {
        this.viagem = viagem;
    }

    public LocalDate getDtChegada() {
        return dtChegada;
    }

    public void setDtChegada(LocalDate dtChegada) {
        this.dtChegada = dtChegada;
    }
}
