package com.jem.barataria.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "LIBROS")
public class LibroEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_LIBRO")
    private Long idLibro;

    @ManyToOne
    @JoinColumn(name = "ID_COLECCION")
    private ColeccionEntity coleccion;

    @ManyToOne
    @JoinColumn(name = "ID_AUTOR")
    private AutorEntity autor;

    @Column(name = "TITULO")
    private String titulo;

    @Column(name = "SUBTITULO")
    private String subtitulo;

    @Column(name = "ISBN", unique = true)
    private String isbn;

    @Lob
    @Column(columnDefinition = "TEXT", name = "TEXTO_LIBRO")
    private String textoLibro;

    @Column(name = "PRECIO")
    private Double precio;

    @Column(name = "PAGINAS")
    private Integer paginas;

    @Column(name = "FORMATO")
    private String formato;

    @Column(name = "ANIO_PUBLICACION")
    private Integer anioPublicacion;

    @Column(name = "TRADUCTOR")
    private String traductor;

    @Column(name = "DESTACADO")
    private Boolean destacado;

    @Column(name = "LIBRO_ACTIVO")
    private Boolean libroActivo;
}