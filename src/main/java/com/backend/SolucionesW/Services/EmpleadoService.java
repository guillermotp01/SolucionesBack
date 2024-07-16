package com.backend.SolucionesW.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.SolucionesW.Models.Empleado;
import com.backend.SolucionesW.Repositories.EmpleadoRepository;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    public List<Empleado> Listar() {
        return empleadoRepository.findAll();
    }

    public Empleado Guardar(Empleado u) {
        return empleadoRepository.save(u);
    }
    
    public Empleado Obtener(Integer id) {
        return empleadoRepository.findById(id).orElse(null);
    }

    public Empleado obtenerUsuario(String username) {
        return empleadoRepository.findByUsername(username);
    }
}
