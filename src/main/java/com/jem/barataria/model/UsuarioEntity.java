package com.jem.barataria.model;

import java.io.Serializable;

import com.jem.barataria.security.RolUsuario;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "USUARIOS")
public class UsuarioEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO")
    private Long idUsuario;

    @ManyToOne
    @JoinColumn(name = "ID_EDITORIAL")
    private GrupoBaratariaEntity grupoBarataria;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "APELLIDO1")
    private String apellido1;

    @Column(name = "APELLIDO2")
    private String apellido2;

    @Column(name = "TELEFONO")
    private String telefono;

    @Column(name = "EMAIL", unique = true)
    private String email;

    @Column(name = "NOMBREUSUARIO", unique = true)
    private String nombreUsuario;

    @Column(name = "CONTRASEÑA")
    private String contraseña;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROL_USUARIO")
    private RolUsuario rolUsuario;

    @Column(name = "USUARIO_ACTIVO")
    private Boolean usuarioActivo;
}