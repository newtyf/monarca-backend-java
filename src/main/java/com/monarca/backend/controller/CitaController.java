package com.monarca.backend.controller;

import com.monarca.backend.dto.CitaDTO;
import com.monarca.backend.service.CitaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/citas")
public class CitaController {

    private final CitaService citaService;

    public CitaController(CitaService citaService) {
        this.citaService = citaService;
    }

    @GetMapping
    public List<CitaDTO> list() {
        return citaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CitaDTO> get(@PathVariable Long id) {
        CitaDTO dto = citaService.findById(id);
        if (dto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<CitaDTO> create(@RequestBody CitaDTO dto) {
        CitaDTO created = citaService.create(dto);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CitaDTO> update(@PathVariable Long id, @RequestBody CitaDTO dto) {
        CitaDTO updated = citaService.update(id, dto);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        citaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cliente/{clienteId}")
    public List<CitaDTO> findByCliente(@PathVariable Long clienteId) {
        return citaService.findByClienteId(clienteId);
    }

    @GetMapping("/cliente-email")
    public List<CitaDTO> findByClienteEmail(@RequestParam("email") String email) {
        return citaService.findByClienteEmail(email);
    }

    @GetMapping("/estado")
    public List<CitaDTO> findByEstado(@RequestParam("estado") String estado) {
        return citaService.findByEstado(estado);
    }
}
