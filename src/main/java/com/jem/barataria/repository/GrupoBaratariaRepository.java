package com.jem.barataria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jem.barataria.model.GrupoBaratariaEntity;

@Repository
public interface GrupoBaratariaRepository extends JpaRepository<GrupoBaratariaEntity, Long> {

}
