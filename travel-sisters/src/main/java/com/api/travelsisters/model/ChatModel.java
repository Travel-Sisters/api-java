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
    @ManyToOne
    @JoinColumn(name = "viagem")
    private ViagemModel viagem;

    @Column(name = "dt_chegada")
    private LocalDate dtChegada;

    public ChatModel(Integer id, ViagemModel viagem, LocalDate dtChegada) {
        this.id = id;
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

    public ViagemModel getViagem() {
        return viagem;
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
