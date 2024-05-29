package com.jem.barataria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jem.barataria.model.DistribucionEntity;

@Repository
public interface DistribucionRepository extends JpaRepository<DistribucionEntity, Long> {

}
