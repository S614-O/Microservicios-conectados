package com.servicio.codigoms_favoritos.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.servicio.codigoms_favoritos.client.CatalogoClient;
import com.servicio.codigoms_favoritos.dto.FavoritoRequestDTO;
import com.servicio.codigoms_favoritos.dto.FavoritoResponseDTO;
import com.servicio.codigoms_favoritos.dto.LibroDTO;
import com.servicio.codigoms_favoritos.model.Favorito;
import com.servicio.codigoms_favoritos.repository.FavoritoRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class FavoritoService {

    private final FavoritoRepository favoritoRepository;
    private final CatalogoClient catalogoClient;

    @Transactional
    public FavoritoResponseDTO agregar(FavoritoRequestDTO dto) {
        LibroDTO libro = catalogoClient.buscarLibro(dto.getLibroId())
                .orElseThrow(() -> new RuntimeException(
                        "El libro con id " + dto.getLibroId() + " no existe en el catálogo"));

        if (favoritoRepository.existsByLibroIdAndUsuario(dto.getLibroId(), dto.getUsuario())) {
            throw new RuntimeException(
                    "El usuario " + dto.getUsuario() + " ya tiene ese libro en favoritos");
        }

        Favorito favorito = new Favorito();
        favorito.setLibroId(dto.getLibroId());
        favorito.setTituloLibro(libro.getTitulo());
        favorito.setUsuario(dto.getUsuario());
        favorito.setFechaAgregado(LocalDateTime.now());

        Favorito guardado = favoritoRepository.save(favorito);
        log.info("Favorito agregado: '{}' para usuario {}", libro.getTitulo(), dto.getUsuario());
        return toResponse(guardado);
    }

    public List<FavoritoResponseDTO> listarTodos() {
        return favoritoRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public List<FavoritoResponseDTO> listarPorUsuario(String usuario) {
        return favoritoRepository.findByUsuario(usuario).stream()
                .map(this::toResponse)
                .toList();
    }

    public List<FavoritoResponseDTO> listarPorLibro(Long libroId) {
        return favoritoRepository.findByLibroId(libroId).stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional
    public void eliminar(Long id) {
        Favorito favorito = favoritoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Favorito con id " + id + " no encontrado"));
        favoritoRepository.delete(favorito);
        log.info("Favorito eliminado: id {}", id);
    }

    private FavoritoResponseDTO toResponse(Favorito favorito) {
        FavoritoResponseDTO dto = new FavoritoResponseDTO();
        dto.setId(favorito.getId());
        dto.setLibroId(favorito.getLibroId());
        dto.setTituloLibro(favorito.getTituloLibro());
        dto.setUsuario(favorito.getUsuario());
        dto.setFechaAgregado(favorito.getFechaAgregado());
        return dto;
    }
}
