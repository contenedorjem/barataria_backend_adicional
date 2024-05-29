package com.jem.barataria.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jem.barataria.model.AutorEntity;

@Repository
public interface AutorRepository extends JpaRepository<AutorEntity, Long> {
}
