package com.monarca.backend.service;

import com.monarca.backend.dto.ServicioDTO;
import com.monarca.backend.model.Servicio;
import com.monarca.backend.repository.ServicioRepository;
import com.monarca.backend.service.ServicioService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ServicioServiceImpl implements ServicioService {

    private final ServicioRepository servicioRepository;

    public ServicioServiceImpl(ServicioRepository servicioRepository) {
        this.servicioRepository = servicioRepository;
    }

    @Override
    public List<ServicioDTO> findAll() {
        return servicioRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public ServicioDTO findById(Long id) {
        return servicioRepository.findById(id).map(this::toDto).orElse(null);
    }

    @Override
    public ServicioDTO create(ServicioDTO dto) {
        Servicio s = toEntity(dto);
        Servicio saved = servicioRepository.save(s);
        return toDto(saved);
    }

    @Override
    public ServicioDTO update(Long id, ServicioDTO dto) {
        Optional<Servicio> opt = servicioRepository.findById(id);
        if (opt.isEmpty()) return null;
        Servicio s = opt.get();
        s.setIdCategoria(dto.getIdCategoria());
        s.setNombre(dto.getNombre());
        s.setDescripcion(dto.getDescripcion());
        s.setDuracionMin(dto.getDuracionMin());
        s.setPrecio(dto.getPrecio());
        s.setEstado(dto.getEstado());
        Servicio updated = servicioRepository.save(s);
        return toDto(updated);
    }

    @Override
    public void delete(Long id) {
        servicioRepository.deleteById(id);
    }

    @Override
    public List<ServicioDTO> searchByNombre(String term) {
        return servicioRepository.searchByNombre(term).stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<ServicioDTO> findByEstado(String estado) {
        return servicioRepository.findByEstado(estado).stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<ServicioDTO> findByCategoria(Integer categoriaId) {
        return servicioRepository.findByCategoria(categoriaId).stream().map(this::toDto).collect(Collectors.toList());
    }

    private ServicioDTO toDto(Servicio s) {
        ServicioDTO d = new ServicioDTO();
        d.setIdServicio(s.getIdServicio());
        d.setIdCategoria(s.getIdCategoria());
        d.setNombre(s.getNombre());
        d.setDescripcion(s.getDescripcion());
        d.setDuracionMin(s.getDuracionMin());
        d.setPrecio(s.getPrecio());
        d.setEstado(s.getEstado());
        return d;
    }

    private Servicio toEntity(ServicioDTO d) {
        Servicio s = new Servicio();
        s.setIdCategoria(d.getIdCategoria());
        s.setNombre(d.getNombre());
        s.setDescripcion(d.getDescripcion());
        s.setDuracionMin(d.getDuracionMin());
        s.setPrecio(d.getPrecio());
        s.setEstado(d.getEstado());
        return s;
    }
}
