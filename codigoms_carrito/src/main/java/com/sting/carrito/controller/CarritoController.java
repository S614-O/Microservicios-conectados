package com.sting.carrito.controller;

import com.sting.carrito.dto.CarritoResponseDTO;
import com.sting.carrito.dto.ItemCarritoRequestDTO;
import com.sting.carrito.service.CarritoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carritos")
@RequiredArgsConstructor
public class CarritoController {

    private final CarritoService carritoService;

    @PostMapping
    public ResponseEntity<CarritoResponseDTO> crear(@RequestParam String usuario) {
        return ResponseEntity.status(201).body(carritoService.crear(usuario));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarritoResponseDTO> obtenerPorId(@PathVariable Long id) {
        return carritoService.obtenerPorId(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<CarritoResponseDTO>> obtenerPorUsuario(@RequestParam String usuario) {
        return ResponseEntity.ok(carritoService.obtenerPorUsuario(usuario));
    }

    @PostMapping("/{id}/items")
    public ResponseEntity<CarritoResponseDTO> agregarItem(@PathVariable Long id,
                                                          @Valid @RequestBody ItemCarritoRequestDTO dto) {
        return ResponseEntity.ok(carritoService.agregarItem(id, dto));
    }

    @DeleteMapping("/{id}/items/{itemId}")
    public ResponseEntity<CarritoResponseDTO> quitarItem(@PathVariable Long id,
                                                         @PathVariable Long itemId) {
        return ResponseEntity.ok(carritoService.quitarItem(id, itemId));
    }

    @DeleteMapping("/{id}/vaciar")
    public ResponseEntity<CarritoResponseDTO> vaciar(@PathVariable Long id) {
        return ResponseEntity.ok(carritoService.vaciar(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (carritoService.obtenerPorId(id).isEmpty()) return ResponseEntity.notFound().build();
        carritoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
