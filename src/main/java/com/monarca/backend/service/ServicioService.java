package com.monarca.backend.service;

import com.monarca.backend.dto.ServicioDTO;

import java.util.List;

public interface ServicioService {

    List<ServicioDTO> findAll();

    ServicioDTO findById(Long id);

    ServicioDTO create(ServicioDTO dto);

    ServicioDTO update(Long id, ServicioDTO dto);

    void delete(Long id);

    List<ServicioDTO> searchByNombre(String term);

    List<ServicioDTO> findByEstado(String estado);

    List<ServicioDTO> findByCategoria(Integer categoriaId);
}
