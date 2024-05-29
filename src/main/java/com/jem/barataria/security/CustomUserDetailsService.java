package com.jem.barataria.security;

import com.jem.barataria.model.UsuarioEntity;
import com.jem.barataria.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
        UsuarioEntity usuario = usuarioRepository.findByNombreUsuarioOrEmail(identifier)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + identifier));

        String roleName = "ROLE_" + usuario.getRolUsuario().name().toUpperCase();

        return User.builder()
                .username(usuario.getNombreUsuario())
                .password(usuario.getContraseña())
                .authorities(roleName)
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(!usuario.getUsuarioActivo())
                .build();
    }
}