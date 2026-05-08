package com.sting.carrito.repository;

import com.sting.carrito.model.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CarritoRepository extends JpaRepository<Carrito, Long> {
    List<Carrito> findByUsuario(String usuario);
}
