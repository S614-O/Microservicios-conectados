package com.servicio.codigoms_cupones.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servicio.codigoms_cupones.model.Cupon;

public interface CuponRepository extends JpaRepository<Cupon, Long>{
    Optional<Cupon> findByCodigo(String codigo);
    
    boolean existsByCodigo(String codigo);

}
