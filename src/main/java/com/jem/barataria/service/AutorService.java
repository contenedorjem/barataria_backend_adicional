package com.jem.barataria.service;

import com.jem.barataria.dto.AutorDto;
import com.jem.barataria.model.AutorEntity;
import com.jem.barataria.model.LibroEntity;
import com.jem.barataria.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    public List<AutorDto> findAll() {
        return autorRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public AutorDto findById(Long id) {
        AutorEntity autor = autorRepository.findById(id).orElseThrow(() -> new RuntimeException("Autor no encontrado"));
        return convertToDto(autor);
    }

    public AutorDto save(AutorDto autorDto) {
        AutorEntity autorEntity = convertToEntity(autorDto);
        autorEntity = autorRepository.save(autorEntity);
        return convertToDto(autorEntity);
    }

    public boolean deleteById(Long id) {
        if (autorRepository.existsById(id)) {
            autorRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private AutorDto convertToDto(AutorEntity autorEntity) {
        AutorDto autorDto = new AutorDto();
        autorDto.setIdAutor(autorEntity.getIdAutor());
        autorDto.setNombre(autorEntity.getNombre());
        autorDto.setApellido1(autorEntity.getApellido1());
        autorDto.setApellido2(autorEntity.getApellido2());
        autorDto.setTextoAutor(autorEntity.getTextoAutor());
        autorDto.setLibrosIds(autorEntity.getLibros() != null ? autorEntity.getLibros().stream().map(LibroEntity::getIdLibro).collect(Collectors.toSet()) : new HashSet<>());
        return autorDto;
    }

    private AutorEntity convertToEntity(AutorDto autorDto) {
        AutorEntity autorEntity = new AutorEntity();
        autorEntity.setIdAutor(autorDto.getIdAutor());
        autorEntity.setNombre(autorDto.getNombre());
        autorEntity.setApellido1(autorDto.getApellido1());
        autorEntity.setApellido2(autorDto.getApellido2());
        autorEntity.setTextoAutor(autorDto.getTextoAutor());
        autorEntity.setLibros(new HashSet<>()); // Inicializar la colección si es necesario
        return autorEntity;
    }
}
