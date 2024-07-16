package com.backend.SolucionesW.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.SolucionesW.Models.Cliente;
import com.backend.SolucionesW.Models.Vehiculo;
import com.backend.SolucionesW.Repositories.VehiculoRepository;

@Service
public class VehiculoServicio {

    @Autowired
    private VehiculoRepository vehiculoRepositorio;
    
    public Vehiculo Guardar(Vehiculo vehiculo){
        return vehiculoRepositorio.save(vehiculo);
    }
    
    public List<Vehiculo> listarVehiculos(Cliente cliente) {
        return vehiculoRepositorio.findAllByCliente(cliente);
    }

    public void Eliminar(Integer id) {
        vehiculoRepositorio.deleteById(id);
    }

    public Vehiculo Obtener(Integer id) {
        return vehiculoRepositorio.findById(id).orElse(null);
    }
}
