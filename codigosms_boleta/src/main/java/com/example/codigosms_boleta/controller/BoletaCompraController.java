package com.example.codigosms_boleta.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.codigosms_boleta.dto.BoletaCompraRequest;
import com.example.codigosms_boleta.dto.BoletaCompraResponse;
import com.example.codigosms_boleta.service.BoletaCompraService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/boletas")
@RequiredArgsConstructor
public class BoletaCompraController {
private final BoletaCompraService boletaCompraService;

    @PostMapping
    public ResponseEntity<BoletaCompraResponse> crearCompra(@Valid @RequestBody BoletaCompraRequest request) {
        // Llamamos al servicio para procesar la lógica de negocio
        BoletaCompraResponse nuevaBoleta = boletaCompraService.crearBoleta(request);
        
        // Retornamos la respuesta con estado 201 (Created)
        return new ResponseEntity<>(nuevaBoleta, HttpStatus.CREATED);
    }















}




