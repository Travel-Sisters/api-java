package com.api.travelsisters.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.Optional;

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

    @ManyToOne
    @JoinColumn(name = "empresa")
    private EmpresaModel fkEmpresa;

    @ManyToOne
    @JoinColumn(name = "usuario")
    private UsuarioModel usuario;


    public MotoristaModel(int id, String cnh, String placaVan, EmpresaModel fkEmpresa,
                          UsuarioModel usuario) {
        this.id = id;
        this.cnh = cnh;
        this.placaVan = placaVan;
        this.fkEmpresa = fkEmpresa;
        this.usuario = usuario;
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

    public EmpresaModel getFkEmpresa() {
        return fkEmpresa;
    }

    public void setFkEmpresa(EmpresaModel fkEmpresa) {
        this.fkEmpresa = fkEmpresa;
    }

    public UsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModel fkUsuario) {
        this.usuario = fkUsuario;
    }


    @Override
    public String toString() {
        return "MotoristaModel{" +
                "id=" + id +
                ", cnh='" + cnh + '\'' +
                ", placaVan='" + placaVan + '\'' +
                ", fkEmpresa=" + fkEmpresa +
                ", fkUsuario=" + usuario +
                '}';
    }
}


