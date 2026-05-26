package com.servicio.codigoms_favoritos.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "favoritos")
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Favorito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long libroId;

    @Column(nullable = false)
    private String tituloLibro;

    @Column(nullable = false)
    private String usuario;

    @Column(nullable = false)
    private LocalDateTime fechaAgregado;
}

