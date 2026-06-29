package com.servicio.codigoms_anuncios.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnuncioResponse {
    private Long id;
    private Long libroId;
    private String tituloLibro;
    private BigDecimal precioLibro;
    private boolean activo;
    private String textoAnuncio;
}
