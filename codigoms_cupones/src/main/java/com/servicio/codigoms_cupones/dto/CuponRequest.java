package com.servicio.codigoms_cupones.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CuponRequest {
    @NotBlank(message = "El codigo no puede estar vacio")
    private String codigo;

    @NotNull(message = "El descuento es obligatorio")
    @Min(value = 1, message = "El descuento minimo es 1%")
    @Max(value = 100, message = "El descuento maximo es 100%")
    private Double descuento;

    @NotNull(message = "La fecha de expiracion es obligatoria")
    private LocalDate fechaExpiracion;
}
