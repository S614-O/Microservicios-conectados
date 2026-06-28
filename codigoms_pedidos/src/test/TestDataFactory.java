package com.servicio.pedidos;

import com.servicio.pedidos.dto.LibroDTO;
import com.servicio.pedidos.dto.PedidoRequestDTO;
import com.servicio.pedidos.model.Pedido;
import net.datafaker.Faker;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Locale;

public class TestDataFactory {
    private static final Faker faker = new Faker(Locale.of("es"));

    public static Pedido unPedidoValido() {
        return Pedido.builder()
                .id(faker.number().numberBetween(1L, 1000L))
                .libroId(faker.number().numberBetween(1L, 500L))
                .tituloLibro(faker.book().title())
                .precioUnitario(BigDecimal.valueOf(faker.number().randomDouble(2, 5000, 30000)))
                .cliente(faker.name().fullName())
                .cantidad(faker.number().numberBetween(1, 5))
                .fechaPedido(LocalDateTime.now())
                .build();
    }

    public static PedidoRequestDTO unPedidoRequest() {
        return PedidoRequestDTO.builder()
                .libroId(faker.number().numberBetween(1L, 500L))
                .cliente(faker.name().fullName())
                .cantidad(faker.number().numberBetween(1, 5))
                .build();
    }

    public static LibroDTO unLibroDTO(Long id) {
        return LibroDTO.builder()
                .id(id)
                .titulo(faker.book().title())
                .precio(BigDecimal.valueOf(faker.number().randomDouble(2, 5000, 30000)))
                .build();
    }
}