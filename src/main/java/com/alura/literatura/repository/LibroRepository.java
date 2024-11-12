package com.alura.literatura.repository;

import com.alura.literatura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    @Query("SELECT l FROM Libro l JOIN FETCH l.idiomas")
    List<Libro> findAllWithIdiomas();
    @Query("SELECT l FROM Libro l JOIN l.idiomas i WHERE UPPER(i) = UPPER(:idioma)")
    List<Libro> findLibrosByIdioma(@Param("idioma") String idioma);


}
