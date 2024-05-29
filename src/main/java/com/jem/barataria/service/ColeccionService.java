package com.jem.barataria.service;

import com.jem.barataria.dto.AutorDto;
import com.jem.barataria.dto.ColeccionDto;
import com.jem.barataria.dto.LibroDto;
import com.jem.barataria.model.ColeccionEntity;
import com.jem.barataria.repository.ColeccionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ColeccionService {

    private final ColeccionRepository coleccionRepository;

    public ColeccionService(ColeccionRepository coleccionRepository) {
        this.coleccionRepository = coleccionRepository;
    }

    public List<ColeccionDto> obtenerTodasLasColeccionesConDetalles() {
        List<ColeccionEntity> colecciones = coleccionRepository.findAllWithLibrosAndAutores();
        return colecciones.stream().map(this::convertirAEntidadDto).collect(Collectors.toList());
    }
    
    private ColeccionDto convertirAEntidadDto(ColeccionEntity coleccion) {
        List<LibroDto> librosDto = coleccion.getLibros().stream().map(libro -> {
            AutorDto autorDto = new AutorDto(
                libro.getAutor().getIdAutor(),
                libro.getAutor().getNombre(),
                libro.getAutor().getApellido1(),
                libro.getAutor().getApellido2(),
                libro.getAutor().getTextoAutor()
            );
            return new LibroDto(
                libro.getIdLibro(),
                libro.getTitulo(),
                libro.getSubtitulo(),
                libro.getIsbn(),
                libro.getTextoLibro(),
                libro.getPrecio(),
                libro.getPaginas(),
                libro.getFormato(),
                libro.getAnioPublicacion(),
                libro.getTraductor(),
                libro.getDestacado(),
                libro.getLibroActivo(),
                libro.getColeccion().getIdColeccion(),
                libro.getAutor().getIdAutor(),
                autorDto
            );
        }).collect(Collectors.toList());
        return new ColeccionDto(
            coleccion.getIdColeccion(),
            coleccion.getNombreColeccion(),
            coleccion.getTextoColeccion(),
            coleccion.getColeccionDescripcion(),
            coleccion.getColeccionAdicional(),
            coleccion.getColeccionActiva(),
            librosDto
        );
    }
    public ColeccionDto obtenerColeccionPorLibroId(Long libroId) {
        return coleccionRepository.findByLibroIdWithLibrosAndAutores(libroId)
                .map(this::convertirAEntidadDto)
                .orElse(null);
    }
    
    public ColeccionDto obtenerColeccionConDetallesPorId(Long id) {
        return coleccionRepository.findByIdWithLibrosAndAutores(id)
                .map(this::convertirAEntidadDto)
                .orElse(null);
    }

    public List<ColeccionDto> findAll() {
        return coleccionRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public ColeccionDto findById(Long id) {
        return coleccionRepository.findById(id)
                .map(this::convertToDto)
                .orElse(null);
    }

    public ColeccionDto save(ColeccionDto coleccionDto) {
        ColeccionEntity coleccion = convertToEntity(coleccionDto);
        coleccion = coleccionRepository.save(coleccion);
        return convertToDto(coleccion);
    }

    public boolean deleteById(Long id) {
        if (coleccionRepository.existsById(id)) {
            coleccionRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private ColeccionDto convertToDto(ColeccionEntity entity) {
        ColeccionDto dto = new ColeccionDto();
        dto.setId(entity.getIdColeccion());
        dto.setNombreColeccion(entity.getNombreColeccion());
        dto.setTextoColeccion(entity.getTextoColeccion());
        dto.setColeccionActiva(entity.getColeccionActiva());
        return dto;
    }

    private ColeccionEntity convertToEntity(ColeccionDto dto) {
        ColeccionEntity entity = new ColeccionEntity();
        entity.setIdColeccion(dto.getId());
        entity.setNombreColeccion(dto.getNombreColeccion());
        entity.setTextoColeccion(dto.getTextoColeccion());
        entity.setColeccionActiva(dto.getColeccionActiva());
        return entity;
    }


}
