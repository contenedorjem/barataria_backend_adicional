package com.jem.barataria.dto;

import com.jem.barataria.model.UsuarioEntity;
import com.jem.barataria.security.RolUsuario;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Data
public class UsuarioDto {
    private Long idUsuario;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String telefono;
    private String email;
    private String nombreUsuario;
    private RolUsuario rolUsuario;
    private Boolean usuarioActivo;
    private String contraseña;

    public UsuarioDto(UsuarioEntity usuario) {
        this.idUsuario = usuario.getIdUsuario();
        this.nombre = usuario.getNombre();
        this.apellido1 = usuario.getApellido1();
        this.apellido2 = usuario.getApellido2();
        this.telefono = usuario.getTelefono();
        this.email = usuario.getEmail();
        this.nombreUsuario = usuario.getNombreUsuario();
        this.rolUsuario = usuario.getRolUsuario();
        this.usuarioActivo = usuario.getUsuarioActivo();
        this.contraseña=usuario.getContraseña();
    }
}