package com.jem.barataria.model;

import java.util.Set;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "colecciones")
public class ColeccionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_coleccion")
    private Long idColeccion;

    @ManyToOne
    @JoinColumn(name = "id_editorial")
    private GrupoBaratariaEntity grupoBarataria;

    @Column(name = "nombre_coleccion")
    private String nombreColeccion;
    
    @Lob
    @Column(columnDefinition = "TEXT", name = "texto_coleccion")
    private String textoColeccion;

    @Lob
    @Column(columnDefinition = "TEXT", name = "coleccion_descripcion")
    private String coleccionDescripcion;

    @Lob
    @Column(columnDefinition = "TEXT", name = "coleccion_adicional")
    private String coleccionAdicional;
    
    @Column(name = "coleccion_activa")
    private Boolean coleccionActiva;

    @OneToMany(mappedBy = "coleccion", fetch = FetchType.LAZY)
    private Set<LibroEntity> libros;
}
