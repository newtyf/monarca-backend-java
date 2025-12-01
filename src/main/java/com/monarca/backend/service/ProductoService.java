package com.monarca.backend.service;

import com.monarca.backend.dto.ProductoDTO;

import java.util.List;

public interface ProductoService {

    List<ProductoDTO> findAll();

    ProductoDTO findById(Long id);

    ProductoDTO create(ProductoDTO dto);

    ProductoDTO update(Long id, ProductoDTO dto);

    void delete(Long id);

    List<ProductoDTO> searchByNombre(String term);

    List<ProductoDTO> findByEstado(String estado);

    List<ProductoDTO> findBajoStock();

    List<ProductoDTO> findByCategoria(String categoria);
}
