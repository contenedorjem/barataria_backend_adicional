package com.jem.barataria.model;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "GRUPO_BARATARIA")
public class GrupoBaratariaEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_EDITORIAL")
    private Long idEditorial;

    @Column(name = "NOMBRE_EDITORIAL", nullable = false, unique = true)
    private String nombreEditorial;

    @Lob
    @Column(columnDefinition = "TEXT", name = "TEXTO_EDITORIAL")
    private String textoEditorial;
    
    @Lob
    @Column(columnDefinition = "TEXT", name = "DESCRIPCION")
    private String textoDescripcion;

    @Lob
    @Column(columnDefinition = "TEXT", name = "ADICIONAL")
    private String textoAdicional;

    @OneToMany(mappedBy = "grupoBarataria")
    private Set<UsuarioEntity> usuarios;

    @OneToMany(mappedBy = "grupoBarataria")
    private Set<DistribucionEntity> distribuciones;

    @OneToMany(mappedBy = "grupoBarataria")
    private Set<ColeccionEntity> colecciones;
    
}