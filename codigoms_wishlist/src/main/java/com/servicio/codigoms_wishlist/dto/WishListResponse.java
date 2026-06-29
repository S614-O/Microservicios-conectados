package com.servicio.codigoms_wishlist.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.servicio.codigoms_wishlist.model.EstadosWishList;

import lombok.Data;


@Data
public class WishListResponse {

    private Long id;
    private Long libroId;
    private String tituloLibro;
    private BigDecimal precioLibro;
    private String usuario;
    private EstadosWishList estado; 
    private String nota;
    private LocalDateTime fechaAgregado;
}
