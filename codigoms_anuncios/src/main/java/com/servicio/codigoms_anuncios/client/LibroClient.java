package com.servicio.codigoms_anuncios.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.servicio.codigoms_anuncios.dto.LibroDTO;
@FeignClient(name = "codigoms-catalogo", url = "${catalogo.service.url}")

public interface LibroClient {

    @GetMapping("/api/libros/{id}")
    LibroDTO obtenerLibro(@PathVariable Long id);

}
