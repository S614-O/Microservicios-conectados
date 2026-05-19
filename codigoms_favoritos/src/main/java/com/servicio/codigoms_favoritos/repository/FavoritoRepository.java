package com.servicio.codigoms_favoritos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servicio.codigoms_favoritos.model.Favorito;

public interface FavoritoRepository extends JpaRepository<Favorito, Long>{
    List<Favorito> findByUsuario(String usuario);

    List<Favorito> findByLibroId(Long libroId);

    boolean existsByLibroIdAndUsuario(Long libroId, String usuario);
}
