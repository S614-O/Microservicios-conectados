package com.soto.codigoms_resenas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.soto.codigoms_resenas.model.Resenia;





public interface ResenaRepository extends JpaRepository<Resenia,Long>{
    List<Resenia> findByLibroId(Long libroId);

    List<Resenia> findByUsuario(String usuario);

    List<Resenia> findByLibroIdOrderByFechaResenaDesc(Long libroId);

    boolean existsByLibroIdAndUsuario(Long libroId, String usuario);

    @Query("SELECT AVG(r.calificacion) FROM Resenia r WHERE r.libroId = :libroId")
    Double promedioCalificacion(@Param("libroId") Long libroId);
}


