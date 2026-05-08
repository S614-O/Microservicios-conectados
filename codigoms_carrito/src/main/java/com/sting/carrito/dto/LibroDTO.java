package com.sting.carrito.dto;

import java.math.BigDecimal;

import lombok.Data;

/**
 * LibroDTO
 *
 * Autor: Prof. Sting Parra Silva
 *
 * Espejo local de LibroResponseDTO de codigoms_catalogo.
 * Los campos deben coincidir con lo que devuelve GET /api/libros/{id}.
 * Este DTO no se persiste — solo se usa para leer datos del otro servicio.
 */
@Data
public class LibroDTO {
    private Long id;
    private String titulo;
    private String isbn;
    private BigDecimal precio;
    private String categoriaNombre;
}
