package com.jem.barataria.controller;

import com.jem.barataria.dto.DistribucionDto;
import com.jem.barataria.service.DistribucionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/distribuciones")
public class DistribucionController {

    private final DistribucionService distribucionService;

    public DistribucionController(DistribucionService distribucionService) {
        this.distribucionService = distribucionService;
    }

    @GetMapping
    public ResponseEntity<List<DistribucionDto>> getAllDistribuciones() {
        List<DistribucionDto> distribuciones = distribucionService.findAll();
        return ResponseEntity.ok(distribuciones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DistribucionDto> getDistribucionById(@PathVariable Long id) {
        return distribucionService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'EDITOR')")
    public ResponseEntity<DistribucionDto> createDistribucion(@RequestBody DistribucionDto dto) {
        DistribucionDto savedDto = distribucionService.saveOrUpdate(dto);
        return new ResponseEntity<>(savedDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'EDITOR')")
    public ResponseEntity<DistribucionDto> updateDistribucion(@PathVariable Long id, @RequestBody DistribucionDto dto) {
        dto.setIdDistribuidor(id);
        DistribucionDto updatedDto = distribucionService.saveOrUpdate(dto);
        return ResponseEntity.ok(updatedDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Void> deleteDistribucion(@PathVariable Long id) {
        boolean deleted = distribucionService.deleteById(id);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
