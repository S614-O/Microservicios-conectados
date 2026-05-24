package com.servicio.codigoms_cupones.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CuponResponse {
    private Long id;
    private String codigo;
    private Double descuento;
    private boolean activo;
    private LocalDate fechaExpiracion;
}
