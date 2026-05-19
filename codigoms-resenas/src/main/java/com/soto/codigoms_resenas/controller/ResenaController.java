package com.soto.codigoms_resenas.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soto.codigoms_resenas.DTO.PromedioResponse;
import com.soto.codigoms_resenas.DTO.ResenaRequest;
import com.soto.codigoms_resenas.DTO.ResenaResponse;
import com.soto.codigoms_resenas.service.ResenaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/resenas")
@RequiredArgsConstructor
public class ResenaController {
private final ResenaService resenaService;

    @PostMapping
    public ResponseEntity<ResenaResponse> crear(@Valid @RequestBody ResenaRequest dto) {
        return ResponseEntity.status(201).body(resenaService.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<ResenaResponse>> obtenerTodas() {
        return ResponseEntity.ok(resenaService.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResenaResponse> obtenerPorId(@PathVariable Long id) {
        return resenaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/libro/{libroId}")
    public ResponseEntity<List<ResenaResponse>> obtenerPorLibro(@PathVariable Long libroId) {
        return ResponseEntity.ok(resenaService.obtenerPorLibro(libroId));
    }

    @GetMapping("/libro/{libroId}/promedio")
    public ResponseEntity<PromedioResponse> obtenerPromedio(@PathVariable Long libroId) {
        return ResponseEntity.ok(resenaService.obtenerPromedio(libroId));
    }

    @GetMapping("/usuario/{usuario}")
    public ResponseEntity<List<ResenaResponse>> obtenerPorUsuario(@PathVariable String usuario) {
        return ResponseEntity.ok(resenaService.obtenerPorUsuario(usuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        resenaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
