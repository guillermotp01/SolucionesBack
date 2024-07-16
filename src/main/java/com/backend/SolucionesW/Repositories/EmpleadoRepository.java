package com.backend.SolucionesW.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.SolucionesW.Models.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {
    public Empleado findByUsername(String username);
}
