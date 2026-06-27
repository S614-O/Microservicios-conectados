package com.soto.codigoms_resenas;

import com.soto.codigoms_resenas.DTO.ResenaRequest;
import com.soto.codigoms_resenas.DTO.ResenaResponse;
import com.soto.codigoms_resenas.client.CatalogoClient;
import com.soto.codigoms_resenas.model.Resenia;
import com.soto.codigoms_resenas.repository.ResenaRepository;
import com.soto.codigoms_resenas.service.ResenaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ResenaServiceTest {

    @Mock
    private ResenaRepository resenaRepository;

    @Mock
    private CatalogoClient catalogoClient;

    @InjectMocks
    private ResenaService resenaService;

    @Test
    void crear_cuandoEsValida_retornaResponse() {

        ResenaRequest request = TestDataFactory.unaResenaRequest();
        when(resenaRepository.existsByLibroIdAndUsuario(request.getLibroId(), request.getUsuario())).thenReturn(false);
        when(catalogoClient.obtenerLibro(request.getLibroId())).thenReturn(TestDataFactory.unLibroDTO(request.getLibroId()));
        
        Resenia guardada = TestDataFactory.unaReseniaValida();
        when(resenaRepository.save(any(Resenia.class))).thenReturn(guardada);

        ResenaResponse result = resenaService.crear(request);
    
        assertThat(result).isNotNull();
        verify(resenaRepository, times(1)).save(any(Resenia.class));
    }

    @Test
    void crear_cuandoYaExiste_lanzaExcepcion() {

        ResenaRequest request = TestDataFactory.unaResenaRequest();
        when(resenaRepository.existsByLibroIdAndUsuario(request.getLibroId(), request.getUsuario())).thenReturn(true);

    
        assertThatThrownBy(() -> resenaService.crear(request))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("ya tiene una reseña");
    }

@Test
    void obtenerPromedio_retornaPromedioCalculado() {
        Long libroId = 1L;
        when(catalogoClient.obtenerLibro(libroId)).thenReturn(TestDataFactory.unLibroDTO(libroId));
        when(resenaRepository.promedioCalificacion(libroId)).thenReturn(4.5);
        when(resenaRepository.findByLibroId(libroId)).thenReturn(List.of(new Resenia(), new Resenia()));

        var resultado = resenaService.obtenerPromedio(libroId);

        assertThat(resultado.getPromedio()).isEqualTo(4.5);
        assertThat(resultado.getTotalResenas()).isEqualTo(2);
        verify(resenaRepository, times(1)).promedioCalificacion(libroId);
    }

    @Test
    void eliminar_cuandoNoExiste_lanzaExcepcion() {
        Long id = 99L;
        when(resenaRepository.existsById(id)).thenReturn(false);


        assertThatThrownBy(() -> resenaService.eliminar(id))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Reseña no encontrada con id: " + id);
        verify(resenaRepository, never()).deleteById(any());
    }
}