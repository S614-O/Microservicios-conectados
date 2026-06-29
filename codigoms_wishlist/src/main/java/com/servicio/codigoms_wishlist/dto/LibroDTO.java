package com.servicio.codigoms_wishlist.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class LibroDTO {
    private Long id;
    private String titulo;
    private String autor;
    private BigDecimal precio;
}
