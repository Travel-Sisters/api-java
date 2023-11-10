package com.api.travelsisters.model;

import jakarta.persistence.*;

@Entity
@Table(name = "empresa")
public class EmpresaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
}
