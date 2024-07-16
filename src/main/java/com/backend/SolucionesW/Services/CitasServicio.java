package com.backend.SolucionesW.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.SolucionesW.Models.Citas;
import com.backend.SolucionesW.Models.Cliente;
import com.backend.SolucionesW.Repositories.CitasRepository;


@Service
public class CitasServicio{
    
    @Autowired
    private CitasRepository citasRepositorio;

    public Citas Guardar(Citas citas){
        return citasRepositorio.save(citas);
    }

    public List<Citas> listarCitas(Cliente cliente) {
        return citasRepositorio.findAllByCliente(cliente);
    }

    public List<Citas> Listar() {
        return citasRepositorio.findAll();
    }

    public void Eliminar(Integer id) {
        citasRepositorio.deleteById(id);
    }

    public Optional<Citas> obtenerCitaPorCodigo(Integer codigo) {
        return citasRepositorio.findById(codigo); 
    }

    public boolean existeId(Integer id) {
        return citasRepositorio.existsById(id);
    }
}
