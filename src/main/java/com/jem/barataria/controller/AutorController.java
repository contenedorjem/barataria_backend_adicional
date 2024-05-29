package com.jem.barataria.controller;

import com.jem.barataria.dto.AutorDto;
import com.jem.barataria.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/autores")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @GetMapping
    public ResponseEntity<List<AutorDto>> getAllAutores() {
        List<AutorDto> autores = autorService.findAll();
        return ResponseEntity.ok(autores);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AutorDto> getAutorById(@PathVariable Long id) {
        AutorDto autor = autorService.findById(id);
        return ResponseEntity.ok(autor);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AutorDto> createAutor(@RequestBody AutorDto autorDto) {
        AutorDto newAutor = autorService.save(autorDto);
        return new ResponseEntity<>(newAutor, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AutorDto> updateAutor(@PathVariable Long id, @RequestBody AutorDto autorDto) {
        autorDto.setIdAutor(id);
        AutorDto updatedAutor = autorService.save(autorDto);
        return ResponseEntity.ok(updatedAutor);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteAutor(@PathVariable Long id) {
        boolean wasDeleted = autorService.deleteById(id);
        return wasDeleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
