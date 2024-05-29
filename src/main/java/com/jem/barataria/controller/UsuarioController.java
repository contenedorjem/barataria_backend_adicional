package com.jem.barataria.controller;

import com.jem.barataria.dto.UsuarioDto;
import com.jem.barataria.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioDto>> getAllUsuarios() {
        try {
            List<UsuarioDto> usuarios = usuarioService.findAll();
            return ResponseEntity.ok(usuarios);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/registro")
    public ResponseEntity<UsuarioDto> registrarUsuario(@RequestBody UsuarioDto usuarioDto, @RequestParam String confirmacionContraseña) {
        UsuarioDto usuarioRegistrado = usuarioService.registrarUsuario(usuarioDto, confirmacionContraseña);
        return ResponseEntity.ok(usuarioRegistrado);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<UsuarioDto> obtenerUsuario(@PathVariable Long id) {
        UsuarioDto usuario = usuarioService.buscarPorId(id);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.ok().build();
    }
}
