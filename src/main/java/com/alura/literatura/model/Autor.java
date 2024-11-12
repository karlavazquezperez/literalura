package com.alura.literatura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

import java.util.List;

@Entity
@Table(name = "autores", uniqueConstraints = {@UniqueConstraint(columnNames = "nombre")})
public class Autor {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(name = "nombre", unique = true)
    private String nombre;
    private Integer fechaNacimiento;
    private Integer fechaDefunsion;
    @ManyToMany(mappedBy = "autores", fetch = FetchType.EAGER)
    private Set<Libro> libros = new HashSet<>();


    public Autor() {}
    public Autor(DatosAutor datosAutor) {
        this.nombre = datosAutor.nombre();
        this.fechaNacimiento = datosAutor.fechaNacimiento();
        this.fechaDefunsion = datosAutor.fechaDefunsion();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(int fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getFechaDefunsion() {
        return fechaDefunsion;
    }

    public void setFechaDefunsion(int fechaDefunsion) {
        this.fechaDefunsion = fechaDefunsion;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
    public void addLibro(Libro libro) {

        if (!libros.contains(libro)) {
            libros.add(libro);
            libro.addAutor(this);
        }
    }


    public Set<Libro> getLibros(){
        return libros;
    }
}


