package com.jem.barataria.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.jem.barataria.dto.ColeccionDto;
import com.jem.barataria.service.ColeccionService;

@RestController
@CrossOrigin
@RequestMapping("/api/colecciones")
public class ColeccionController {

    private final ColeccionService coleccionService;

    public ColeccionController(ColeccionService coleccionService) {
        this.coleccionService = coleccionService;
    }

    @GetMapping("/libros/{libroId}/colecciones/detalles")
    public ResponseEntity<ColeccionDto> getColeccionPorLibroId(@PathVariable Long libroId) {
        ColeccionDto coleccionDto = coleccionService.obtenerColeccionPorLibroId(libroId);
        if (coleccionDto != null) {
            return ResponseEntity.ok(coleccionDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping
    public ResponseEntity<List<ColeccionDto>> getAllColecciones() {
        List<ColeccionDto> colecciones = coleccionService.findAll();
        return ResponseEntity.ok(colecciones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ColeccionDto> getColeccionById(@PathVariable Long id) {
        ColeccionDto coleccion = coleccionService.findById(id);
        if (coleccion != null) {
            return ResponseEntity.ok(coleccion);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'EDITOR')")
    public ResponseEntity<ColeccionDto> createColeccion(@RequestBody ColeccionDto coleccion) {
        ColeccionDto savedColeccion = coleccionService.save(coleccion);
        return new ResponseEntity<>(savedColeccion, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'EDITOR')")
    public ResponseEntity<ColeccionDto> updateColeccion(@PathVariable Long id,
            @RequestBody ColeccionDto coleccionDetails) {
        coleccionDetails.setId(id);
        ColeccionDto updatedColeccion = coleccionService.save(coleccionDetails);
        if (updatedColeccion != null) {
            return ResponseEntity.ok(updatedColeccion);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Void> deleteColeccion(@PathVariable Long id) {
        if (coleccionService.deleteById(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/libros/detalles")
    public ResponseEntity<List<ColeccionDto>> obtenerTodosLosLibrosConDetalles() {
        List<ColeccionDto> coleccionDtos = coleccionService.obtenerTodasLasColeccionesConDetalles(); // Corregido aquí
        if (coleccionDtos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(coleccionDtos);
    }

    @GetMapping("/{id}/detalles")
    public ResponseEntity<ColeccionDto> obtenerColeccionConDetallesPorId(@PathVariable Long id) {
        ColeccionDto coleccionDto = coleccionService.obtenerColeccionConDetallesPorId(id);
        if (coleccionDto != null) {
            return ResponseEntity.ok(coleccionDto);
        }
        return ResponseEntity.notFound().build();
    }
}
