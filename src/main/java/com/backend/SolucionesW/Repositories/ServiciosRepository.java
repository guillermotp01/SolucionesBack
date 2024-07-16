package com.backend.SolucionesW.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.backend.SolucionesW.Models.Servicios;

import java.util.Optional;

public interface ServiciosRepository extends JpaRepository<Servicios, Integer> {
    @SuppressWarnings("null")
    Optional<Servicios> findById(Integer id);
}