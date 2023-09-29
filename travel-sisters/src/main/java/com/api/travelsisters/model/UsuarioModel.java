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
@Getter
@Setter
@Table(name = "usuario", uniqueConstraints = @UniqueConstraint(columnNames = {"cpf", "email"} ))
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @NotBlank
    @Size(min = 3, max = 100)
    @Column(name = "nome")
    private String nome;
    @CPF
    @Column(name = "cpf")
    private String cpf;
    @Email
    @Column(name = "email")
    private String email;
    @NotBlank
    @Column(name = "senha")
    private String senha;
    @PastOrPresent
    @NotNull
    private LocalDate nascimento;

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }
}
