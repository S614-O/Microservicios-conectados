package com.servicio.codigoms_favoritos;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.servicio.codigoms_favoritos.client.CatalogoClient;
import com.servicio.codigoms_favoritos.dto.FavoritoRequestDTO;
import com.servicio.codigoms_favoritos.dto.LibroDTO;
import com.servicio.codigoms_favoritos.model.Favorito;
import com.servicio.codigoms_favoritos.repository.FavoritoRepository;
import com.servicio.codigoms_favoritos.service.FavoritoService;

@ExtendWith(MockitoExtension.class)
class FavoritoServiceTest {

    @Mock private FavoritoRepository favoritoRepository;
    @Mock private CatalogoClient catalogoClient;
    @InjectMocks private FavoritoService favoritoService;

    @Test
    void agregar_cuandoEsValido_guardaFavorito() {
        FavoritoRequestDTO request = TestDataFactory.unRequest();
        LibroDTO libro = TestDataFactory.unLibroDTO(request.getLibroId());

        when(catalogoClient.buscarLibro(request.getLibroId())).thenReturn(Optional.of(libro));
        when(favoritoRepository.existsByLibroIdAndUsuario(any(), any())).thenReturn(false);
        when(favoritoRepository.save(any(Favorito.class))).thenReturn(TestDataFactory.unFavoritoValido());

        var result = favoritoService.agregar(request);

        assertThat(result).isNotNull();
        verify(favoritoRepository, times(1)).save(any(Favorito.class));
    }

    @Test
    void agregar_cuandoLibroNoExiste_lanzaExcepcion() {
        FavoritoRequestDTO request = TestDataFactory.unRequest();
        when(catalogoClient.buscarLibro(request.getLibroId())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> favoritoService.agregar(request))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("no existe en el catálogo");
    }
}