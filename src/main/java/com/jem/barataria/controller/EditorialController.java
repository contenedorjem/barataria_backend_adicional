package com.jem.barataria.controller;

import com.jem.barataria.dto.GrupoBaratariaDto;
import com.jem.barataria.service.GrupoBaratariaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/editoriales")
public class EditorialController {

    private final GrupoBaratariaService grupoBaratariaService;

    public EditorialController(GrupoBaratariaService grupoBaratariaService) {
        this.grupoBaratariaService = grupoBaratariaService;
    }

    @GetMapping
    public ResponseEntity<List<GrupoBaratariaDto>> getAllEditoriales() {
        List<GrupoBaratariaDto> editoriales = grupoBaratariaService.findAll();
        return ResponseEntity.ok(editoriales);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GrupoBaratariaDto> getEditorialById(@PathVariable Long id) {
        Optional<GrupoBaratariaDto> dtoOptional = grupoBaratariaService.findById(id);
        return dtoOptional
                .map(dto -> ResponseEntity.ok(dto))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'EDITOR')")
    public ResponseEntity<GrupoBaratariaDto> createEditorial(@RequestBody GrupoBaratariaDto dto) {
        GrupoBaratariaDto savedDto = grupoBaratariaService.saveOrUpdate(dto);
        return new ResponseEntity<>(savedDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'EDITOR')")
    public ResponseEntity<GrupoBaratariaDto> updateEditorial(@PathVariable Long id, @RequestBody GrupoBaratariaDto dto) {
        dto.setIdEditorial(id);
        GrupoBaratariaDto updatedDto = grupoBaratariaService.saveOrUpdate(dto);
        return ResponseEntity.ok(updatedDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Void> deleteEditorial(@PathVariable Long id) {
        boolean deleted = grupoBaratariaService.deleteById(id);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}