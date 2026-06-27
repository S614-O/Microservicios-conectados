package com.servicio.codigoms_cupones.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.servicio.codigoms_cupones.dto.CuponRequest;
import com.servicio.codigoms_cupones.dto.CuponResponse;
import com.servicio.codigoms_cupones.service.CuponService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/cupones")
@RequiredArgsConstructor
public class CuponController {
    private final CuponService cuponService;

    @GetMapping
    public ResponseEntity<List<CuponResponse>> obtenerTodos() {
        return ResponseEntity.ok(cuponService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuponResponse> obtenerPorId(@PathVariable Long id) {
        return cuponService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CuponResponse> crear(@Valid @RequestBody CuponRequest dto) {
        return ResponseEntity.status(201).body(cuponService.guardar(dto));
    }

    @GetMapping("/validar/{codigo}")
    public ResponseEntity<CuponResponse> validar(@PathVariable String codigo) {
        return cuponService.validarCupon(codigo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/desactivar")
    public ResponseEntity<CuponResponse> desactivar(@PathVariable Long id) {
        return cuponService.desactivar(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (cuponService.obtenerPorId(id).isEmpty()) return ResponseEntity.notFound().build();
        cuponService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
