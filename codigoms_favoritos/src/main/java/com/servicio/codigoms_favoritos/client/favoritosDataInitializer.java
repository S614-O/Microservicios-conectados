package com.servicio.codigoms_favoritos.client;

import com.servicio.codigoms_favoritos.model.Favorito;
import com.servicio.codigoms_favoritos.repository.FavoritoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class favoritosDataInitializer implements CommandLineRunner {

    private final FavoritoRepository favoritoRepository;
    @Override
    public void run(String... args) {
        if (favoritoRepository.count() > 0) {
            log.info(">>> DataInitializer: favoritos ya existentes, se omite carga");
            return;
        }

        favoritoRepository.save(Favorito.builder()
                .libroId(1L)
                .usuario("Panchosky")
                .build());

        favoritoRepository.save(Favorito.builder()
                .libroId(2L)
                .usuario("Panchosky")
                .build());

        log.info(">>> DataInitializer: Favoritos de prueba añadidos correctamente");
    }




}
