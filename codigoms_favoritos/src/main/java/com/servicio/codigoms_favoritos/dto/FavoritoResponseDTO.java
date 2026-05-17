package com.servicio.codigoms_favoritos.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class FavoritoResponseDTO {
    private Long id;
    private Long libroId;
    private String tituloLibro;
    private String usuario;
    private LocalDateTime fechaAgregado;
}
