package com.servicio.codigoms_favoritos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FavoritoRequestDTO {

    @NotNull(message = "El libroId es obligatorio")
    private Long libroId;

    @NotBlank(message = "El usuario no puede estar vacío")
    private String usuario;
}
