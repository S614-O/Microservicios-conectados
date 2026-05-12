package com.sting.catalogo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


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
