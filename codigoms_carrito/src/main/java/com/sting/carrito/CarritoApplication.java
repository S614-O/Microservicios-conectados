package com.sting.carrito;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * CarritoApplication
 *
 * Autor: Prof. Sting Parra Silva
 *
 * Microservicio de carrito. Llama a codigoms_catalogo cuando
 * necesita verificar o recuperar datos de un libro.
 *
 * @EnableFeignClients activa el escaneo de interfaces @FeignClient.
 */
@SpringBootApplication
@EnableFeignClients
public class CarritoApplication {
    public static void main(String[] args) {
        SpringApplication.run(CarritoApplication.class, args);
    }
}
