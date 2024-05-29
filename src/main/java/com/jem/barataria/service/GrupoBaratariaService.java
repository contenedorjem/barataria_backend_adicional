package com.jem.barataria.service;

import com.jem.barataria.dto.GrupoBaratariaDto;
import com.jem.barataria.model.GrupoBaratariaEntity;
import com.jem.barataria.repository.GrupoBaratariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GrupoBaratariaService {

    @Autowired
    private GrupoBaratariaRepository grupoBaratariaRepository;

    public List<GrupoBaratariaDto> findAll() {
        return grupoBaratariaRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public Optional<GrupoBaratariaDto> findById(Long id) {
        return grupoBaratariaRepository.findById(id)
                .map(this::convertToDto);
    }

    public GrupoBaratariaDto saveOrUpdate(GrupoBaratariaDto dto) {
        GrupoBaratariaEntity entity = convertToEntity(dto);
        entity = grupoBaratariaRepository.save(entity);
        return convertToDto(entity);
    }

    public boolean deleteById(Long id) {
        if (grupoBaratariaRepository.existsById(id)) {
            grupoBaratariaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private GrupoBaratariaDto convertToDto(GrupoBaratariaEntity entity) {
        GrupoBaratariaDto dto = new GrupoBaratariaDto();
        dto.setIdEditorial(entity.getIdEditorial());
        dto.setNombreEditorial(entity.getNombreEditorial());
        dto.setTextoEditorial(entity.getTextoEditorial());
        dto.setTextoDescripcion(entity.getTextoDescripcion());
        dto.setTextoAdicional(entity.getTextoAdicional());
        return dto;
    }

    private GrupoBaratariaEntity convertToEntity(GrupoBaratariaDto dto) {
        GrupoBaratariaEntity entity = new GrupoBaratariaEntity();
        entity.setIdEditorial(dto.getIdEditorial());
        entity.setNombreEditorial(dto.getNombreEditorial());
        entity.setTextoEditorial(dto.getTextoEditorial());
        entity.setTextoDescripcion(dto.getTextoDescripcion());
        entity.setTextoAdicional(dto.getTextoAdicional());
        return entity;
    }
}
