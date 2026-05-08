package com.sting.pedidos.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PedidoResponseDTO {
    private Long id;
    private Long libroId;
    private String tituloLibro;
    private BigDecimal precioUnitario;
    private String cliente;
    private Integer cantidad;
    private BigDecimal total;
    private LocalDateTime fechaPedido;
}
