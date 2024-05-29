package com.jem.barataria.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DistribucionDto {
    private Long idDistribuidor;
    private Long idEditorial;
    private String nombre;
    private String telefono1;
    private String telefono2;
    private String email1;
    private String email2;
    private String pais;
    private String region;
    private String direccion;
    private String web;
    private Boolean distribuidorEspania;
    private Boolean distribuidorActivo;
}