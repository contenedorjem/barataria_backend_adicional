package com.jem.barataria.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.jem.barataria.model.LibroEntity;

@Repository
public interface LibroRepository extends JpaRepository<LibroEntity, Long> {
}
