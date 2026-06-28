package com.soto.codigoms_resenas.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ResenaRequest {
    @NotNull(message = "El libroId es obligatorio")
    private Long libroId;

    @NotBlank(message = "El usuario es obligatorio")
    private String usuario;

    @NotNull(message = "La calificacion es obligatoria")
    @Min(value = 1, message = "La calificacion minima es 1")
    @Max(value = 5, message = "La calificacion maxima es 5")
    private Integer calificacion;

    private String comentario;
}
