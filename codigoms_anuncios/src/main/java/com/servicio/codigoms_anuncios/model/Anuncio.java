package com.servicio.codigoms_anuncios.model;


import java.math.BigDecimal;

import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Table(name = "anuncios") 
@Data 
@NoArgsConstructor 
@AllArgsConstructor 

public class Anuncio {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
 
    @Column(nullable=false)
    private Long libroId;

    @Column(nullable=false)
    private String tituloLibro;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precioLibro;

    private boolean activo;

@PrePersist
    protected void onCreate() {
        this.activo = true; 
    
    }

}
