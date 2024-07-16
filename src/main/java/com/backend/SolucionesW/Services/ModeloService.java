package com.backend.SolucionesW.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.SolucionesW.Models.Modelo;
import com.backend.SolucionesW.Repositories.ModeloRepository;

@Service
public class ModeloService {
    
    @Autowired
    private ModeloRepository modeloRepository;

    public List<Modelo> Listar() {
        return modeloRepository.findAll();
    }
    
}
