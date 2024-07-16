package com.backend.SolucionesW.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.SolucionesW.Models.EstadoConservacion;

public interface EstadoConservacionRepository extends JpaRepository<EstadoConservacion, Integer>{
    
}
