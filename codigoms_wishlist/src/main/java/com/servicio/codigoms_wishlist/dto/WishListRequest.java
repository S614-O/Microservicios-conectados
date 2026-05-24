package com.servicio.codigoms_wishlist.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class WishListRequest {

    @NotNull(message= "El ID del libro es obligatorio")
    private Long libroId;

    @NotBlank(message= "El USUARIO es obligatorio")
    private String usuario;

    
    private String nota; // comentario opcional 

}
