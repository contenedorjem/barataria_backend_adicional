package com.jem.barataria.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.jem.barataria.model.UsuarioEntity;
import com.jem.barataria.repository.UsuarioRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
// ACTIVAR ESTE PARA ACTIVAR LA SEGURIDAD
    /* DESACTIVADA SEGURIDAD
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authz -> authz
                .requestMatchers(HttpMethod.GET, "/api/**").permitAll()
                .requestMatchers("/api/usuarios/registro", "/api/auth/**").permitAll()
                .requestMatchers("/favicon.ico").permitAll()
                .requestMatchers("/swagger-ui.html", "/swagger-ui/index.html", "/v3/api-docs", "/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**", "/webjars/**").permitAll()
                .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults());
        
        return http.build();
    }
 */
// ACTIVAR ESTE PARA DESACTIVAR SECURITY
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    // Deshabilita completamente la seguridad
    http.authorizeHttpRequests(authz -> authz.anyRequest().permitAll());
    
    // Opcionalmente, podrías querer deshabilitar CSRF y CORS para pruebas
    http.csrf().disable();
    http.cors().disable();

    return http.build();
}
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UsuarioRepository usuarioRepository) {
        return username -> {
            UsuarioEntity usuario = usuarioRepository.findByNombreUsuario(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));
            return new org.springframework.security.core.userdetails.User(usuario.getNombreUsuario(), usuario.getContraseña(), List.of(new SimpleGrantedAuthority("ROLE_" + usuario.getRolUsuario().name())));
        };
    }
}
