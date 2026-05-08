package com.sting.catalogo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * LibroResponseDTO
 *
 * Autor: Prof. Sting Parra Silva
 *
 * Este DTO es lo que los otros microservicios reciben cuando consultan
 * GET /api/libros/{id}. Sus campos deben coincidir con LibroDTO en
 * codigoms_carrito y codigoms_pedidos.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LibroResponseDTO {
    private Long id;
    private String titulo;
    private String isbn;
    private BigDecimal precio;
    private String categoriaNombre;
}
