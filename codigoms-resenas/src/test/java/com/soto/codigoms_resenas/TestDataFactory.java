package com.soto.codigoms_resenas;

import com.soto.codigoms_resenas.DTO.LibroDTO;
import com.soto.codigoms_resenas.DTO.ResenaRequest;
import com.soto.codigoms_resenas.model.Resenia;
import net.datafaker.Faker;
import java.time.LocalDateTime;
import java.util.Locale;

public class TestDataFactory {
    private static final Faker faker = new Faker(Locale.of("es"));

    public static Resenia unaReseniaValida() {
        return Resenia.builder()
                .id(faker.number().numberBetween(1L, 100L))
                .libroId(faker.number().numberBetween(1L, 500L))
                .tituloLibro(faker.book().title())
                .usuario(faker.internet().username())
                .calificacion(faker.number().numberBetween(1, 6))
                .comentario(faker.lorem().sentence())
                .fechaResena(LocalDateTime.now())
                .build();
    }

    public static ResenaRequest unaResenaRequest() {
        ResenaRequest dto = new ResenaRequest();
        dto.setLibroId(faker.number().numberBetween(1L, 500L));
        dto.setUsuario(faker.internet().username());
        dto.setCalificacion(faker.number().numberBetween(1, 6));
        dto.setComentario(faker.lorem().sentence());
        return dto;
    }

    public static LibroDTO unLibroDTO(Long id) {
        LibroDTO libro = new LibroDTO();
        libro.setId(id);
        libro.setTitulo(faker.book().title());
        return libro;
    }
}