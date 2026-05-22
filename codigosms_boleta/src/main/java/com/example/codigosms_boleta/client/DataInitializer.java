package com.example.codigosms_boleta.client;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.codigosms_boleta.model.BoletaCompra;
import com.example.codigosms_boleta.model.EstadoCompra;
import com.example.codigosms_boleta.model.MetodoPago;
import com.example.codigosms_boleta.repository.BoletaCompraRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final BoletaCompraRepository boletaCompraRepository;

    @Override
    public void run(String... args){
        if (boletaCompraRepository.count()>0){
            log.info(">>> DataInitializer: datos ya existentes, se omite carga");
            return;
        }
boletaCompraRepository.save(BoletaCompra.builder()
    .carritoId(1L)
    .usuario("PepitoPro")
    .montoTotal(new BigDecimal("45.99"))
    .estado(EstadoCompra.PENDIENTE)
    .metodoPago(MetodoPago.TARJETA_CREDITO) 
    .build());


    log.info("DataInitializer: Datos cargados correctamente");

    }
}
