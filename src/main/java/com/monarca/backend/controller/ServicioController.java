package com.monarca.backend.controller;

import com.monarca.backend.dto.ServicioDTO;
import com.monarca.backend.service.ServicioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicios")
public class ServicioController {

    private final ServicioService servicioService;

    public ServicioController(ServicioService servicioService) {
        this.servicioService = servicioService;
    }

    @GetMapping
    public List<ServicioDTO> list() {
        return servicioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicioDTO> get(@PathVariable Long id) {
        ServicioDTO dto = servicioService.findById(id);
        if (dto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<ServicioDTO> create(@RequestBody ServicioDTO dto) {
        ServicioDTO created = servicioService.create(dto);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicioDTO> update(@PathVariable Long id, @RequestBody ServicioDTO dto) {
        ServicioDTO updated = servicioService.update(id, dto);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        servicioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public List<ServicioDTO> search(@RequestParam("q") String q) {
        return servicioService.searchByNombre(q);
    }

    @GetMapping("/estado")
    public List<ServicioDTO> findByEstado(@RequestParam("estado") String estado) {
        return servicioService.findByEstado(estado);
    }

    @GetMapping("/categoria/{categoriaId}")
    public List<ServicioDTO> findByCategoria(@PathVariable Integer categoriaId) {
        return servicioService.findByCategoria(categoriaId);
    }
}
