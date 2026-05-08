package com.sting.catalogo.controller;

import com.sting.catalogo.dto.LibroRequestDTO;
import com.sting.catalogo.dto.LibroResponseDTO;
import com.sting.catalogo.service.LibroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * LibroController
 *
 * Autor: Prof. Sting Parra Silva
 *
 * GET /api/libros/{id} es el endpoint que consumen
 * codigoms_carrito y codigoms_pedidos via FeignClient.
 */
@RestController
@RequestMapping("/api/libros")
@RequiredArgsConstructor
public class LibroController {

    private final LibroService libroService;

    @GetMapping
    public ResponseEntity<List<LibroResponseDTO>> obtenerTodos() {
        return ResponseEntity.ok(libroService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LibroResponseDTO> obtenerPorId(@PathVariable Long id) {
        return libroService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<LibroResponseDTO> crear(@Valid @RequestBody LibroRequestDTO dto) {
        return ResponseEntity.status(201).body(libroService.guardar(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (libroService.obtenerPorId(id).isEmpty()) return ResponseEntity.notFound().build();
        libroService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
