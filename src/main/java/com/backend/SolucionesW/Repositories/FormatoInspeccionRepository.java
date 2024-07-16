package com.backend.SolucionesW.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.SolucionesW.Models.FormatoDeInspeccion;

public interface FormatoInspeccionRepository extends JpaRepository<FormatoDeInspeccion, Integer>{
    
}
