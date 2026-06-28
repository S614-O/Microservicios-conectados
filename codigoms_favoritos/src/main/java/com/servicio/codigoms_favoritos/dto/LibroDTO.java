package com.servicio.codigoms_favoritos.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor 
@NoArgsConstructor
@Builder
public class LibroDTO {
    private Long id;
    private String titulo;
    private String autor;
    private BigDecimal precio;
}

