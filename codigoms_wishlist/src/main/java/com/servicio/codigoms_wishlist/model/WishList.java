package com.servicio.codigoms_wishlist.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "wishlist")
@Builder
public class WishList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long libroId;

    @Column(nullable = false)
    private String tituloLibro;

    @Column(nullable = false)
    private BigDecimal precioLibro;

    @Column(nullable = false)
    private String usuario;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadosWishList estado; // "PENDIENTE", "COMPRADO", "ELIMINADO"

    @Column
    private String nota;

    @Column(nullable = false)
    private LocalDateTime fechaAgregado;
}
