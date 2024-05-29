package com.jem.barataria.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AutorDto {
    private Long idAutor;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String textoAutor;

    @JsonIgnore
    private Set<Long> librosIds;

    public AutorDto(Long idAutor, String nombre, String apellido1, String apellido2, String textoAutor) {
        this.idAutor = idAutor;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.textoAutor = textoAutor;
    }
}
