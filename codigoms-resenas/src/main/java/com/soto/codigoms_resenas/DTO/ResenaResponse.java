package com.soto.codigoms_resenas.DTO;
import java.time.LocalDateTime;

import lombok.Data;
@Data
public class ResenaResponse {
    private Long id;
    private Long libroId;
    private String tituloLibro;
    private String usuario;
    private Integer calificacion;
    private String comentario;
    private LocalDateTime fechaResena;
}