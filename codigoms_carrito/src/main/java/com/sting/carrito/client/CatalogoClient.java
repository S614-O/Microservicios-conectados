package com.sting.carrito.client;

import com.sting.carrito.dto.LibroDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * CatalogoClient
 *
 * Autor: Prof. Sting Parra Silva
 *
 * Esta interfaz representa la conexion HTTP hacia codigoms_catalogo.
 * Cuando CarritoService llama obtenerLibro(id), FeignClient genera:
 *   GET http://localhost:8082/api/libros/{id}
 *
 * Si codigoms_catalogo no esta corriendo → FeignException (conexion rechazada).
 * Si el libro no existe → FeignException.NotFound (404).
 */
@FeignClient(name = "codigoms-catalogo", url = "${catalogo.service.url}")
public interface CatalogoClient {

    @GetMapping("/api/libros/{id}")
    LibroDTO obtenerLibro(@PathVariable Long id);
}
