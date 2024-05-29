package com.jem.barataria.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "DISTRIBUCION")
public class DistribucionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DISTRIBUIDOR")
    private Long idDistribuidor;

    @ManyToOne
    @JoinColumn(name = "ID_EDITORIAL")
    private GrupoBaratariaEntity grupoBarataria;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "TELEFONO1")
    private String telefono1;

    @Column(name = "TELEFONO2")
    private String telefono2;

    @Column(name = "EMAIL1")
    private String email1;

    @Column(name = "EMAIL2")
    private String email2;

    @Column(name = "PAIS")
    private String pais;

    @Column(name = "REGION")
    private String region;

    @Column(name = "DIRECCION")
    private String direccion;

    @Column(name = "WEB")
    private String web;

    @Column(name = "DISTRIBUIDOR_ESPANIA")
    private Boolean distribuidorEspania;

    @Column(name = "DISTRIBUIDOR_ACTIVO")
    private Boolean distribuidorActivo;
}
