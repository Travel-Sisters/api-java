package com.api.travelsisters.model;

import jakarta.persistence.*;

import java.util.Random;

@Entity
@Table(name = "usuario_viagem")
public class UsuarioViagemModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private UsuarioModel usuario;

    @ManyToOne
    @JoinColumn(name = "viagem_id", referencedColumnName = "id")
    private ViagemModel viagem;

    public UsuarioViagemModel(UsuarioModel usuario, ViagemModel viagem) {
        this.usuario = usuario;
        this.viagem = viagem;
    }

    public UsuarioViagemModel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }

    public ViagemModel getViagem() {
        return viagem;
    }

    public void setViagem(ViagemModel viagem) {
        this.viagem = viagem;
    }

    @Override
    public String toString() {
        return "UsuarioViagemModel{" +
                "id=" + id +
                ", usuario=" + usuario +
                ", viagem=" + viagem +
                '}';
    }
}

