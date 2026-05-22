package com.servicio.codigoms_wishlist.client;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.servicio.codigoms_wishlist.dto.LibroDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component
@RequiredArgsConstructor

public class CatalogoClient {
    private final WebClient catalogoWebClient;
    
    public Optional<LibroDTO> buscarLibro(Long libroId) {
        try {
            LibroDTO libro = catalogoWebClient.get()
                    .uri("/api/libros/{id}", libroId)
                    .retrieve()
                    .bodyToMono(LibroDTO.class)
                    .block();
            return Optional.ofNullable(libro);
        } catch (WebClientResponseException.NotFound e) {
            return Optional.empty();
        } catch (Exception e) {
            log.error("Error al conectar con codigoms_catalogo: {}", e.getMessage());
            throw new RuntimeException("El servicio de catálogo no está disponible");
        }
    }
}
