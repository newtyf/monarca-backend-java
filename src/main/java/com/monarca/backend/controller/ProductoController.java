package com.monarca.backend.controller;

import com.monarca.backend.dto.ProductoDTO;
import com.monarca.backend.service.ProductoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public List<ProductoDTO> list() {
        return productoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> get(@PathVariable Long id) {
        ProductoDTO dto = productoService.findById(id);
        if (dto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<ProductoDTO> create(@RequestBody ProductoDTO dto) {
        ProductoDTO created = productoService.create(dto);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> update(@PathVariable Long id, @RequestBody ProductoDTO dto) {
        ProductoDTO updated = productoService.update(id, dto);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public List<ProductoDTO> search(@RequestParam("q") String q) {
        return productoService.searchByNombre(q);
    }

    @GetMapping("/estado")
    public List<ProductoDTO> findByEstado(@RequestParam("estado") String estado) {
        return productoService.findByEstado(estado);
    }

    @GetMapping("/bajo-stock")
    public List<ProductoDTO> findBajoStock() {
        return productoService.findBajoStock();
    }

    @GetMapping("/categoria")
    public List<ProductoDTO> findByCategoria(@RequestParam("categoria") String categoria) {
        return productoService.findByCategoria(categoria);
    }
}
