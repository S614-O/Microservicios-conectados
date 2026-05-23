package com.example.codigosms_boleta.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.codigosms_boleta.client.CarritoClient;
import com.example.codigosms_boleta.dto.BoletaCompraRequest;
import com.example.codigosms_boleta.dto.BoletaCompraResponse;
import com.example.codigosms_boleta.dto.CarritoDTO;
import com.example.codigosms_boleta.model.BoletaCompra;
import com.example.codigosms_boleta.model.EstadoCompra;
import com.example.codigosms_boleta.repository.BoletaCompraRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor

public class BoletaCompraService {
    private final BoletaCompraRepository compraRepository;
    
    private final CarritoClient carritoClient;

    @Transactional
    public BoletaCompraResponse crearBoleta(BoletaCompraRequest request) {
        log.info("Registrando compra...");
        CarritoDTO carrito = carritoClient.obtenerCarritoPorId(request.getCarritoId());
    
        validarCarrito(carrito, request.getUsuario());
        // Si la validación pasa, continuamos con el cálculo del total
   
         BigDecimal totalCalculado = carrito.getItems().stream()
            .map(item -> item.getPrecioUnitario().multiply(new BigDecimal(item.getCantidad())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);

    // Crear y guardar la entidad Compra
    BoletaCompra nuevaCompra = new BoletaCompra();
    nuevaCompra.setUsuario(request.getUsuario());
    nuevaCompra.setCarritoId(carrito.getId());
    nuevaCompra.setMontoTotal(totalCalculado);
    nuevaCompra.setMetodoPago(request.getMetodoPago());
    nuevaCompra.setEstado(EstadoCompra.PENDIENTE);
    nuevaCompra.setFechaCompra(LocalDateTime.now());

    return mapADTO(compraRepository.save(nuevaCompra));
        
    }

    @Transactional
    public BoletaCompraResponse obtenerBoletaPorId(Long id) {
        return compraRepository.findById(id)
                .map(this::mapADTO)
                .orElseThrow(() -> new jakarta.persistence.EntityNotFoundException(
                    "Anuncio con ID " + id + " no fue encontrado"
                ));
    }


    public void eliminarUsuario(Long id) {
        compraRepository.deleteById(id);
       
    }


    


    @Transactional
    public Page<BoletaCompraResponse> listarBoletas(Pageable pageable){
        Page<BoletaCompra> paginaBoleta = compraRepository.findAll(pageable);
        return paginaBoleta.map(this::mapADTO);
    }



    



    private void validarCarrito(CarritoDTO carrito, String usuarioRequest) {
    // 1. Validar que el microservicio de carrito devolvió información
    if (carrito == null) {
        throw new RuntimeException("Error: El carrito no pudo ser recuperado.");
    }

    if (carrito.getItems() == null || carrito.getItems().isEmpty()) {
        throw new RuntimeException("Error: El carrito está vacío.");
    }

    if (!carrito.getUsuario().equalsIgnoreCase(usuarioRequest)) {
        throw new RuntimeException("Error: El carrito no pertenece al usuario indicado.");
    }
    

}






private BoletaCompraResponse mapADTO(BoletaCompra c){
    return BoletaCompraResponse.builder()
                .compraId(c.getId())
                .usuario(c.getUsuario())
                .montoTotal(c.getMontoTotal())
                .estado(c.getEstado().name())
                .fechaCompra(c.getFechaCompra())
                .build();
}















}

