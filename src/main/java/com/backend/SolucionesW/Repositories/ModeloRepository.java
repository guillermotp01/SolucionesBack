package com.backend.SolucionesW.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.SolucionesW.Models.Modelo;

public interface ModeloRepository extends JpaRepository<Modelo, Integer>{
    
}
