package com.example.codigosms_usuario.client;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.codigosms_usuario.model.Usuario;
import com.example.codigosms_usuario.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args){
        if (usuarioRepository.count()>0){
            log.info(">>> DataInitializer: datos ya existentes, se omite carga");
            return;
        }


    
    usuarioRepository.save(Usuario.builder()
    .nombreUsuario("PepitoPro")
    .nombreReal("Pedro Pérez")
    .rut("12345678-9")
    .build());

    usuarioRepository.save(Usuario.builder()
    .nombreUsuario("JuanitoGamer777")        
    .nombreReal("Juanito Pollo")
    .rut("27777999-2")
    .build());

    log.info("Data Initializer: Usuarios Añadidos Correctamente");

}


}
