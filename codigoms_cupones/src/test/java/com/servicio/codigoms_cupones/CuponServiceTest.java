package com.servicio.codigoms_cupones;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.servicio.codigoms_cupones.dto.CuponRequest;
import com.servicio.codigoms_cupones.dto.CuponResponse;
import com.servicio.codigoms_cupones.model.Cupon;
import com.servicio.codigoms_cupones.repository.CuponRepository;
import com.servicio.codigoms_cupones.service.CuponService;

@ExtendWith(MockitoExtension.class)
class CuponServiceTest {

    @Mock
    private CuponRepository cuponRepository;

    @InjectMocks
    private CuponService cuponService;

    @Test
    void obtenerTodos_devuelveListaDeCuponResponse() {
        
        List<Cupon> cupones = List.of(TestDataFactory.unCuponValido(), TestDataFactory.unCuponValido());
        when(cuponRepository.findAll()).thenReturn(cupones);

        
        List<CuponResponse> resultado = cuponService.obtenerTodos();

        
        assertThat(resultado).hasSize(2);
        verify(cuponRepository, times(1)).findAll();
    }

    @Test
    void guardar_cuandoDatosSonValidos_retornaCuponResponse() {
        
        CuponRequest request = TestDataFactory.unCuponRequest();
        when(cuponRepository.existsByCodigo(request.getCodigo().toUpperCase())).thenReturn(false);
        
        Cupon cuponGuardado = TestDataFactory.unCuponValido();
        cuponGuardado.setCodigo(request.getCodigo());
        when(cuponRepository.save(any(Cupon.class))).thenReturn(cuponGuardado);

        
        CuponResponse resultado = cuponService.guardar(request);

        
        assertThat(resultado.getCodigo()).isEqualTo(request.getCodigo());
        verify(cuponRepository, times(1)).save(any(Cupon.class));
    }

    @Test
    void guardar_cuandoCodigoYaExiste_lanzaExcepcion() {
        
        CuponRequest request = TestDataFactory.unCuponRequest();
        when(cuponRepository.existsByCodigo(request.getCodigo().toUpperCase())).thenReturn(true);

        
        assertThatThrownBy(() -> cuponService.guardar(request))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Ya existe un cupon con el codigo: " + request.getCodigo());
                
        verify(cuponRepository, never()).save(any(Cupon.class));
    }

    @Test
    void validarCupon_cuandoEsInactivo_lanzaExcepcion() {
        
        Cupon cuponInactivo = TestDataFactory.unCuponInactivo();
        when(cuponRepository.findByCodigo(cuponInactivo.getCodigo())).thenReturn(Optional.of(cuponInactivo));

        
        assertThatThrownBy(() -> cuponService.validarCupon(cuponInactivo.getCodigo()))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("El cupon esta desactivado");
    }

    @Test
    void eliminar_ejecutaDeleteByIdEnElRepositorio() {
        
        cuponService.eliminar(1L);

        
        verify(cuponRepository, times(1)).deleteById(1L);
    }
}