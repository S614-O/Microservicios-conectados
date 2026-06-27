package com.servicio.codigoms_anuncios.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnuncioRequest {

    @NotNull(message = "El ID del libro es obligatorio")
    private Long libroId;


}
