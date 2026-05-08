package com.sting.carrito.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

/**
 * ItemCarrito
 *
 * Autor: Prof. Sting Parra Silva
 *
 * libroId es solo un numero — no hay @ManyToOne a Libro porque
 * Libro vive en otro microservicio con otra BD.
 * tituloLibro y precioUnitario son snapshots capturados via FeignClient
 * en el momento de agregar el item.
 */
@Getter @Setter @NoArgsConstructor
@ToString(exclude = "carrito")
@EqualsAndHashCode(exclude = "carrito")
@Entity
@Table(name = "items_carrito")
public class ItemCarrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Referencia logica al libro en codigoms_catalogo
    @Column(nullable = false)
    private Long libroId;

    // Snapshot del titulo al momento de agregar
    @Column(nullable = false)
    private String tituloLibro;

    // Snapshot del precio al momento de agregar
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precioUnitario;

    @Column(nullable = false)
    private Integer cantidad;

    @ManyToOne
    @JoinColumn(name = "carrito_id", nullable = false)
    private Carrito carrito;

    public ItemCarrito(Long libroId, String tituloLibro, BigDecimal precioUnitario,
                       Integer cantidad, Carrito carrito) {
        this.libroId = libroId;
        this.tituloLibro = tituloLibro;
        this.precioUnitario = precioUnitario;
        this.cantidad = cantidad;
        this.carrito = carrito;
    }
}
