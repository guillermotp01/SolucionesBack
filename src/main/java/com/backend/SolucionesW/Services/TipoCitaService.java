package com.backend.SolucionesW.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.SolucionesW.Models.TipoCita;
import com.backend.SolucionesW.Repositories.TipoCitaRepository;

@Service
public class TipoCitaService {
    
    @Autowired
    private TipoCitaRepository tipoCitaRepository;

    public List<TipoCita> Listar() {
        return tipoCitaRepository.findAll();
    }
}
