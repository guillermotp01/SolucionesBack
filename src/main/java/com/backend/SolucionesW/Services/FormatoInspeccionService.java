package com.backend.SolucionesW.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.SolucionesW.Models.FormatoDeInspeccion;
import com.backend.SolucionesW.Repositories.FormatoInspeccionRepository;

@Service
public class FormatoInspeccionService {
    
    @Autowired
    private FormatoInspeccionRepository formatoInspeccionRepository;

    public List<FormatoDeInspeccion> Listar() {
        return formatoInspeccionRepository.findAll();
    }

    public FormatoDeInspeccion Guardar(FormatoDeInspeccion formato) {
        return formatoInspeccionRepository.save(formato);
    }
}
