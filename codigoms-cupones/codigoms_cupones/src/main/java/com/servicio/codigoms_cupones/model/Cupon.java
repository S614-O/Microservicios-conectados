package com.servicio.codigoms_cupones.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cupones")

public class Cupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El codigo no puede estar vacio")
    @Column(nullable = false, unique = true, length = 50)
    private String codigo;

    @Column(nullable = false)
    private Double descuento;
    
    @Column(nullable = false)
    private boolean activo;

    @Column(nullable = false)
    private LocalDate fechaExpiracion;
}
