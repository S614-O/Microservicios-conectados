package com.example.codigosms_usuario.service;

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
        



        

}
