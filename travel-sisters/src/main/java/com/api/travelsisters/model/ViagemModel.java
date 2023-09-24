package com.api.travelsisters.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "viagem")
public class ViagemModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    @Setter
    private int id;
    @Column(name = "data_viagem")
    @PastOrPresent
    @NotNull
    @Getter
    @Setter
    private LocalDate dataViagem;

    @Column(name = "ponto_desembarque")
    @NotNull
    @Getter
    @Setter
    private int pontoDesembarque;

    @Column(name = "ponto_encontro")
    @NotNull
    @Getter
    @Setter
    private int pontoEncontro;

    @Column(name = "descricao")
    @NotBlank
    @Getter
    @Setter
    private int descricao;

    @Column(name = "horario")
    @PastOrPresent
    @NotNull
    @Getter
    @Setter
    private LocalDate horario;

  }
