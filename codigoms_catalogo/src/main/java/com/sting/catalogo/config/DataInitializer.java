package com.sting.catalogo.config;

import com.sting.catalogo.model.Categoria;
import com.sting.catalogo.model.Libro;
import com.sting.catalogo.repository.CategoriaRepository;
import com.sting.catalogo.repository.LibroRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final CategoriaRepository categoriaRepository;
    private final LibroRepository libroRepository;

    @Override
    public void run(String... args) {
        if (categoriaRepository.count() > 0) {
            log.info(">>> DataInitializer: datos ya existentes, se omite carga.");
            return;
        }

        Categoria prog = categoriaRepository.save(new Categoria(null, "Programacion", "Libros de lenguajes y frameworks"));
        Categoria bd   = categoriaRepository.save(new Categoria(null, "Bases de Datos", "SQL, NoSQL y diseno de datos"));

        libroRepository.save(new Libro(null, "Clean Code",             "978-01", new BigDecimal("45.99"), prog));
        libroRepository.save(new Libro(null, "Spring Boot in Action",  "978-02", new BigDecimal("52.00"), prog));
        libroRepository.save(new Libro(null, "MySQL Avanzado",         "978-03", new BigDecimal("38.50"), bd));
        libroRepository.save(new Libro(null, "NoSQL Distilled",        "978-04", new BigDecimal("29.99"), bd));

        log.info(">>> DataInitializer: {} categorias y {} libros insertados.",
                categoriaRepository.count(), libroRepository.count());
    }
}
