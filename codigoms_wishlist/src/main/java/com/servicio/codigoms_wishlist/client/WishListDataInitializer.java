package com.servicio.codigoms_wishlist.client;


import com.servicio.codigoms_wishlist.model.WishList;
import com.servicio.codigoms_wishlist.repository.WishListRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class WishListDataInitializer implements CommandLineRunner {
    private final WishListRepository wishListRepository;

    @Override
    public void run(String... args) {
        if (wishListRepository.count() > 0) {
            log.info(">>> DataInitializer: items de wishlist ya existentes, se omite carga");
            return;
        }

        wishListRepository.save(WishList.builder()
                .libroId(1L)
                .usuario("Panchosky")
                .nota("Me interesa")
                .build());

        wishListRepository.save(WishList.builder()
                .libroId(2L)
                .usuario("PepitoPro")
                .nota("Me interesa")
                .build());

        log.info(">>> DataInitializer: WishList de prueba añadida correctamente");
    }


}
