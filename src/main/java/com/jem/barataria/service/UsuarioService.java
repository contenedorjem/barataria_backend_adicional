package com.jem.barataria.service;

import com.jem.barataria.dto.UsuarioDto;
import com.jem.barataria.model.UsuarioEntity;
import com.jem.barataria.repository.UsuarioRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsuarioDto registrarUsuario(UsuarioDto usuarioDto, String confirmacionContraseña) {
        if (!usuarioDto.getContraseña().equals(confirmacionContraseña)) {
            throw new IllegalArgumentException("Las contraseñas no coinciden.");
        }
        UsuarioEntity usuario = new UsuarioEntity();
        BeanUtils.copyProperties(usuarioDto, usuario);
        usuario.setContraseña(passwordEncoder.encode(usuarioDto.getContraseña()));
        usuario = usuarioRepository.save(usuario);
        return new UsuarioDto(usuario);
    }

    public Optional<UsuarioDto> autenticarUsuario(String identificador, String contraseña) {
        Optional<UsuarioEntity> usuario = usuarioRepository.findByNombreUsuarioOrEmail(identificador);
        return usuario.filter(usr -> passwordEncoder.matches(contraseña, usr.getContraseña()))
                      .map(UsuarioDto::new);
    }


    public List<UsuarioDto> findAll() {
        List<UsuarioEntity> usuarios = usuarioRepository.findAll();
        return usuarios.stream().map(UsuarioDto::new).collect(Collectors.toList());
    }

    public UsuarioDto buscarPorId(Long id) {
        UsuarioEntity usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado."));
        return new UsuarioDto(usuario);
    }

    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}
