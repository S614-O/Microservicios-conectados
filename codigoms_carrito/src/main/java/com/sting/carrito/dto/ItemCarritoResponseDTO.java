package com.sting.carrito.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ItemCarritoResponseDTO {
    private Long id;
    private Long libroId;
    private String tituloLibro;
    private Integer cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal subtotal;
}
