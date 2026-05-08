package com.sting.carrito.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Carrito
 *
 * Autor: Prof. Sting Parra Silva
 *
 * No tiene FK a Libro ni a Categoria — esas entidades viven
 * en codigoms_catalogo (otra BD, otro servidor).
 * ItemCarrito solo guarda libroId como referencia numerica.
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "carritos")
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String usuario;

    @Column(nullable = false)
    private LocalDateTime fechaCreacion;

    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL,
               orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ItemCarrito> items = new ArrayList<>();

    public Carrito(String usuario) {
        this.usuario = usuario;
        this.fechaCreacion = LocalDateTime.now();
    }
}
