package com.backend.SolucionesW.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.SolucionesW.Models.EstadoCita;

public interface EstadoCitaRepository extends JpaRepository<EstadoCita,Integer>{
    @SuppressWarnings("null")
    Optional<EstadoCita> findById(Integer id);
}
