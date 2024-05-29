package com.jem.barataria.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jem.barataria.model.ColeccionEntity;

@Repository
public interface ColeccionRepository extends JpaRepository<ColeccionEntity, Long> {
    @Query("SELECT c FROM ColeccionEntity c JOIN FETCH c.libros l JOIN FETCH l.autor a")
    List<ColeccionEntity> findAllWithLibrosAndAutores();
    
    @Query("SELECT c FROM ColeccionEntity c LEFT JOIN FETCH c.libros l LEFT JOIN FETCH l.autor WHERE c.idColeccion = :id")
    Optional<ColeccionEntity> findByIdWithLibrosAndAutores(@Param("id") Long id);

    @Query("SELECT c FROM ColeccionEntity c JOIN FETCH c.libros l JOIN FETCH l.autor WHERE l.idLibro = :libroId")
    Optional<ColeccionEntity> findByLibroIdWithLibrosAndAutores(@Param("libroId") Long libroId);
}
