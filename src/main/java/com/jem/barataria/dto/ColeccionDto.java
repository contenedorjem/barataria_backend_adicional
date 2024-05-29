package com.jem.barataria.dto;

import java.util.List;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ColeccionDto {
    private Long id;
    private String nombreColeccion;
    private String textoColeccion;
    private String coleccionDescripcion;
    private String coleccionAdicional;
    private Boolean coleccionActiva;
    private List<LibroDto> libros;
}