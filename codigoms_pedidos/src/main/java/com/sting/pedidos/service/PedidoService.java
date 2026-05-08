package com.sting.pedidos.service;

import com.sting.pedidos.client.CatalogoClient;
import com.sting.pedidos.dto.LibroDTO;
import com.sting.pedidos.dto.PedidoRequestDTO;
import com.sting.pedidos.dto.PedidoResponseDTO;
import com.sting.pedidos.model.Pedido;
import com.sting.pedidos.repository.PedidoRepository;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final CatalogoClient catalogoClient;

    @Transactional
    public PedidoResponseDTO crear(PedidoRequestDTO dto) {
        LibroDTO libro = consultarLibro(dto.getLibroId());

        Pedido pedido = new Pedido();
        pedido.setLibroId(libro.getId());
        pedido.setTituloLibro(libro.getTitulo());
        pedido.setPrecioUnitario(libro.getPrecio());
        pedido.setCliente(dto.getCliente());
        pedido.setCantidad(dto.getCantidad());
        pedido.setFechaPedido(LocalDateTime.now());

        Pedido guardado = pedidoRepository.save(pedido);
        log.info("Pedido creado id={} para libro '{}'", guardado.getId(), libro.getTitulo());
        return mapToDTO(guardado);
    }

    public List<PedidoResponseDTO> obtenerTodos() {
        return pedidoRepository.findAll().stream().map(this::mapToDTO).toList();
    }

    public Optional<PedidoResponseDTO> obtenerPorId(Long id) {
        return pedidoRepository.findById(id).map(this::mapToDTO);
    }

    public List<PedidoResponseDTO> obtenerPorCliente(String cliente) {
        return pedidoRepository.findByCliente(cliente).stream().map(this::mapToDTO).toList();
    }

    @Transactional
    public void eliminar(Long id) {
        pedidoRepository.deleteById(id);
    }

    private LibroDTO consultarLibro(Long libroId) {
        try {
            return catalogoClient.obtenerLibro(libroId);
        } catch (FeignException.NotFound e) {
            throw new RuntimeException("El libro con id " + libroId + " no existe en el catalogo");
        } catch (FeignException e) {
            log.error("Error al contactar codigoms_catalogo: {}", e.getMessage());
            throw new RuntimeException("No se pudo verificar el libro. Verifique que codigoms_catalogo este corriendo.");
        }
    }

    private PedidoResponseDTO mapToDTO(Pedido p) {
        PedidoResponseDTO dto = new PedidoResponseDTO();
        dto.setId(p.getId());
        dto.setLibroId(p.getLibroId());
        dto.setTituloLibro(p.getTituloLibro());
        dto.setPrecioUnitario(p.getPrecioUnitario());
        dto.setCliente(p.getCliente());
        dto.setCantidad(p.getCantidad());
        dto.setFechaPedido(p.getFechaPedido());
        dto.setTotal(p.getPrecioUnitario().multiply(BigDecimal.valueOf(p.getCantidad())));
        return dto;
    }
}
