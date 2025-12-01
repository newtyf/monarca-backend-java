package com.monarca.backend.service;

import com.monarca.backend.dto.ProductoDTO;
import com.monarca.backend.model.Producto;
import com.monarca.backend.repository.ProductoRepository;
import com.monarca.backend.service.ProductoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<ProductoDTO> findAll() {
        return productoRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public ProductoDTO findById(Long id) {
        return productoRepository.findById(id).map(this::toDto).orElse(null);
    }

    @Override
    public ProductoDTO create(ProductoDTO dto) {
        Producto p = toEntity(dto);
        Producto saved = productoRepository.save(p);
        return toDto(saved);
    }

    @Override
    public ProductoDTO update(Long id, ProductoDTO dto) {
        Optional<Producto> opt = productoRepository.findById(id);
        if (opt.isEmpty()) return null;
        Producto p = opt.get();
        p.setNombre(dto.getNombre());
        p.setDescripcion(dto.getDescripcion());
        p.setMarca(dto.getMarca());
        p.setCategoria(dto.getCategoria());
        p.setStock(dto.getStock());
        p.setStockMinimo(dto.getStockMinimo());
        p.setPrecio(dto.getPrecio());
        p.setProveedor(dto.getProveedor());
        p.setEstado(dto.getEstado());
        Producto updated = productoRepository.save(p);
        return toDto(updated);
    }

    @Override
    public void delete(Long id) {
        productoRepository.deleteById(id);
    }

    @Override
    public List<ProductoDTO> searchByNombre(String term) {
        return productoRepository.searchByNombre(term).stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<ProductoDTO> findByEstado(String estado) {
        return productoRepository.findByEstado(estado).stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<ProductoDTO> findBajoStock() {
        return productoRepository.findBajoStock().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<ProductoDTO> findByCategoria(String categoria) {
        return productoRepository.findByCategoria(categoria).stream().map(this::toDto).collect(Collectors.toList());
    }

    private ProductoDTO toDto(Producto p) {
        ProductoDTO d = new ProductoDTO();
        d.setIdProducto(p.getIdProducto());
        d.setNombre(p.getNombre());
        d.setDescripcion(p.getDescripcion());
        d.setMarca(p.getMarca());
        d.setCategoria(p.getCategoria());
        d.setStock(p.getStock());
        d.setStockMinimo(p.getStockMinimo());
        d.setPrecio(p.getPrecio());
        d.setProveedor(p.getProveedor());
        d.setEstado(p.getEstado());
        return d;
    }

    private Producto toEntity(ProductoDTO d) {
        Producto p = new Producto();
        p.setNombre(d.getNombre());
        p.setDescripcion(d.getDescripcion());
        p.setMarca(d.getMarca());
        p.setCategoria(d.getCategoria());
        p.setStock(d.getStock());
        p.setStockMinimo(d.getStockMinimo());
        p.setPrecio(d.getPrecio());
        p.setProveedor(d.getProveedor());
        p.setEstado(d.getEstado());
        return p;
    }
}
