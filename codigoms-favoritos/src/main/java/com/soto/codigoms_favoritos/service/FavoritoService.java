package com.soto.codigoms_favoritos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.soto.codigoms_favoritos.model.Favorito;
import com.soto.codigoms_favoritos.repository.FavoritoRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class FavoritoService {

    private final FavoritoRepository repository;

    public List<Favorito> obtenerTodas() { return repository.findAll(); } //obtener todos
    public Optional<Favorito> obtenerPorUsuario(String usuario) { return repository.findByusuario(usuario); } //buscar por user
    public Favorito guardar(Favorito c) { return repository.save(c); } //guadar favorito
    public void eliminar(Long id) { repository.deleteById(id); } // borrar favorito

    //FALTA : Qué usuarios marcaron ese libro



}
