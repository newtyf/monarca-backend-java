package com.monarca.backend.repository;

import com.monarca.backend.model.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServicioRepository extends JpaRepository<Servicio, Long> {

    @Query("SELECT s FROM Servicio s WHERE lower(s.nombre) LIKE lower(concat('%',:term,'%'))")
    List<Servicio> searchByNombre(@Param("term") String term);

    @Query("SELECT s FROM Servicio s WHERE s.estado = :estado")
    List<Servicio> findByEstado(@Param("estado") String estado);

    @Query("SELECT s FROM Servicio s WHERE s.idCategoria = :categoriaId")
    List<Servicio> findByCategoria(@Param("categoriaId") Integer categoriaId);
}
