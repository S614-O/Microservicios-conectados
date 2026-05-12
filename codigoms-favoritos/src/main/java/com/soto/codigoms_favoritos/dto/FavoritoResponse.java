package com.soto.codigoms_favoritos.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavoritoResponse {
    private String id;
    private String libroId;
    private String tituloLibro;
    private String usuario;
    private LocalDateTime fechaAgregado;

}
