package com.backend.SolucionesW.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.SolucionesW.Models.Citas;
import com.backend.SolucionesW.Models.Cliente;

public interface CitasRepository extends JpaRepository<Citas, Integer>{
    List<Citas> findByClienteId(Integer clienteId);
    List<Citas> findAllByCliente(Cliente cliente);
    @SuppressWarnings("null")
    Optional<Citas> findById(Integer id);
    @SuppressWarnings("null")
    boolean existsById(Integer id);
}
