package com.api.travelsisters.model;

import jakarta.persistence.*;

@Entity
@Table(name = "empresa")
public class EmpresaModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    public EmpresaModel(Integer id) {
        this.id = id;
    }

    public EmpresaModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "EmpresaModel{" +
                "id=" + id +
                '}';
    }
}
