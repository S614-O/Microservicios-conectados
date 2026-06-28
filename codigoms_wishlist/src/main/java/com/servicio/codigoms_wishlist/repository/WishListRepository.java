package com.servicio.codigoms_wishlist.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servicio.codigoms_wishlist.model.EstadosWishList;
import com.servicio.codigoms_wishlist.model.WishList;

public interface WishListRepository extends JpaRepository<WishList, Long> {

    List<WishList> findByUsuario(String usuario);

List<WishList> findByUsuarioAndEstado(String usuario, EstadosWishList estado);

    boolean existsByUsuarioAndLibroId(String usuario, Long libroId);

    Optional<WishList> findByUsuarioAndLibroId(String usuario, Long libroId);



}
