package com.example.codigosms_usuario.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.codigosms_usuario.dto.UsuarioRequest;
import com.example.codigosms_usuario.dto.UsuarioResponse;
import com.example.codigosms_usuario.model.Usuario;
import com.example.codigosms_usuario.repository.UsuarioRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor

public class UsuarioService {

        private final UsuarioRepository usuarioRepository;

        private UsuarioResponse mapADTO(Usuario usuario){
            return UsuarioResponse.builder()
            .usuarioId(usuario.getId())
            .nombreUsuario(usuario.getNombreUsuario())
            .nombreReal(usuario.getNombreReal())
            .rut(usuario.getRut())
            .fechaCreacion(usuario.getFechaCreacion())
            .build();
        }
        
        @Transactional
        public UsuarioResponse crearUsuario (UsuarioRequest dto){
            log.info("Creando Usuario....",dto.getNombreUsuario());
          
            Usuario u = new Usuario();
            u.setNombreReal(dto.getNombreReal());
            u.setNombreUsuario(dto.getNombreUsuario());
            u.setRut(dto.getRut());

            Usuario uGuardado = usuarioRepository.save(u);
            
            return mapADTO(uGuardado) ;   
        
        }
        


        public Page<UsuarioResponse> listarUsuarios (Pageable pageable){
            Page<Usuario> page= usuarioRepository.findAll(pageable);
            return page.map(this::mapADTO);
        }

@Transactional
    public void eliminarUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new jakarta.persistence.EntityNotFoundException(
                "No se puede eliminar: El anuncio con ID " + id + " no existe"
            );
        }
        usuarioRepository.deleteById(id);
        log.info("Anuncio eliminado exitosamente con id={}", id);
    }


        
public UsuarioResponse obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id)
                .map(this::mapADTO)
                .orElseThrow(() -> new jakarta.persistence.EntityNotFoundException(
                    "Anuncio con ID " + id + " no fue encontrado"
                ));
    }






}
