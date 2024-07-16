package com.backend.SolucionesW.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.SolucionesW.Models.EstadoConservacion;
import com.backend.SolucionesW.Repositories.EstadoConservacionRepository;

@Service
public class EstadoConservacionService {
    
    @Autowired
    private EstadoConservacionRepository estadoConservacionRepository;

    public List<EstadoConservacion> Listar() {
        return estadoConservacionRepository.findAll();
    }
}
