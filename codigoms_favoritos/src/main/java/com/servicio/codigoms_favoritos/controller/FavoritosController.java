package com.servicio.codigoms_favoritos.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.servicio.codigoms_favoritos.dto.FavoritoRequestDTO;
import com.servicio.codigoms_favoritos.dto.FavoritoResponseDTO;
import com.servicio.codigoms_favoritos.service.FavoritoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/favoritos")
@RequiredArgsConstructor
public class FavoritosController {
 private final FavoritoService favoritoService;

    @PostMapping
    public ResponseEntity<FavoritoResponseDTO> agregar(@Valid @RequestBody FavoritoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(favoritoService.agregar(dto));
    }

    @GetMapping
    public ResponseEntity<List<FavoritoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(favoritoService.listarTodos());
    }

    @GetMapping("/usuario/{usuario}")
    public ResponseEntity<List<FavoritoResponseDTO>> listarPorUsuario(@PathVariable String usuario) {
        return ResponseEntity.ok(favoritoService.listarPorUsuario(usuario));
    }

    @GetMapping("/libro/{libroId}")
    public ResponseEntity<List<FavoritoResponseDTO>> listarPorLibro(@PathVariable Long libroId) {
        return ResponseEntity.ok(favoritoService.listarPorLibro(libroId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        favoritoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
