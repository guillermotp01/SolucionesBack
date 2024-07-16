package com.backend.SolucionesW.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.SolucionesW.Models.Marca;

public interface MarcaRepository extends JpaRepository<Marca, Integer>{
    
}
