package com.example.codigosms_usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.codigosms_usuario.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
