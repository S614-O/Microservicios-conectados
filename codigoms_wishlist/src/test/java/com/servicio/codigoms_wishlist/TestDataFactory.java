package com.servicio.codigoms_wishlist;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Locale;

import com.servicio.codigoms_wishlist.dto.LibroDTO;
import com.servicio.codigoms_wishlist.dto.WishListRequest;
import com.servicio.codigoms_wishlist.model.EstadosWishList;
import com.servicio.codigoms_wishlist.model.WishList;

import net.datafaker.Faker;

public class TestDataFactory {
    
    private static final Faker faker = new Faker(Locale.of("es"));

    public static WishList unaWishListValida() {
        return WishList.builder()
                .id(faker.number().numberBetween(1L, 1000L))
                .libroId(faker.number().numberBetween(1L, 500L))
                .tituloLibro(faker.book().title())
                .precioLibro(BigDecimal.valueOf(faker.number().randomDouble(2, 5000, 30000)))
                .usuario(faker.internet().username())
                .estado(EstadosWishList.PENDIENTE)
                .nota(faker.lorem().sentence())
                .fechaAgregado(LocalDateTime.now())
                .build();
    }

    public static WishListRequest unWishListRequest() {
        WishListRequest request = new WishListRequest();
        request.setLibroId(faker.number().numberBetween(1L, 500L));
        request.setUsuario(faker.internet().username());
        request.setNota(faker.lorem().sentence());
        return request;
    }

    public static LibroDTO unLibroDTO(Long id) {
        LibroDTO libro = new LibroDTO();
        libro.setId(id);
        libro.setTitulo(faker.book().title());
        libro.setPrecio(BigDecimal.valueOf(faker.number().randomDouble(2, 5000, 30000)));
        return libro;
    }
}