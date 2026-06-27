package com.soto.codigoms_resenas.client;

import com.soto.codigoms_resenas.model.Resenia;
import com.soto.codigoms_resenas.repository.ResenaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ResenaDataInitializer implements CommandLineRunner {
    private final ResenaRepository resenaRepository;

    @Override
    public void run(String... args) {
        if (resenaRepository.count() > 0) {
            log.info(">>> DataInitializer: reseñas ya existentes, se omite carga");
            return;
        }

        resenaRepository.save(Resenia.builder()
                .libroId(1L)
                .usuario("PepitoPro")
                .calificacion(4)
                .comentario("Muy Bueno")
                .build());

        resenaRepository.save(Resenia.builder()
                .libroId(2L)
                .usuario("Panchosky")
                .calificacion(1)
                .comentario("No Entendi nada")
                .build());

        log.info(">>> DataInitializer: Reseñas de prueba añadidas correctamente");
    }



}

