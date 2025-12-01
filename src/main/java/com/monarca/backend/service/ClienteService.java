package com.monarca.backend.service;

import com.monarca.backend.dto.ClienteDTO;

import java.util.List;

public interface ClienteService {

    List<ClienteDTO> findAll();

    ClienteDTO findById(Long id);

    ClienteDTO create(ClienteDTO dto);

    ClienteDTO update(Long id, ClienteDTO dto);

    void delete(Long id);

    List<ClienteDTO> searchByNombre(String term);
}
