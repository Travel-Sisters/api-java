package com.api.travelsisters.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuario", uniqueConstraints = @UniqueConstraint(columnNames = {"cpf", "email"} ))
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    @Setter
    private int id;
    @NotBlank
    @Size(min = 3, max = 100)
    @Column(name = "nome")
    @Getter
    @Setter
    private String nome;
    @CPF
    @Column(name = "cpf")
    @Getter
    @Setter
    private String cpf;
    @Email
    @Column(name = "email")
    @Getter
    @Setter
    private String email;
    @NotBlank
    @Column(name = "senha")
    @Getter
    @Setter
    private String senha;
    @PastOrPresent
    @NotNull
    @Getter
    @Setter
    private LocalDate nascimento;

}
