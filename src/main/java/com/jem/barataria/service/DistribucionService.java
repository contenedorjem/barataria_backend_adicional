package com.jem.barataria.service;

import com.jem.barataria.dto.DistribucionDto;
import com.jem.barataria.model.DistribucionEntity;
import com.jem.barataria.model.GrupoBaratariaEntity;
import com.jem.barataria.repository.DistribucionRepository;
import com.jem.barataria.repository.GrupoBaratariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DistribucionService {

    @Autowired
    private DistribucionRepository distribucionRepository;
    
    @Autowired
    private GrupoBaratariaRepository grupoBaratariaRepository;

    public List<DistribucionDto> findAll() {
        return distribucionRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public Optional<DistribucionDto> findById(Long id) {
        return distribucionRepository.findById(id)
                .map(this::convertToDto);
    }

    public DistribucionDto saveOrUpdate(DistribucionDto dto) {
        DistribucionEntity distribucionEntity = convertToEntity(dto);
        distribucionEntity = distribucionRepository.save(distribucionEntity);
        return convertToDto(distribucionEntity);
    }

    public boolean deleteById(Long id) {
        if (distribucionRepository.existsById(id)) {
            distribucionRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private DistribucionDto convertToDto(DistribucionEntity entity) {
        DistribucionDto dto = new DistribucionDto();
        dto.setIdDistribuidor(entity.getIdDistribuidor());
        dto.setIdEditorial(entity.getGrupoBarataria().getIdEditorial());
        dto.setNombre(entity.getNombre());
        dto.setTelefono1(entity.getTelefono1());
        dto.setTelefono2(entity.getTelefono2());
        dto.setEmail1(entity.getEmail1());
        dto.setEmail2(entity.getEmail2());
        dto.setPais(entity.getPais());
        dto.setRegion(entity.getRegion());
        dto.setDireccion(entity.getDireccion());
        dto.setWeb(entity.getWeb());
        dto.setDistribuidorEspania(entity.getDistribuidorEspania());
        dto.setDistribuidorActivo(entity.getDistribuidorActivo());
        return dto;
    }

    private DistribucionEntity convertToEntity(DistribucionDto dto) {
        DistribucionEntity entity = new DistribucionEntity();
        if (dto.getIdDistribuidor() != null) {
            Optional<DistribucionEntity> existingEntity = distribucionRepository.findById(dto.getIdDistribuidor());
            if (existingEntity.isPresent()) {
                entity = existingEntity.get();
            }
        }
        GrupoBaratariaEntity grupoBaratariaEntity = grupoBaratariaRepository.findById(dto.getIdEditorial())
                .orElseThrow(() -> new IllegalArgumentException("Editorial no encontrada con el id: " + dto.getIdEditorial()));
        entity.setGrupoBarataria(grupoBaratariaEntity);
        entity.setNombre(dto.getNombre());
        entity.setTelefono1(dto.getTelefono1());
        entity.setTelefono2(dto.getTelefono2());
        entity.setEmail1(dto.getEmail1());
        entity.setEmail2(dto.getEmail2());
        entity.setPais(dto.getPais());
        entity.setRegion(dto.getRegion());
        entity.setDireccion(dto.getDireccion());
        entity.setWeb(dto.getWeb());
        entity.setDistribuidorEspania(dto.getDistribuidorEspania());
        entity.setDistribuidorActivo(dto.getDistribuidorActivo());
        return entity;
    }
}
