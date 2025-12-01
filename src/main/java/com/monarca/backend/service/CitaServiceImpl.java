package com.monarca.backend.service;

import com.monarca.backend.dto.CitaDTO;
import com.monarca.backend.dto.CitaConClienteDTO;
import com.monarca.backend.dto.ClienteDTO;
import com.monarca.backend.model.Cita;
import com.monarca.backend.model.Cliente;
import com.monarca.backend.model.Servicio;
import com.monarca.backend.repository.CitaRepository;
import com.monarca.backend.repository.ClienteRepository;
import com.monarca.backend.repository.ServicioRepository;
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
    private final ServicioRepository servicioRepository;

    public CitaServiceImpl(CitaRepository citaRepository, ClienteRepository clienteRepository, ServicioRepository servicioRepository) {
        this.citaRepository = citaRepository;
        this.clienteRepository = clienteRepository;
        this.servicioRepository = servicioRepository;
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
        // Validar que el cliente exista
        if (dto.getIdCliente() == null) {
            throw new IllegalArgumentException("El ID del cliente es requerido");
        }
        Cliente cliente = clienteRepository.findById(dto.getIdCliente())
                .orElseThrow(() -> new IllegalArgumentException("El cliente con ID " + dto.getIdCliente() + " no existe"));
        
        // Validar que el servicio exista
        if (dto.getIdServicio() == null) {
            throw new IllegalArgumentException("El ID del servicio es requerido");
        }
        servicioRepository.findById(dto.getIdServicio().longValue())
                .orElseThrow(() -> new IllegalArgumentException("El servicio con ID " + dto.getIdServicio() + " no existe"));
        
        // Validar fecha y hora
        if (dto.getFechaHora() == null) {
            throw new IllegalArgumentException("La fecha y hora son requeridas");
        }
        
        Cita c = new Cita();
        c.setCliente(cliente);
        c.setIdServicio(dto.getIdServicio());
        c.setIdUser(dto.getIdUser());
        c.setFechaHora(dto.getFechaHora());
        c.setDuracionEstimada(dto.getDuracionEstimada());
        c.setEstado(dto.getEstado() != null && !dto.getEstado().isEmpty() ? dto.getEstado() : "pendiente");
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
    
    @Override
    public List<CitaConClienteDTO> findAllConCliente() {
        return citaRepository.findAll().stream()
                .map(this::toDtoConCliente)
                .collect(Collectors.toList());
    }
    
    @Override
    public CitaConClienteDTO findByIdConCliente(Long id) {
        return citaRepository.findById(id)
                .map(this::toDtoConCliente)
                .orElse(null);
    }
    
    private CitaConClienteDTO toDtoConCliente(Cita c) {
        CitaConClienteDTO d = new CitaConClienteDTO();
        d.setIdCita(c.getIdCita());
        
        // Mapear cliente completo
        if (c.getCliente() != null) {
            ClienteDTO clienteDTO = new ClienteDTO();
            clienteDTO.setIdCliente(c.getCliente().getIdCliente());
            clienteDTO.setNombre(c.getCliente().getNombre());
            clienteDTO.setEmail(c.getCliente().getEmail());
            clienteDTO.setTelefono(c.getCliente().getTelefono());
            clienteDTO.setFechaNacimiento(c.getCliente().getFechaNacimiento());
            clienteDTO.setGenero(c.getCliente().getGenero());
            clienteDTO.setDireccion(c.getCliente().getDireccion());
            clienteDTO.setNotas(c.getCliente().getNotas());
            d.setCliente(clienteDTO);
        }
        
        d.setIdServicio(c.getIdServicio());
        d.setIdUser(c.getIdUser());
        d.setFechaHora(c.getFechaHora());
        d.setDuracionEstimada(c.getDuracionEstimada());
        d.setEstado(c.getEstado());
        d.setNotas(c.getNotas());
        return d;
    }
}
