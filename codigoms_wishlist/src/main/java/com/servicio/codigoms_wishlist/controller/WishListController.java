package com.servicio.codigoms_wishlist.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.servicio.codigoms_wishlist.dto.WishListRequest;
import com.servicio.codigoms_wishlist.dto.WishListResponse;
import com.servicio.codigoms_wishlist.model.EstadosWishList;
import com.servicio.codigoms_wishlist.service.WishListService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/wishlist")
@RequiredArgsConstructor
public class WishListController {
    private final WishListService wishListService;

    @PostMapping
    public ResponseEntity<WishListResponse> agregar(@Valid @RequestBody WishListRequest dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(wishListService.agregarLibro(dto));
    }

    @GetMapping("/usuario/{usuario}")
    public ResponseEntity<List<WishListResponse>> listarPorUsuario(@PathVariable String usuario) {
        return ResponseEntity.ok(wishListService.obtenerPorUsuario(usuario));
    }

    @GetMapping("/usuario/{usuario}/estado/{estado}")
    public ResponseEntity<List<WishListResponse>> listarPorUsuarioYEstado(@PathVariable String usuario, @PathVariable EstadosWishList estado) {
        return ResponseEntity.ok(wishListService.obtenerPorUsuarioYEstado(usuario, estado));
    }

    @PutMapping("/{id}/estado/{estado}")
    public ResponseEntity<WishListResponse> cambiarEstado(@PathVariable Long id, @PathVariable EstadosWishList estado) {
        return wishListService.cambiarEstado(id, estado).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        wishListService.eliminar(id);
        return ResponseEntity.noContent().build();
    }


    

}
