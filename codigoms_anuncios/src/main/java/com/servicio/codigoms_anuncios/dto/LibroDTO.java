package com.servicio.codigoms_anuncios.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class LibroDTO {
    private Long id;
    private String titulo;
    private BigDecimal precio;
}
