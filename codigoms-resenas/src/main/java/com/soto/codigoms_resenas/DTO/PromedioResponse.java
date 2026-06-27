package com.soto.codigoms_resenas.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class PromedioResponse {
    private Long libroId;
    private String tituloLibro;
    private Double promedio;
    private Long totalResenas;
}