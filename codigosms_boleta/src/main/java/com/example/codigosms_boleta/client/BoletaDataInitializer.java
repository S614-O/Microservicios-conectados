package main.java.com.servicio.codigoms_boletas.client;
import com.servicio.codigoms_boletas.modelo.BoletaCompra;
import com.servicio.codigoms_boletas.modelo.MetodoPago;
import com.servicio.codigoms_boletas.repository.BoletaCompraRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class BoletaDataInitializer implements CommandLineRunner {

    private final BoletaCompraRepository boletaCompraRepository;

    @Override
    public void run(String... args) {
        if (boletaCompraRepository.count() > 0) {
            log.info(">>> DataInitializer: boletas ya existentes, se omite carga");
            return;
        }

        boletaCompraRepository.save(BoletaCompra.builder()
                .carritoId(1L)
                .usuario("PepitoPro")
                .metodoPago(MetodoPago.EFECTIVO)
                .build());

        boletaCompraRepository.save(BoletaCompra.builder()
                .carritoId(2L)
                .usuario("Panchosky")
                .metodoPago(MetodoPago.TRANSFERENCIA)
                .build());

        log.info(">>> DataInitializer: Boletas de prueba añadidas correctamente");
    }
}
