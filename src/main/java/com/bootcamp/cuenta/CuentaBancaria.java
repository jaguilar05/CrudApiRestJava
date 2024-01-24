package com.bootcamp.cuenta;

import com.bootcamp.estudiante.Estudiante;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class CuentaBancaria {
    @Id
    @Column(name = "id_cuenta") //de esta forma el id se asigna de forma automatica
    @SequenceGenerator(
            sequenceName = "sequence_cuenta",
            name = "sequence_cuenta"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequence_cuenta"
    )
    private Long id_cuenta;
    private Long numeroCuenta;
    private String banco;

    private String titular;

    @JsonIgnore
    @OneToOne(mappedBy = "cuenta")
    private Estudiante estudiante;

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public CuentaBancaria(){

    }
    public CuentaBancaria(Long id_cuenta, Long numeroCuenta, String banco, String titular) {
        this.id_cuenta = id_cuenta;
        this.numeroCuenta = numeroCuenta;
        this.banco = banco;
        this.titular = titular;
    }

    public Long getId_cuenta() {
        return id_cuenta;
    }

    public void setId_cuenta(Long id_cuenta) {
        this.id_cuenta = id_cuenta;
    }

    public Long getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(Long numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CuentaBancaria that = (CuentaBancaria) o;
        return Objects.equals(id_cuenta, that.id_cuenta) && Objects.equals(numeroCuenta, that.numeroCuenta) && Objects.equals(banco, that.banco) && Objects.equals(titular, that.titular);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_cuenta, numeroCuenta, banco, titular);
    }

    @Override
    public String toString() {
        return "CuentaBancaria{" +
                "id_cuenta=" + id_cuenta +
                ", numeroCuenta=" + numeroCuenta +
                ", banco='" + banco + '\'' +
                ", titular='" + titular + '\'' +
                '}';
    }
}
