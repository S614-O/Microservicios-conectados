package com.example.codigosms_usuario.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.codigosms_usuario.dto.UsuarioRequest;
import com.example.codigosms_usuario.dto.UsuarioResponse;
import com.example.codigosms_usuario.service.UsuarioService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
@RestController
@RequestMapping("/api/usuariosd")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService service;

    @PostMapping
    public ResponseEntity<UsuarioResponse> crearUsuario(@Valid @RequestBody UsuarioRequest dto){
        return ResponseEntity.status(201).body(service.crearUsuario(dto));
    }

}
/*        @PostMapping
    public ResponseEntity<ResenaResponse> crear(@Valid @RequestBody ResenaRequest dto) {
        return ResponseEntity.status(201).body(resenaService.crear(dto));
    } */