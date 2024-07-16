package com.backend.SolucionesW.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.SolucionesW.Models.Componentes;
import com.backend.SolucionesW.Repositories.ComponentesRepository;

@Service
public class ComponentesService {
    
    @Autowired
    private ComponentesRepository componentesRepository;

    public List<Componentes> Listar() {
        return componentesRepository.findAll();
    }
}
