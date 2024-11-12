package com.alura.literatura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(
        @JsonAlias("id") Long Id,
        @JsonAlias("title")String titulo,
        @JsonAlias("authors") Set<DatosAutor> autores,
        @JsonAlias("copyright") boolean copyright,
        @JsonAlias("download_count") int descargas,
        @JsonAlias("languages") List<String> idiomas
) {
}
