package com.servicio.codigoms_anuncios;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.servicio.codigoms_anuncios.client.LibroClient;
import com.servicio.codigoms_anuncios.dto.AnuncioRequest;
import com.servicio.codigoms_anuncios.dto.AnuncioResponse;
import com.servicio.codigoms_anuncios.dto.LibroDTO;
import com.servicio.codigoms_anuncios.model.Anuncio;
import com.servicio.codigoms_anuncios.repository.AnuncioRepository;
import com.servicio.codigoms_anuncios.service.AnuncioService;

import feign.FeignException;
import jakarta.persistence.EntityNotFoundException;

@ExtendWith(MockitoExtension.class)
@DisplayName("AnuncioService - Pruebas Unitarias")
class CodigomsAnunciosApplicationTests {
	@Mock
    private AnuncioRepository anuncioRepository;

    @Mock
    private LibroClient libroClient;

    @InjectMocks
    private AnuncioService anuncioService;
	@Test
    @DisplayName("crearAnuncio: registra el anuncio y genera el texto correcto cuando el libro existe")
    void crearAnuncio_libroExistente_guardaAnuncioCorrectamente() {
    
        Long libroId = 10L;
        LibroDTO libroMock = TestDataFactory.unLibroDTO(libroId, 25000.0);
        AnuncioRequest request = TestDataFactory.unAnuncioRequest(libroId);
        Anuncio anuncioGuardado = TestDataFactory.unAnuncio(libroId, libroMock.getTitulo(), libroMock.getPrecio());

        when(libroClient.obtenerLibro(libroId)).thenReturn(libroMock);
        when(anuncioRepository.save(any(Anuncio.class))).thenReturn(anuncioGuardado);

 
        AnuncioResponse resultado = anuncioService.crearAnuncio(request);


        assertThat(resultado).isNotNull();
        assertThat(resultado.getLibroId()).isEqualTo(libroId);
        assertThat(resultado.getTituloLibro()).isEqualTo(libroMock.getTitulo());
        assertThat(resultado.getPrecioLibro()).isEqualByComparingTo(libroMock.getPrecio());
        assertThat(resultado.getTextoAnuncio())
                .contains("¡Este libro llamado " + libroMock.getTitulo())
                .contains("$" + libroMock.getPrecio());

        verify(libroClient).obtenerLibro(libroId);
        verify(anuncioRepository).save(any(Anuncio.class));
    }

    @Test
    @DisplayName("crearAnuncio: lanza excepción cuando el cliente Feign no encuentra el libro")
    void crearAnuncio_libroNoExiste_lanzaFeignException() {

        Long libroId = 99L;
        AnuncioRequest request = TestDataFactory.unAnuncioRequest(libroId);

        FeignException.NotFound feignException = mock(FeignException.NotFound.class);
        when(libroClient.obtenerLibro(libroId)).thenThrow(feignException);


        assertThatThrownBy(() -> anuncioService.crearAnuncio(request))
                .isInstanceOf(FeignException.NotFound.class);

        verify(anuncioRepository, never()).save(any(Anuncio.class));
    }

    @Test
    @DisplayName("listarAnuncios: retorna una página de anuncios mapeados a DTO")
    void listarAnuncios_retornaPaginaDeAnunciosMapeados() {

        Pageable pageable = PageRequest.of(0, 5);
        Anuncio a1 = TestDataFactory.unAnuncio(1L, "Clean Code", new BigDecimal("45.99"));
        Anuncio a2 = TestDataFactory.unAnuncio(2L, "Design Patterns", new BigDecimal("55.00"));
        Page<Anuncio> paginaMock = new PageImpl<>(List.of(a1, a2), pageable, 2);

        when(anuncioRepository.findAll(pageable)).thenReturn(paginaMock);


        Page<AnuncioResponse> resultado = anuncioService.listarAnuncios(pageable);


        assertThat(resultado).hasSize(2);
        assertThat(resultado.getContent()).extracting(AnuncioResponse::getTituloLibro)
                .containsExactly("Clean Code", "Design Patterns");
        

        assertThat(resultado.getContent().get(0).getTextoAnuncio())
                .isEqualTo("¡Este libro de ID 1 está a un precio imperdible $45.99!!");

        verify(anuncioRepository).findAll(pageable);
    }

    @Test
    @DisplayName("obtenerAnuncioPorId: devuelve el anuncio convertido a DTO cuando el ID existe")
    void obtenerAnuncioPorId_idExistente_retornaAnuncioResponse() {

        Long anuncioId = 1L;
        Anuncio anuncio = TestDataFactory.unAnuncio(5L, "Refactoring", new BigDecimal("60.00"));
        anuncio.setId(anuncioId);

        when(anuncioRepository.findById(anuncioId)).thenReturn(Optional.of(anuncio));


        AnuncioResponse resultado = anuncioService.obtenerAnuncioPorId(anuncioId);


        assertThat(resultado).isNotNull();
        assertThat(resultado.getId()).isEqualTo(anuncioId);
        assertThat(resultado.getLibroId()).isEqualTo(5L);
        verify(anuncioRepository).findById(anuncioId);
    }

    @Test
    @DisplayName("obtenerAnuncioPorId: lanza EntityNotFoundException si el ID no existe")
    void obtenerAnuncioPorId_idInexistente_lanzaEntityNotFoundException() {

        Long idInexistente = 999L;
        when(anuncioRepository.findById(idInexistente)).thenReturn(Optional.empty());


        assertThatThrownBy(() -> anuncioService.obtenerAnuncioPorId(idInexistente))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Anuncio con ID " + idInexistente + " no fue encontrado");
    }

    @Test
    @DisplayName("eliminarAnuncio: elimina el registro cuando el ID del anuncio existe")
    void eliminarAnuncio_idExistente_eliminaCorrectamente() {
        // Arrange
        Long idEliminar = 2L;
        when(anuncioRepository.existsById(idEliminar)).thenReturn(true);
        doNothing().when(anuncioRepository).deleteById(idEliminar);


        anuncioService.eliminarAnuncio(idEliminar);


        verify(anuncioRepository).existsById(idEliminar);
        verify(anuncioRepository).deleteById(idEliminar);
    }

    @Test
    @DisplayName("eliminarAnuncio: lanza EntityNotFoundException si el anuncio a eliminar no existe")
    void eliminarAnuncio_idInexistente_lanzaEntityNotFoundException() {

        Long idInexistente = 888L;
        when(anuncioRepository.existsById(idInexistente)).thenReturn(false);

  
        assertThatThrownBy(() -> anuncioService.eliminarAnuncio(idInexistente))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("No se puede eliminar: El anuncio con ID " + idInexistente + " no existe");

        verify(anuncioRepository, never()).deleteById(anyLong());
    }
}
