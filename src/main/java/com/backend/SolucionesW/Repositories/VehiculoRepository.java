package com.backend.SolucionesW.Repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.SolucionesW.Models.Cliente;
import com.backend.SolucionesW.Models.Vehiculo;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Integer>{
    List<Vehiculo> findByClienteId(Integer clienteId);
    List<Vehiculo> findAllByCliente(Cliente cliente);
}
