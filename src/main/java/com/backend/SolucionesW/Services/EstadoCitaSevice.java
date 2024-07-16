package com.backend.SolucionesW.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.SolucionesW.Models.EstadoCita;
import com.backend.SolucionesW.Repositories.EstadoCitaRepository;

@Service
public class EstadoCitaSevice {

    @Autowired
    private EstadoCitaRepository estadoCitaRepository;

    public EstadoCita Obtener(Integer id) {
        return estadoCitaRepository.findById(id).orElse(null);
    }
}
