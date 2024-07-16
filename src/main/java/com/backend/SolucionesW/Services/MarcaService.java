package com.backend.SolucionesW.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.SolucionesW.Models.Marca;
import com.backend.SolucionesW.Repositories.MarcaRepository;

@Service
public class MarcaService {
    @Autowired
    private MarcaRepository marcaRepository;

    public List<Marca> Listar() {
        return marcaRepository.findAll();
    }
}
