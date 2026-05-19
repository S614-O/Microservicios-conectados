package com.soto.codigoms_resenas.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.soto.codigoms_resenas.DTO.LibroDTO;
import com.soto.codigoms_resenas.DTO.PromedioResponse;
import com.soto.codigoms_resenas.DTO.ResenaRequest;
import com.soto.codigoms_resenas.DTO.ResenaResponse;
import com.soto.codigoms_resenas.client.CatalogoClient;
import com.soto.codigoms_resenas.model.Resenia;
import com.soto.codigoms_resenas.repository.ResenaRepository;

import feign.FeignException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResenaService {
private final ResenaRepository resenaRepository;
    private final CatalogoClient catalogoClient;

    @Transactional
    public ResenaResponse crear(ResenaRequest dto) {
        log.info("Creando reseña — libroId={}, usuario={}", dto.getLibroId(), dto.getUsuario());

        if (resenaRepository.existsByLibroIdAndUsuario(dto.getLibroId(), dto.getUsuario())) {
            throw new RuntimeException(
                "El usuario '" + dto.getUsuario() + "' ya tiene una reseña para este libro");
        }

        LibroDTO libro = verificarLibro(dto.getLibroId());

        Resenia resena = new Resenia();
        resena.setLibroId(dto.getLibroId());
        resena.setTituloLibro(libro.getTitulo());
        resena.setUsuario(dto.getUsuario());
        resena.setCalificacion(dto.getCalificacion());
        resena.setComentario(dto.getComentario());
        resena.setFechaResena(LocalDateTime.now());

        Resenia guardada = resenaRepository.save(resena);
        log.info("Reseña creada id={} para libro '{}'", guardada.getId(), libro.getTitulo());
        return mapToDTO(guardada);
    }

    public List<ResenaResponse> obtenerTodas() {
        return resenaRepository.findAll().stream().map(this::mapToDTO).toList();
    }

    public Optional<ResenaResponse> obtenerPorId(Long id) {
        return resenaRepository.findById(id).map(this::mapToDTO);
    }

    public List<ResenaResponse> obtenerPorLibro(Long libroId) {
        return resenaRepository.findByLibroIdOrderByFechaResenaDesc(libroId)
                .stream().map(this::mapToDTO).toList();
    }

    public List<ResenaResponse> obtenerPorUsuario(String usuario) {
        return resenaRepository.findByUsuario(usuario)
                .stream().map(this::mapToDTO).toList();
    }

    public PromedioResponse obtenerPromedio(Long libroId) {
        LibroDTO libro = verificarLibro(libroId);

        Double promedio = resenaRepository.promedioCalificacion(libroId);
        long total = resenaRepository.findByLibroId(libroId).size();

        double promedioFinal = promedio != null
                ? Math.round(promedio * 10.0) / 10.0
                : 0.0;

        return new PromedioResponse(libroId, libro.getTitulo(), promedioFinal, total);
    }

    @Transactional
    public void eliminar(Long id) {
        if (!resenaRepository.existsById(id)) {
            throw new RuntimeException("Reseña no encontrada con id: " + id);
        }
        resenaRepository.deleteById(id);
        log.info("Reseña id={} eliminada", id);
    }

    private LibroDTO verificarLibro(Long libroId) {
        try {
            return catalogoClient.obtenerLibro(libroId);
        } catch (FeignException.NotFound e) {
            throw new RuntimeException("El libro con id " + libroId + " no existe en el catalogo");
        } catch (FeignException e) {
            log.error("Error al contactar codigoms_catalogo: {}", e.getMessage());
            throw new RuntimeException(
                "No se pudo verificar el libro. Verifique que codigoms_catalogo este corriendo.");
        }
    }

    private ResenaResponse mapToDTO(Resenia r) {
        ResenaResponse dto = new ResenaResponse();
        dto.setId(r.getId());
        dto.setLibroId(r.getLibroId());
        dto.setTituloLibro(r.getTituloLibro());
        dto.setUsuario(r.getUsuario());
        dto.setCalificacion(r.getCalificacion());
        dto.setComentario(r.getComentario());
        dto.setFechaResena(r.getFechaResena());
        return dto;
    }

}
