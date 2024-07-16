package com.backend.SolucionesW.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.SolucionesW.Models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    public Cliente findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByNombre(String nombre);
}
