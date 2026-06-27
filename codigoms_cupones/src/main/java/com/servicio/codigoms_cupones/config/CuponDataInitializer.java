package com.servicio.codigoms_cupones.config;
 // Ajusta el paquete de tu entidad si cambia
import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.servicio.codigoms_cupones.model.Cupon;
import com.servicio.codigoms_cupones.repository.CuponRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class CuponDataInitializer implements CommandLineRunner {

    private final CuponRepository cuponRepository;

    @Override
    public void run(String... args) {
        if (cuponRepository.count() > 0) {
            log.info(">>> DataInitializer: cupones ya existentes, se omite carga");
            return;
        }

        cuponRepository.save(Cupon.builder()
                .codigo("VERANO2026")
                .descuento(15.0)
                .fechaExpiracion(LocalDate.of(2026, 5, 28))
                .build());

        cuponRepository.save(Cupon.builder()
                .codigo("LIBROS2026")
                .descuento(90.0)
                .fechaExpiracion(LocalDate.of(2026, 6, 29))
                .build());

        log.info(">>> DataInitializer: Cupones de prueba añadidos correctamente");
    }
}