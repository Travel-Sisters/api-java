package com.api.travelsisters.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@Table(name = "viagem")
public class ViagemModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "data_viagem")
    @NotNull
    private LocalDate data;
    @Column(name = "ponto_embarque")
    @NotNull
    private String pontoEmbarque;
    @Column(name = "ponto_desembarque")
    @NotNull
    private String pontoDesembarque;
    @Column(name = "descricao")
    @NotBlank
    private String descricao;
    @Column(name = "horario")
    @NotNull
    private LocalTime horario;
    @Column(name = "valor")
    @NotNull
    private Double valor;

    public ViagemModel(int id, LocalDate data, String pontoEmbarque, String pontoDesembarque, String descricao, LocalTime horario, Double valor) {
        this.id = id;
        this.data = data;
        this.pontoEmbarque = pontoEmbarque;
        this.pontoDesembarque = pontoDesembarque;
        this.descricao = descricao;
        this.horario = horario;
        this.valor = valor;
    }

    public ViagemModel() {
    }

    public int getId() {
        return id;
    }

    public LocalDate getData() {
        return data;
    }

    public String getPontoEmbarque() {
        return pontoEmbarque;
    }

    public String getPontoDesembarque() {
        return pontoDesembarque;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public Double getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return "ViagemModel{" +
                "id=" + id +
                ", data=" + data +
                ", pontoEmbarque='" + pontoEmbarque + '\'' +
                ", pontoDesembarque='" + pontoDesembarque + '\'' +
                ", descricao='" + descricao + '\'' +
                ", horario=" + horario +
                ", valor=" + valor +
                '}';
    }
}
