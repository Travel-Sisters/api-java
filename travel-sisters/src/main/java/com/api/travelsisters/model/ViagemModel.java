package com.api.travelsisters.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "viagem")
public class ViagemModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @NotNull
    @Column(name = "data_viagem")
    private LocalDate data;
    @ManyToOne
    @JoinColumn(name = "ponto_embarque")
    private EnderecosModel pontoEmbarque;
    @ManyToOne
    @JoinColumn(name = "ponto_desembarque")
    private EnderecosModel pontoDesembarque;
    @Column(name = "descricao")
    @NotBlank
    private String descricao;
    @Column(name = "horario")
    @NotNull
    private LocalTime horario;
    @Column(name = "valor")
    @NotNull
    private Double valor;
    @ManyToOne
    @JoinColumn(name = "usuario")
    private UsuarioModel usuario;
    @ManyToOne
    @JoinColumn(name = "motorista")
    private MotoristaModel motorista;

    public ViagemModel() {
    }

    public ViagemModel(int id, LocalDate data, EnderecosModel pontoEmbarque,
                       EnderecosModel pontoDesembarque, String descricao,
                       LocalTime horario, Double valor, UsuarioModel usuario,
                       MotoristaModel motorista) {
        this.id = id;
        this.data = data;
        this.pontoEmbarque = pontoEmbarque;
        this.pontoDesembarque = pontoDesembarque;
        this.descricao = descricao;
        this.horario = horario;
        this.valor = valor;
        this.usuario = usuario;
        this.motorista = motorista;
    }

    public UsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }

    public MotoristaModel getMotorista() {
        return motorista;
    }

    public void setMotorista(MotoristaModel motorista) {
        this.motorista = motorista;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public EnderecosModel getPontoEmbarque() {
        return pontoEmbarque;
    }

    public void setPontoEmbarque(EnderecosModel pontoEmbarque) {
        this.pontoEmbarque = pontoEmbarque;
    }

    public EnderecosModel getPontoDesembarque() {
        return pontoDesembarque;
    }

    public void setPontoDesembarque(EnderecosModel pontoDesembarque) {
        this.pontoDesembarque = pontoDesembarque;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "ViagemModel{" +
                "id=" + id +
                ", data=" + data +
                ", pontoEmbarque=" + pontoEmbarque +
                ", pontoDesembarque=" + pontoDesembarque +
                ", descricao='" + descricao + '\'' +
                ", horario=" + horario +
                ", valor=" + valor +
                ", usuario=" + usuario +
                ", motorista=" + motorista +
                '}';
    }
}
