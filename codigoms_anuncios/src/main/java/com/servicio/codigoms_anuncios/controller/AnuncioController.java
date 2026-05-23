package com.servicio.codigoms_anuncios.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.servicio.codigoms_anuncios.dto.AnuncioRequest;
import com.servicio.codigoms_anuncios.dto.AnuncioResponse;
import com.servicio.codigoms_anuncios.service.AnuncioService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/anuncios")
@RequiredArgsConstructor
public class AnuncioController {
    private final AnuncioService anuncioService;


@PostMapping
    public ResponseEntity<AnuncioResponse> crear(@Valid @RequestBody AnuncioRequest dto) {
        return ResponseEntity.status(201).body(anuncioService.crearAnuncio(dto));
    }

    // Cambiado a Page para soportar la paginación eficiente que definimos
    @GetMapping
    public ResponseEntity<Page<AnuncioResponse>> obtenerTodos(
            @org.springframework.data.web.PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(anuncioService.listarAnuncios(pageable));
    }

@GetMapping("/{id}")
public ResponseEntity<AnuncioResponse> obtenerPorId(@PathVariable Long id) {
    AnuncioResponse response = anuncioService.obtenerAnuncioPorId(id);
    return ResponseEntity.ok(response);
}



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        
        anuncioService.eliminarAnuncio(id);
        return ResponseEntity.noContent().build();
    }


}
