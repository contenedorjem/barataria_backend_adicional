package com.jem.barataria.controller;

import com.jem.barataria.dto.LibroDto;
import com.jem.barataria.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/libros")
public class LibroController {
    @Autowired
    private LibroService libroService;

    @GetMapping
    public ResponseEntity<List<LibroDto>> getAllLibros() {
        List<LibroDto> libros = libroService.findAll();
        return ResponseEntity.ok(libros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LibroDto> getLibroById(@PathVariable Long id) {
        LibroDto libroDto = libroService.findById(id);
        return ResponseEntity.ok(libroDto);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'EDITOR')")
    public ResponseEntity<LibroDto> createLibro(@RequestBody LibroDto libroDto) {
        LibroDto savedLibroDto = libroService.save(libroDto);
        return ResponseEntity.status(201).body(savedLibroDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'EDITOR')")
    public ResponseEntity<LibroDto> updateLibro(@PathVariable Long id, @RequestBody LibroDto libroDto) {
        libroDto.setIdLibro(id);
        LibroDto updatedLibroDto = libroService.save(libroDto);
        return ResponseEntity.ok(updatedLibroDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Void> deleteLibro(@PathVariable Long id) {
        libroService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
