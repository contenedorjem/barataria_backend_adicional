package com.jem.barataria.service;

import com.jem.barataria.dto.LibroDto;
import com.jem.barataria.model.AutorEntity;
import com.jem.barataria.model.ColeccionEntity;
import com.jem.barataria.model.LibroEntity;
import com.jem.barataria.repository.AutorRepository;
import com.jem.barataria.repository.ColeccionRepository;
import com.jem.barataria.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class LibroService {
    @Autowired
    private LibroRepository libroRepository;
    @Autowired
    private ColeccionRepository coleccionRepository;
    @Autowired
    private AutorRepository autorRepository;

    public List<LibroDto> findAll() {
        return libroRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public LibroDto findById(Long id) {
        LibroEntity libro = libroRepository.findById(id).orElseThrow(() -> new RuntimeException("Libro no encontrado"));
        return convertToDto(libro);
    }

    public LibroDto save(LibroDto libroDto) {
        LibroEntity libroEntity = new LibroEntity();
        if (libroDto.getIdLibro() != null) {
            libroEntity = libroRepository.findById(libroDto.getIdLibro()).orElse(libroEntity);
        }
        libroEntity.setTitulo(libroDto.getTitulo());
        libroEntity.setSubtitulo(libroDto.getSubtitulo());
        libroEntity.setIsbn(libroDto.getIsbn());
        libroEntity.setTextoLibro(libroDto.getTextoLibro());
        libroEntity.setPrecio(libroDto.getPrecio());
        libroEntity.setPaginas(libroDto.getPaginas());
        libroEntity.setFormato(libroDto.getFormato());
        libroEntity.setAnioPublicacion(libroDto.getAnioPublicacion());
        libroEntity.setTraductor(libroDto.getTraductor());
        libroEntity.setDestacado(libroDto.getDestacado());
        libroEntity.setLibroActivo(libroDto.getLibroActivo());

        if (libroDto.getIdColeccion() != null) {
            ColeccionEntity coleccion = coleccionRepository.findById(libroDto.getIdColeccion()).orElseThrow(() -> new RuntimeException("Colección no encontrada"));
            libroEntity.setColeccion(coleccion);
        }
        if (libroDto.getIdAutor() != null) {
            AutorEntity autor = autorRepository.findById(libroDto.getIdAutor()).orElseThrow(() -> new RuntimeException("Autor no encontrado"));
            libroEntity.setAutor(autor);
        }
        libroEntity = libroRepository.save(libroEntity);
        return convertToDto(libroEntity);
    }

    public void deleteById(Long id) {
        Optional<LibroEntity> libroEntityOptional = libroRepository.findById(id);
        if (libroEntityOptional.isPresent()) {
            LibroEntity libroEntity = libroEntityOptional.get();
            libroEntity.setColeccion(null);  // Rompe la relación con la colección
            libroEntity.setAutor(null);  // Rompe la relación con el autor
            libroRepository.save(libroEntity);  // Guarda los cambios para actualizar las relaciones

            libroRepository.deleteById(id);  // Ahora intenta eliminar la entidad
        } else {
            throw new RuntimeException("Libro no encontrado");
        }
    }

    private LibroDto convertToDto(LibroEntity libro) {
        LibroDto dto = new LibroDto();
        dto.setIdLibro(libro.getIdLibro());
        dto.setTitulo(libro.getTitulo());
        dto.setSubtitulo(libro.getSubtitulo());
        dto.setIsbn(libro.getIsbn());
        dto.setTextoLibro(libro.getTextoLibro());
        dto.setPrecio(libro.getPrecio());
        dto.setPaginas(libro.getPaginas());
        dto.setFormato(libro.getFormato());
        dto.setAnioPublicacion(libro.getAnioPublicacion());
        dto.setTraductor(libro.getTraductor());
        dto.setDestacado(libro.getDestacado());
        dto.setLibroActivo(libro.getLibroActivo());

        if (libro.getColeccion() != null) {
            dto.setIdColeccion(libro.getColeccion().getIdColeccion());
        }
        if (libro.getAutor() != null) {
            dto.setIdAutor(libro.getAutor().getIdAutor());
        }
        return dto;
    }
}
