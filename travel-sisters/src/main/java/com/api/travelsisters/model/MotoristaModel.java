package com.api.travelsisters.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

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
    @ManyToOne
    @JoinColumn(name = "empresa")
    private EmpresaModel fkEmpresa;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "usuario")
    private UsuarioModel fkUsuario;
    @Column(name = "handler")
    private Integer handler;


    public MotoristaModel(int id, String cnh, String placaVan, EmpresaModel fkEmpresa,
                          UsuarioModel fkUsuario, int handler) {
        this.id = id;
        this.cnh = cnh;
        this.placaVan = placaVan;
        this.fkEmpresa = fkEmpresa;
        this.fkUsuario = fkUsuario;
        this.handler = handler;
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

    public UsuarioModel getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(UsuarioModel fkUsuario) {
        this.fkUsuario = fkUsuario;
    }

    public int getHandler() {
        return handler;
    }

    public void setHandler(int handler) {
        this.handler = handler;
    }

    @Override
    public String toString() {
        return "MotoristaModel{" +
                "id=" + id +
                ", cnh='" + cnh + '\'' +
                ", placaVan='" + placaVan + '\'' +
                ", fkEmpresa=" + fkEmpresa +
                ", fkUsuario=" + fkUsuario +
                ", handler=" + handler +
                '}';
    }
}


