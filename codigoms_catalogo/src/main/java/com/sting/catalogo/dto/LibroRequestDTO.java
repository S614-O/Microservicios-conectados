package com.sting.catalogo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class LibroRequestDTO {

    @NotBlank(message = "El titulo no puede estar vacio")
    private String titulo;

    @NotBlank(message = "El ISBN no puede estar vacio")
    private String isbn;

    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser mayor a 0")
    private BigDecimal precio;

    @NotNull(message = "El categoriaId es obligatorio")
    private Long categoriaId;
}
