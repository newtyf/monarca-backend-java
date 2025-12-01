package com.monarca.backend.repository;

import com.monarca.backend.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    @Query("SELECT p FROM Producto p WHERE lower(p.nombre) LIKE lower(concat('%',:term,'%'))")
    List<Producto> searchByNombre(@Param("term") String term);

    @Query("SELECT p FROM Producto p WHERE p.estado = :estado")
    List<Producto> findByEstado(@Param("estado") String estado);

    @Query("SELECT p FROM Producto p WHERE p.stock <= p.stockMinimo")
    List<Producto> findBajoStock();

    @Query("SELECT p FROM Producto p WHERE p.categoria = :categoria")
    List<Producto> findByCategoria(@Param("categoria") String categoria);
}
