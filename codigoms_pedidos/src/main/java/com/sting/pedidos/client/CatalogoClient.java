package com.sting.pedidos.client;

import com.sting.pedidos.dto.LibroDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * CatalogoClient
 *
 * Autor: Prof. Sting Parra Silva
 *
 * Mismo patron que en codigoms_carrito.
 * Tanto carrito como pedidos necesitan consultar libros →
 * ambos tienen su propio CatalogoClient apuntando al mismo servicio.
 * Cada microservicio es independiente y no comparte clientes con otros.
 */
@FeignClient(name = "codigoms-catalogo", url = "${catalogo.service.url}")
public interface CatalogoClient {

    @GetMapping("/api/libros/{id}")
    LibroDTO obtenerLibro(@PathVariable Long id);
}
