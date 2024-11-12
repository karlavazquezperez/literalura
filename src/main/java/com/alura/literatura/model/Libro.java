package com.alura.literatura.model;

import jakarta.persistence.*;

import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name="libros")
public class Libro {
    @Id
    //@GeneratedValue(strategy= GenerationType.IDENTITY)
    private long Id;
    @Column(unique = true)
    private String titulo;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "autor_libro",
            joinColumns = @JoinColumn(name = "libro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private Set<Autor> autores = new HashSet<>();

    private boolean copyright;
    private int descargas;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> idiomas = new ArrayList<>();


    public Libro(){}

    public Libro(DatosLibro datosLibro){
        this.Id=datosLibro.Id();
        this.titulo=datosLibro.titulo();
        this.autores = datosLibro.autores().stream()
                .map(Autor::new)
                .collect(Collectors.toSet());

        this.copyright=datosLibro.copyright();
        this.descargas=datosLibro.descargas();
        this.idiomas=datosLibro.idiomas();

    }

    @Override
    public String toString() {
        return  "Título=" + titulo +
                ", Id=" + Id +
                ", autores=" + autores +
                ", copyright='" + copyright + '\'' +
                ", descargas='" + descargas + '\'' +
                ", idiomas='" + idiomas + '\'';
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Set<Autor> getAutores() {
        return autores;
    }


    public void addAutor(Autor autor) {
        this.autores.add(autor);
    }



    public void addAutores(Set<Autor> autores) {
        this.autores = autores;
    }







    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public boolean isCopyright() {
        return copyright;
    }

    public void setCopyright(boolean copyright) {
        this.copyright = copyright;
    }

    public int getDescargas() {
        return descargas;
    }

    public void setDescargas(int descargas) {
        this.descargas = descargas;
    }
}
