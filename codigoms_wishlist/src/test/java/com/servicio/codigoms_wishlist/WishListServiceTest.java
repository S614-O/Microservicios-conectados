package com.servicio.codigoms_wishlist;

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

import com.servicio.codigoms_wishlist.client.CatalogoClient;
import com.servicio.codigoms_wishlist.dto.LibroDTO;
import com.servicio.codigoms_wishlist.dto.WishListRequest;
import com.servicio.codigoms_wishlist.dto.WishListResponse;
import com.servicio.codigoms_wishlist.model.WishList;
import com.servicio.codigoms_wishlist.repository.WishListRepository;
import com.servicio.codigoms_wishlist.service.WishListService;

@ExtendWith(MockitoExtension.class)
class WishListServiceTest {

    @Mock
    private WishListRepository wishListRepository;

    @Mock
    private CatalogoClient catalogoClient; 

    @InjectMocks
    private WishListService wishListService;

    @Test
    void agregarLibro_cuandoTodoEsValido_guardaYRetornaResponse() {

        WishListRequest request = TestDataFactory.unWishListRequest();
        LibroDTO libroSimulado = TestDataFactory.unLibroDTO(request.getLibroId());


        when(catalogoClient.buscarLibro(request.getLibroId())).thenReturn(Optional.of(libroSimulado));

        when(wishListRepository.existsByUsuarioAndLibroId(request.getUsuario(), request.getLibroId())).thenReturn(false);

        WishList wishListGuardada = TestDataFactory.unaWishListValida();
        wishListGuardada.setLibroId(libroSimulado.getId());
        wishListGuardada.setUsuario(request.getUsuario());
        when(wishListRepository.save(any(WishList.class))).thenReturn(wishListGuardada);


        WishListResponse resultado = wishListService.agregarLibro(request);


        assertThat(resultado.getUsuario()).isEqualTo(request.getUsuario());
        assertThat(resultado.getLibroId()).isEqualTo(request.getLibroId());
        verify(wishListRepository, times(1)).save(any(WishList.class));
    }

    @Test
    void agregarLibro_cuandoLibroNoExisteEnCatalogo_lanzaExcepcion() {

        WishListRequest request = TestDataFactory.unWishListRequest();
        

        when(catalogoClient.buscarLibro(request.getLibroId())).thenReturn(Optional.empty());


        assertThatThrownBy(() -> wishListService.agregarLibro(request))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("El libro con id " + request.getLibroId() + " no existe en el catálogo");


        verify(wishListRepository, never()).save(any(WishList.class));
    }

    @Test
    void obtenerPorUsuario_retornaListaDeResponse() {

        String usuarioTest = "Francisco Adrovez";
        List<WishList> listaSimulada = List.of(TestDataFactory.unaWishListValida(), TestDataFactory.unaWishListValida());
        when(wishListRepository.findByUsuario(usuarioTest)).thenReturn(listaSimulada);


        List<WishListResponse> resultado = wishListService.obtenerPorUsuario(usuarioTest);


        assertThat(resultado).hasSize(2);
        verify(wishListRepository, times(1)).findByUsuario(usuarioTest);
    }
}