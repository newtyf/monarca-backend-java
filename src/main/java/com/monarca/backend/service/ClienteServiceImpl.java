package com.monarca.backend.service;

import com.monarca.backend.dto.ClienteDTO;
import com.monarca.backend.model.Cliente;
import com.monarca.backend.repository.ClienteRepository;
import com.monarca.backend.service.ClienteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<ClienteDTO> findAll() {
        return clienteRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public ClienteDTO findById(Long id) {
        return clienteRepository.findById(id).map(this::toDto).orElse(null);
    }

    @Override
    public ClienteDTO create(ClienteDTO dto) {
        Cliente c = toEntity(dto);
        Cliente saved = clienteRepository.save(c);
        return toDto(saved);
    }

    @Override
    public ClienteDTO update(Long id, ClienteDTO dto) {
        Optional<Cliente> opt = clienteRepository.findById(id);
        if (opt.isEmpty()) return null;
        Cliente c = opt.get();
        c.setNombre(dto.getNombre());
        c.setEmail(dto.getEmail());
        c.setTelefono(dto.getTelefono());
        c.setFechaNacimiento(dto.getFechaNacimiento());
        c.setGenero(dto.getGenero());
        c.setDireccion(dto.getDireccion());
        c.setNotas(dto.getNotas());
        Cliente updated = clienteRepository.save(c);
        return toDto(updated);
    }

    @Override
    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }

    @Override
    public List<ClienteDTO> searchByNombre(String term) {
        return clienteRepository.searchByNombre(term).stream().map(this::toDto).collect(Collectors.toList());
    }

    private ClienteDTO toDto(Cliente c) {
        ClienteDTO d = new ClienteDTO();
        d.setIdCliente(c.getIdCliente());
        d.setNombre(c.getNombre());
        d.setEmail(c.getEmail());
        d.setTelefono(c.getTelefono());
        d.setFechaNacimiento(c.getFechaNacimiento());
        d.setGenero(c.getGenero());
        d.setDireccion(c.getDireccion());
        d.setNotas(c.getNotas());
        return d;
    }

    private Cliente toEntity(ClienteDTO d) {
        Cliente c = new Cliente();
        c.setNombre(d.getNombre());
        c.setEmail(d.getEmail());
        c.setTelefono(d.getTelefono());
        c.setFechaNacimiento(d.getFechaNacimiento());
        c.setGenero(d.getGenero());
        c.setDireccion(d.getDireccion());
        c.setNotas(d.getNotas());
        return c;
    }
}
