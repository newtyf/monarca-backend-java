package com.monarca.backend.repository;

import com.monarca.backend.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByEmail(String email);

    @Query("SELECT c FROM Cliente c WHERE lower(c.nombre) LIKE lower(concat('%',:term,'%'))")
    List<Cliente> searchByNombre(@Param("term") String term);
}
