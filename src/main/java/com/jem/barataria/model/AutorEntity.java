package com.jem.barataria.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Set;
import lombok.*;

import java.util.HashSet;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "autor")
public class AutorEntity implements Serializable { 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_autor")
    private Long idAutor;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "apellido1", nullable = false)
    private String apellido1;

    @Column(name = "apellido2", nullable = false)
    private String apellido2;

    @Lob
    @Column(columnDefinition = "TEXT", name = "texto_autor")
    private String textoAutor;

    @OneToMany(mappedBy = "autor", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<LibroEntity> libros = new HashSet<>(); // Inicializar la colección con una colección vacía
}
