package com.api.travelsisters.model;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "email")
    private String email;
    @Column(name = "senha")
    private String senha;
    @Column(name = "cad_passageira")
    private int cadPassageira;

    public UsuarioModel() {
    }

    public UsuarioModel(int id, String email, String senha, int cadPassageira) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.cadPassageira = cadPassageira;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getCadPassageira() {
        return cadPassageira;
    }

    public void setCadPassageira(int cadPassageira) {
        this.cadPassageira = cadPassageira;
    }
}
