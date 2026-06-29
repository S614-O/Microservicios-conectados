package com.servicio.codigoms_favoritos;

import java.time.LocalDateTime;
import java.util.Locale;

import com.servicio.codigoms_favoritos.dto.FavoritoRequestDTO;
import com.servicio.codigoms_favoritos.dto.LibroDTO;
import com.servicio.codigoms_favoritos.model.Favorito;

import net.datafaker.Faker;

public class TestDataFactory {
    private static final Faker faker = new Faker(Locale.of("es"));

    public static Favorito unFavoritoValido() {
        return Favorito.builder()
                .id(faker.number().numberBetween(1L, 100L))
                .libroId(faker.number().numberBetween(1L, 500L))
                .tituloLibro(faker.book().title())
                .usuario(faker.internet().username())
                .fechaAgregado(LocalDateTime.now())
                .build();
    }

    public static FavoritoRequestDTO unRequest() {
        return new FavoritoRequestDTO(faker.number().numberBetween(1L, 500L), faker.internet().username());
    }

public static LibroDTO unLibroDTO(Long id) {
    return LibroDTO.builder()
            .id(id)
            .titulo(faker.book().title())
            .build();
}
}