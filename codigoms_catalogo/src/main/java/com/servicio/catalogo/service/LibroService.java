package com.servicio.catalogo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.servicio.catalogo.dto.LibroRequestDTO;
import com.servicio.catalogo.dto.LibroResponseDTO;
import com.servicio.catalogo.model.Categoria;
import com.servicio.catalogo.model.Libro;
import com.servicio.catalogo.repository.CategoriaRepository;
import com.servicio.catalogo.repository.LibroRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class LibroService {

    private final LibroRepository libroRepository;
    private final CategoriaRepository categoriaRepository;

    public List<LibroResponseDTO> obtenerTodos() {
        return libroRepository.findAll().stream().map(this::mapToDTO).toList();
    }

    public Optional<LibroResponseDTO> obtenerPorId(Long id) {
        log.info("Consulta de libro id={}", id);
        return libroRepository.findById(id).map(this::mapToDTO);
    }

    public LibroResponseDTO guardar(LibroRequestDTO dto) {
        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria no encontrada con el id numero: " + dto.getCategoriaId()));
        Libro libro = new Libro(null, dto.getTitulo(), dto.getIsbn(), dto.getPrecio(), categoria);
        return mapToDTO(libroRepository.save(libro));
    }

    public void eliminar(Long id) {
        libroRepository.deleteById(id);
    }

    private LibroResponseDTO mapToDTO(Libro l) {
        return new LibroResponseDTO(l.getId(), l.getTitulo(), l.getIsbn(),
                l.getPrecio(), l.getCategoria().getNombre());
    }
}
