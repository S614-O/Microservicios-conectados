package com.servicio.codigoms_cupones;

import java.time.LocalDate;
import java.util.Locale;

import com.servicio.codigoms_cupones.dto.CuponRequest;
import com.servicio.codigoms_cupones.model.Cupon;

import net.datafaker.Faker;

public class TestDataFactory {
    
    private static final Faker faker = new Faker(Locale.of("es"));

    public static Cupon unCuponValido() {
        return Cupon.builder()
                .id(faker.number().numberBetween(100L, 1000L))
                .codigo("PROMO-" + faker.random().hex(6).toUpperCase())
                .descuento(faker.number().randomDouble(2, 5, 50))
                .activo(true)
                .fechaExpiracion(LocalDate.now().plusDays(10)) // Expira en el futuro
                .build();
    }

    public static Cupon unCuponExpirado() {
        Cupon cupon = unCuponValido();
        cupon.setFechaExpiracion(LocalDate.now().minusDays(5)); // Expiró hace 5 días
        return cupon;
    }
    
    public static Cupon unCuponInactivo() {
        Cupon cupon = unCuponValido();
        cupon.setActivo(false);
        return cupon;
    }

    
    public static CuponRequest unCuponRequest() {
        CuponRequest request = new CuponRequest();
        request.setCodigo("PROMO-" + faker.random().hex(6).toUpperCase());
        request.setDescuento(faker.number().randomDouble(2, 5, 50));
        request.setFechaExpiracion(LocalDate.now().plusDays(10));
        return request;
    }
}