package com.api.travelsisters.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "motorista", uniqueConstraints = @UniqueConstraint(columnNames = {"cnh", "placa_van"}))
public class MotoristaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    @Setter
    private int id;
    @NotBlank
    @Column(name = "cnh")
    @Getter
    @Setter
    private String cnh;
    @NotBlank
    @Column(name = "placa_van")
    @Getter
    @Setter
    private String placaVan;

    @NotNull
    @Column(name = "empresa")
    @Getter
    @Setter
    private String fkEmpresa;

}


