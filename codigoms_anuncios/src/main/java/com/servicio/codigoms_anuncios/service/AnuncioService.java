package com.servicio.codigoms_anuncios.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.servicio.codigoms_anuncios.client.LibroClient;
import com.servicio.codigoms_anuncios.dto.AnuncioRequest;
import com.servicio.codigoms_anuncios.dto.AnuncioResponse;
import com.servicio.codigoms_anuncios.dto.LibroDTO;
import com.servicio.codigoms_anuncios.model.Anuncio;
import com.servicio.codigoms_anuncios.repository.AnuncioRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AnuncioService {
    private final AnuncioRepository anuncioRepository;
    private final LibroClient libroClient;

    @Transactional
    public AnuncioResponse crearAnuncio(AnuncioRequest request){
        LibroDTO libro = libroClient.obtenerLibro(request.getLibroId());
        
        Anuncio a =  Anuncio.builder()
        .libroId(request.getLibroId())
        .precioLibro(libro.getPrecio())
        .tituloLibro(libro.getTitulo())
        .build();

        Anuncio aGuardado = anuncioRepository.save(a) ;
        String frase = "¡Este libro llamado " + aGuardado.getTituloLibro() +  " está a un precio imperdible $" + aGuardado.getPrecioLibro() + "!!";

        return AnuncioResponse.builder()
        .id(aGuardado.getId())
        .libroId(aGuardado.getLibroId())
        .tituloLibro(aGuardado.getTituloLibro())
        .precioLibro(aGuardado.getPrecioLibro())
        .activo(aGuardado.isActivo())
        .textoAnuncio(frase)
        .build();


    }

    public Page<AnuncioResponse> listarAnuncios (Pageable pageable){
        Page<Anuncio> page = anuncioRepository.findAll(pageable);
        return page.map(this::mapToDTO);        
    }



public AnuncioResponse mapToDTO(Anuncio a){
if (a == null) return null;

    // Generamos la frase estricta usando variables locales
    String texto = "¡Este libro de ID " + a.getLibroId() + " está a un precio imperdible $" + a.getPrecioLibro() + "!!";

    AnuncioResponse dto = new AnuncioResponse();
    dto.setId(a.getId());
    dto.setLibroId(a.getLibroId());
    dto.setTituloLibro(a.getTituloLibro());
    dto.setPrecioLibro(a.getPrecioLibro());
    dto.setActivo(a.isActivo());
    dto.setTextoAnuncio(texto);

    return dto;

}


@Transactional
    public void eliminarAnuncio(Long id) {
        if (!anuncioRepository.existsById(id)) {
            throw new jakarta.persistence.EntityNotFoundException(
                "No se puede eliminar: El anuncio con ID " + id + " no existe"
            );
        }
        anuncioRepository.deleteById(id);
        log.info("Anuncio eliminado exitosamente con id={}", id);
    }

public AnuncioResponse obtenerAnuncioPorId(Long id) {
        return anuncioRepository.findById(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new jakarta.persistence.EntityNotFoundException(
                    "Anuncio con ID " + id + " no fue encontrado"
                ));
    }


}
