package com.sting.catalogo.repository;

import com.sting.catalogo.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    List<Libro> findByCategoriaId(Long categoriaId);
}
