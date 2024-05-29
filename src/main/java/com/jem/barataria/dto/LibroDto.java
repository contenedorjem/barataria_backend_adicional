package com.jem.barataria.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class LibroDto {
        private Long idLibro;
        private String titulo;
        private String subtitulo;
        private String isbn;
        private String textoLibro;
        private Double precio;
        private Integer paginas;
        private String formato;
        private Integer anioPublicacion;
        private String traductor;
        private Boolean destacado;
        private Boolean libroActivo;
        private Long idColeccion;
        private Long idAutor;
        private AutorDto autor;
}