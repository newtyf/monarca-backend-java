package com.monarca.backend.service;

import com.monarca.backend.dto.CitaDTO;
import com.monarca.backend.model.Cita;
import com.monarca.backend.model.Cliente;
import com.monarca.backend.repository.CitaRepository;
import com.monarca.backend.repository.ClienteRepository;
import com.monarca.backend.service.CitaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CitaServiceImpl implements CitaService {

    private final CitaRepository citaRepository;
    private final ClienteRepository clienteRepository;

    public CitaServiceImpl(CitaRepository citaRepository, ClienteRepository clienteRepository) {
        this.citaRepository = citaRepository;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<CitaDTO> findAll() {
        return citaRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public CitaDTO findById(Long id) {
        return citaRepository.findById(id).map(this::toDto).orElse(null);
    }

    @Override
    public CitaDTO create(CitaDTO dto) {
        Cita c = new Cita();
        if (dto.getIdCliente() != null) {
            Cliente cliente = clienteRepository.findById(dto.getIdCliente()).orElse(null);
            c.setCliente(cliente);
        }
        c.setIdServicio(dto.getIdServicio());
        c.setIdUser(dto.getIdUser());
        c.setFechaHora(dto.getFechaHora());
        c.setDuracionEstimada(dto.getDuracionEstimada());
        c.setEstado(dto.getEstado());
        c.setNotas(dto.getNotas());
        Cita saved = citaRepository.save(c);
        return toDto(saved);
    }

    @Override
    public CitaDTO update(Long id, CitaDTO dto) {
        Optional<Cita> opt = citaRepository.findById(id);
        if (opt.isEmpty()) return null;
        Cita c = opt.get();
        if (dto.getIdCliente() != null) {
            Cliente cliente = clienteRepository.findById(dto.getIdCliente()).orElse(null);
            c.setCliente(cliente);
        }
        c.setIdServicio(dto.getIdServicio());
        c.setIdUser(dto.getIdUser());
        c.setFechaHora(dto.getFechaHora());
        c.setDuracionEstimada(dto.getDuracionEstimada());
        c.setEstado(dto.getEstado());
        c.setNotas(dto.getNotas());
        Cita updated = citaRepository.save(c);
        return toDto(updated);
    }

    @Override
    public void delete(Long id) {
        citaRepository.deleteById(id);
    }

    @Override
    public List<CitaDTO> findByClienteId(Long clienteId) {
        return citaRepository.findByClienteId(clienteId).stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<CitaDTO> findByClienteEmail(String email) {
        return citaRepository.findByClienteEmail(email).stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<CitaDTO> findByEstado(String estado) {
        return citaRepository.findByEstado(estado).stream().map(this::toDto).collect(Collectors.toList());
    }

    private CitaDTO toDto(Cita c) {
        CitaDTO d = new CitaDTO();
        d.setIdCita(c.getIdCita());
        if (c.getCliente() != null) d.setIdCliente(c.getCliente().getIdCliente());
        d.setIdServicio(c.getIdServicio());
        d.setIdUser(c.getIdUser());
        d.setFechaHora(c.getFechaHora());
        d.setDuracionEstimada(c.getDuracionEstimada());
        d.setEstado(c.getEstado());
        d.setNotas(c.getNotas());
        return d;
    }
}
