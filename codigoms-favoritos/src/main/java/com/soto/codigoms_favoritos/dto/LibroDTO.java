package com.soto.codigoms_favoritos.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class LibroDTO {
    private Long id;
    private String titulo;
    private String autor;
    private BigDecimal precio;
}
