package com.servicio.codigoms_anuncios.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.servicio.codigoms_anuncios.model.Anuncio;

public interface AnuncioRepository extends JpaRepository<Anuncio, Long> {



}
