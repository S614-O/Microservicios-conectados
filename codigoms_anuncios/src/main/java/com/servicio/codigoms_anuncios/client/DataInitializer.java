package com.servicio.codigoms_anuncios.client;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.servicio.codigoms_anuncios.model.Anuncio;
import com.servicio.codigoms_anuncios.repository.AnuncioRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements  CommandLineRunner{

    private final AnuncioRepository anuncioRepository;

    @Override
    public void run(String... args) {
       
        if (anuncioRepository.count() > 0) {
            log.info(">>> DataInitializer: anuncios ya existentes, se omite carga");
            return;
        }

       
        anuncioRepository.save(Anuncio.builder()
                .libroId(1L)
                .tituloLibro("Clean Code")
                .precioLibro(new java.math.BigDecimal("45.99"))
                .build());

        
        anuncioRepository.save(Anuncio.builder()
                .libroId(2L)
                .tituloLibro("Spring Boot in Action")
                .precioLibro(new java.math.BigDecimal("52.00"))
                .build());

       

        log.info(">>> DataInitializer: Anuncios de prueba añadidos correctamente");
    }







}
