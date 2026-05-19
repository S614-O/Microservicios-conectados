package com.servicio.codigoms_cupones.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.servicio.codigoms_cupones.dto.CuponResponse;

@FeignClient(name = "codigoms-cupones", url = "http://localhost:8085")
public interface CuponClient {

    @GetMapping("/api/cupones/validar/{codigo}")
    CuponResponse validarCupon(@PathVariable("codigo") String codigo);
}
