package com.api.travelsisters.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
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
    @PastOrPresent
    @NotNull
    private LocalDate data;

    @Column(name = "ponto_desembarque")
    @NotNull
    private String pontoDesembarque;

    @Column(name = "ponto_encontro")
    @NotNull
    private String pontoEncontro;

    @Column(name = "descricao")
    @NotBlank
    private String descricao;

    @Column(name = "horario")
    @PastOrPresent
    @NotNull
    private LocalTime horario;
    @Column(name = "valor")
    @NotNull
    private Double valor;

}
