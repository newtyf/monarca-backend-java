package com.monarca.backend.service;

import com.monarca.backend.dto.CitaDTO;
import com.monarca.backend.dto.CitaConClienteDTO;

import java.util.List;

public interface CitaService {

    List<CitaDTO> findAll();

    CitaDTO findById(Long id);

    CitaDTO create(CitaDTO dto);

    CitaDTO update(Long id, CitaDTO dto);

    void delete(Long id);

    List<CitaDTO> findByClienteId(Long clienteId);

    List<CitaDTO> findByClienteEmail(String email);

    List<CitaDTO> findByEstado(String estado);
    
    // Métodos con relaciones
    List<CitaConClienteDTO> findAllConCliente();
    
    CitaConClienteDTO findByIdConCliente(Long id);
}
