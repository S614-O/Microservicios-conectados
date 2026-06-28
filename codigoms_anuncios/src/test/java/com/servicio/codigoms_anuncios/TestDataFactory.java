package com.servicio.codigoms_anuncios;
import java.math.BigDecimal;

import com.servicio.codigoms_anuncios.dto.AnuncioRequest;
import com.servicio.codigoms_anuncios.dto.LibroDTO;
import com.servicio.codigoms_anuncios.model.Anuncio;

import net.datafaker.Faker;

public class TestDataFactory {
    private static final Faker faker = new Faker();

    public static LibroDTO unLibroDTO(Long id, double precio) {
        LibroDTO dto = new LibroDTO();
        dto.setId(id);
        dto.setTitulo(faker.book().title());
        dto.setPrecio(BigDecimal.valueOf(precio));
        return dto;
    }

    public static AnuncioRequest unAnuncioRequest(Long libroId) {
        return AnuncioRequest.builder()
                .libroId(libroId)
                .build();
    }

    public static Anuncio unAnuncio(Long libroId, String titulo, BigDecimal precio) {
        return Anuncio.builder()
                .id(faker.number().randomNumber())
                .libroId(libroId)
                .tituloLibro(titulo)
                .precioLibro(precio)
                .activo(true)
                .build();
    }



}
