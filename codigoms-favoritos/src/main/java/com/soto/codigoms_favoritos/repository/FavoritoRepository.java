package com.soto.codigoms_favoritos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soto.codigoms_favoritos.model.Favorito;

public interface FavoritoRepository extends JpaRepository<Favorito, Long>{

    Optional<Favorito> findByusuario(String usuario);

}
