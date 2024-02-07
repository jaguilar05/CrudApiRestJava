package com.bootcamp.estudiante;

import com.bootcamp.materia.Materia;
import com.bootcamp.cuenta.CuentaBancaria;
import com.bootcamp.libro.Libro;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "estudiante")
public class Estudiante {
    @Id
    @Column(name = "id_estudiante") //de esta forma el id se asigna de forma automatica
    @SequenceGenerator(
            sequenceName = "sequence_estudiante",
            name = "sequence_estudiante"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequence_estudiante"
    )
    private Long id;
    @Column(length = 300,nullable = false)
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private LocalDate fechaNacimiento;
    @Column(nullable = false, unique = true)
    private String email;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "id_cuenta",
            referencedColumnName = "id_cuenta",
            unique = true
    )
    private CuentaBancaria cuenta;

    @OneToMany(mappedBy = "estudiante")
    private List<Libro> libros = new ArrayList<>();

    @ManyToMany(
            cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "inscripciones",
            joinColumns = @JoinColumn(
                    name = "estudiante_id",
                    referencedColumnName = "id_estudiante"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "materia_id",
                    referencedColumnName = "id_materia"
            )

    )
    private List<Materia> materias = new ArrayList<>();

    public List<Materia> getMaterias() {
        return materias;
    }

    public void addMateria(Materia materia){
        if (!materias.contains(materia)){
            materias.add(materia);
        }
    }

    public void removeMateria(Materia materia){
        materias.remove(materia);
    }

    public void setMaterias(List<Materia> materias) {
        this.materias = materias;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void addLibros(Libro libro) {
        if (!libros.contains(libro)){
            libros.add(libro);
        }
    }

    public void removeLibros(Libro libro){
        libros.remove(libro);
    }

    public CuentaBancaria getCuenta() {
        return cuenta;
    }

    public void setCuenta(CuentaBancaria cuenta) {
        this.cuenta = cuenta;
    }

    public Estudiante(){

    }


    public Estudiante(Long id, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, LocalDate fechaNacimiento, String email) {
        this.id = id;
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.fechaNacimiento = fechaNacimiento;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estudiante that = (Estudiante) o;
        return Objects.equals(id, that.id) && Objects.equals(primerNombre, that.primerNombre) && Objects.equals(segundoNombre, that.segundoNombre) && Objects.equals(primerApellido, that.primerApellido) && Objects.equals(segundoApellido, that.segundoApellido) && Objects.equals(fechaNacimiento, that.fechaNacimiento) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, primerNombre, segundoNombre, primerApellido, segundoApellido, fechaNacimiento, email);
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "id=" + id +
                ", primerNombre='" + primerNombre + '\'' +
                ", segundoNombre='" + segundoNombre + '\'' +
                ", primerApellido='" + primerApellido + '\'' +
                ", segundoApellido='" + segundoApellido + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", email='" + email + '\'' +
                '}';
    }
}
