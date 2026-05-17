package com.servicio.catalogo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * CatalogApplication
 *
 * Autor: Prof. Sting Parra Silva
 *
 * Microservicio de catalogo. Expone informacion de categorias y libros
 * al resto del sistema via HTTP. No llama a nadie — solo recibe llamadas.
 *
 * Sistema completo (tres proyectos corriendo al mismo tiempo):
 *   codigoms_catalogo  → puerto 8082  ← este proyecto
 *   codigoms_carrito   → puerto 8083  (llama a catalogo)
 *   codigoms_pedidos   → puerto 8081  (llama a catalogo)
 */
@SpringBootApplication
public class CatalogApplication {
    public static void main(String[] args) {
        SpringApplication.run(CatalogApplication.class, args);
    }
}
