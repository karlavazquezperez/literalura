package com.alura.literatura.repository;
import com.alura.literatura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    @Query("SELECT a FROM Autor a WHERE a.fechaNacimiento <= :anio AND (a.fechaDefunsion IS NULL OR a.fechaDefunsion > :anio)")
    List<Autor> findAutoresVivosEnAnio(@Param("anio") int anio);

    @Query("SELECT a FROM Autor a JOIN FETCH a.libros")
    List<Autor> findAllWithLibros();

    Optional<Autor> findByNombre(String nombre);





}
