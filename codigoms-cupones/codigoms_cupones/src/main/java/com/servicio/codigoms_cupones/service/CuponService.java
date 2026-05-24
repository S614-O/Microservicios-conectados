package com.servicio.codigoms_cupones.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.servicio.codigoms_cupones.dto.CuponRequest;
import com.servicio.codigoms_cupones.dto.CuponResponse;
import com.servicio.codigoms_cupones.model.Cupon;
import com.servicio.codigoms_cupones.repository.CuponRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CuponService {
    private final CuponRepository cuponRepository;

    
    private CuponResponse mapToDTO(Cupon c) {
        return new CuponResponse(c.getId(), c.getCodigo(), c.getDescuento(), c.isActivo(), c.getFechaExpiracion());
    }

    
    public List<CuponResponse> obtenerTodos() {
        return cuponRepository.findAll().stream().map(this::mapToDTO).toList();
    }

    public Optional<CuponResponse> obtenerPorId(Long id) {
        log.info("Consulta de cupon id={}", id);
        return cuponRepository.findById(id).map(this::mapToDTO);
    }

    public CuponResponse guardar(CuponRequest dto) {
        if (cuponRepository.existsByCodigo(dto.getCodigo().toUpperCase()))
            throw new RuntimeException("Ya existe un cupon con el codigo: " + dto.getCodigo());
        if (dto.getFechaExpiracion().isBefore(LocalDate.now()))
            throw new RuntimeException("La fecha de expiracion no puede ser en el pasado");

        Cupon cupon = new Cupon(null, dto.getCodigo().toUpperCase(), dto.getDescuento(), true, dto.getFechaExpiracion());
        return mapToDTO(cuponRepository.save(cupon));
    }

    public Optional<CuponResponse> validarCupon(String codigo) {
        log.info("Validando cupon codigo={}", codigo);
        return cuponRepository.findByCodigo(codigo.toUpperCase()).map(cupon -> {
            if (!cupon.isActivo())
                throw new RuntimeException("El cupon esta desactivado");
            if (cupon.getFechaExpiracion().isBefore(LocalDate.now()))
                throw new RuntimeException("El cupon ha expirado");
            return mapToDTO(cupon);
        });
    }

    public Optional<CuponResponse> desactivar(Long id) {
        return cuponRepository.findById(id).map(cupon -> {
            cupon.setActivo(false);
            return mapToDTO(cuponRepository.save(cupon));
        });
    }

    public void eliminar(Long id) {
        cuponRepository.deleteById(id);
    }

}
