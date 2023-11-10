package com.api.travelsisters.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name = "motorista", uniqueConstraints = @UniqueConstraint(columnNames = {"cnh", "placa_van"}))
public class MotoristaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @NotBlank
    @Column(name = "cnh")
    private String cnh;
    @NotBlank
    @Column(name = "placa_van")
    private String placaVan;

    @NotNull
    @Column(name = "empresa")
    private int fkEmpresa;

    @NotNull
    @Column(name = "usuario")
    private int fkUsuario;


    public MotoristaModel(int id, String cnh, String placaVan, int fkEmpresa, int fkUsuario) {
        this.id = id;
        this.cnh = cnh;
        this.placaVan = placaVan;
        this.fkEmpresa = fkEmpresa;
        this.fkUsuario = fkUsuario;
    }

    public MotoristaModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public String getPlacaVan() {
        return placaVan;
    }

    public void setPlacaVan(String placaVan) {
        this.placaVan = placaVan;
    }

    public int getFkEmpresa() {
        return fkEmpresa;
    }

    public void setFkEmpresa(int fkEmpresa) {
        this.fkEmpresa = fkEmpresa;
    }

    public int getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(int fkUsuario) {
        this.fkUsuario = fkUsuario;
    }
}


