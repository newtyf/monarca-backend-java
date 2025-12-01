package com.monarca.backend.repository;

import com.monarca.backend.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CitaRepository extends JpaRepository<Cita, Long> {

    @Query("SELECT c FROM Cita c WHERE c.cliente.idCliente = :clienteId")
    List<Cita> findByClienteId(@Param("clienteId") Long clienteId);

    @Query("SELECT c FROM Cita c WHERE c.cliente.email = :email")
    List<Cita> findByClienteEmail(@Param("email") String email);

    @Query("SELECT c FROM Cita c WHERE c.estado = :estado")
    List<Cita> findByEstado(@Param("estado") String estado);
}
