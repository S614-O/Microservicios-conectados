package com.soto.codigoms_favoritos.dto;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class FavoritoRequest {
    private String libroId;
    private String tituloLibro;
    private String usuario;
    private LocalDateTime fechaAgregado;
}
