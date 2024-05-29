package com.jem.barataria.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.jem.barataria.model.UsuarioEntity;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    Optional<UsuarioEntity> findByNombreUsuario(String nombreUsuario);
    Optional<UsuarioEntity> findByEmail(String email);

    default Optional<UsuarioEntity> findByNombreUsuarioOrEmail(String identifier) {
        return findByNombreUsuario(identifier).or(() -> findByEmail(identifier));
    }
}