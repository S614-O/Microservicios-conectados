package com.servicio.codigoms_wishlist.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.servicio.codigoms_wishlist.client.CatalogoClient;
import com.servicio.codigoms_wishlist.dto.LibroDTO;
import com.servicio.codigoms_wishlist.dto.WishListRequest;
import com.servicio.codigoms_wishlist.dto.WishListResponse;
import com.servicio.codigoms_wishlist.model.EstadosWishList;
import com.servicio.codigoms_wishlist.model.WishList;
import com.servicio.codigoms_wishlist.repository.WishListRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor

public class WishListService {
    private final WishListRepository wishListRepository;
    private final CatalogoClient catalogoClient;

    //Convertirlo a DTO
    private WishListResponse mapToResponse(WishList wishList) {
        WishListResponse response = new WishListResponse();
        response.setId(wishList.getId());
        response.setLibroId(wishList.getLibroId());
        response.setTituloLibro(wishList.getTituloLibro());
        response.setPrecioLibro(wishList.getPrecioLibro());
        response.setUsuario(wishList.getUsuario());
        response.setEstado(wishList.getEstado());
        response.setNota(wishList.getNota());
        response.setFechaAgregado(wishList.getFechaAgregado());
        return response;
    }
//crear un item en la wishlist, validando que el libro exista en el catálogo y que no esté ya en la wishlist del usuario
//Revisar validaciones (BORRAR DESPUES DE REVISAR XD)

    public WishListResponse agregarLibro(WishListRequest dto) {
        LibroDTO libro = catalogoClient.buscarLibro(dto.getLibroId())
                .orElseThrow(() -> new RuntimeException(
                        "El libro con id " + dto.getLibroId() + " no existe en el catálogo"));

        if (wishListRepository.existsByUsuarioAndLibroId(dto.getUsuario(), dto.getLibroId()))
            throw new RuntimeException(
                    "El usuario " + dto.getUsuario() + " ya tiene ese libro en la wishlist");


        WishList wishList = new WishList();
        wishList.setLibroId(libro.getId());
        wishList.setTituloLibro(libro.getTitulo());
        wishList.setPrecioLibro(libro.getPrecio());
        wishList.setUsuario(dto.getUsuario());
        wishList.setEstado(EstadosWishList.PENDIENTE);
        wishList.setNota(dto.getNota());
        wishList.setFechaAgregado(LocalDateTime.now());

        WishList guardado = wishListRepository.save(wishList);
        log.info("Libro '{}' agregado a wishlist de {}", libro.getTitulo(), dto.getUsuario());
        return mapToResponse(guardado);
}


    public void eliminar(Long id) {
        if (!wishListRepository.existsById(id))
            throw new RuntimeException("Item no encontrado con id: " + id);
        wishListRepository.deleteById(id);
    }


// Obtener wishlist de un usuario
    public List<WishListResponse> obtenerPorUsuario(String usuario) {
        log.info("Obteniendo wishlist del usuario={}", usuario);
        return wishListRepository.findByUsuario(usuario)
            .stream().map(this::mapToResponse).toList();
    }


    //cambia estado de un item de la wishlist
    public Optional<WishListResponse> cambiarEstado(Long id, EstadosWishList estado) {
        log.info("Cambiando estado id={} a {}", id, estado);
        return wishListRepository.findById(id).map(item -> {
            item.setEstado(estado);
            return mapToResponse(wishListRepository.save(item));
        });
    }

    // Obtener wishlist de un usuario filtrada por estado
    public List<WishListResponse> obtenerPorUsuarioYEstado(String usuario, EstadosWishList estado) {
        log.info("Obteniendo wishlist usuario={} estado={}", usuario, estado);
        return wishListRepository.findByUsuarioAndEstado(usuario, estado)
            .stream().map(this::mapToResponse).toList();
    }
}
