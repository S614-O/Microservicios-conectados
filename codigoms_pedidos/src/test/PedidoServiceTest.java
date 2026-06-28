package com.servicio.pedidos;
import com.servicio.pedidos.client.CatalogoClient;
import com.servicio.pedidos.dto.LibroDTO;
import com.servicio.pedidos.dto.PedidoRequestDTO;
import com.servicio.pedidos.dto.PedidoResponseDTO;
import com.servicio.pedidos.model.Pedido;
import com.servicio.pedidos.repository.PedidoRepository;
import com.servicio.pedidos.service.PedidoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PedidoServiceTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private CatalogoClient catalogoClient;

    @InjectMocks
    private PedidoService pedidoService;

    @Test
    void crear_cuandoTodoEsValido_retornaPedidoConTotalCalculado() {

        PedidoRequestDTO request = TestDataFactory.unPedidoRequest();
        LibroDTO libro = TestDataFactory.unLibroDTO(request.getLibroId());
        
        when(catalogoClient.obtenerLibro(request.getLibroId())).thenReturn(libro);
        
        Pedido pedidoGuardado = TestDataFactory.unPedidoValido();
        pedidoGuardado.setLibroId(request.getLibroId());
        pedidoGuardado.setCantidad(request.getCantidad());
        pedidoGuardado.setPrecioUnitario(libro.getPrecio());
        
        when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedidoGuardado);


        PedidoResponseDTO response = pedidoService.crear(request);


        BigDecimal totalEsperado = libro.getPrecio().multiply(BigDecimal.valueOf(request.getCantidad()));
        assertThat(response.getTotal()).isEqualByComparingTo(totalEsperado);
        verify(pedidoRepository, times(1)).save(any(Pedido.class));
    }

    @Test
    void crear_cuandoLibroNoExiste_lanzaExcepcion() {

        PedidoRequestDTO request = TestDataFactory.unPedidoRequest();
        when(catalogoClient.obtenerLibro(request.getLibroId()))
            .thenThrow(new feign.FeignException.NotFound("Not Found", null, null, null));

        assertThatThrownBy(() -> pedidoService.crear(request))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("no existe en el catalogo");
    }
}