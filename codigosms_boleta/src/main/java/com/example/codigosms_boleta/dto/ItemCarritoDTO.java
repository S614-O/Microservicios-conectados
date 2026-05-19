package com.example.codigosms_boleta.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemCarritoDTO {
    private Long id;
    private Long libroId;       // Referencia al catálogo
    private String tituloLibro;  // Snapshot del título
    private BigDecimal precioUnitario; // Snapshot del precio
    private Integer cantidad;    // Cantidad seleccionada
}
