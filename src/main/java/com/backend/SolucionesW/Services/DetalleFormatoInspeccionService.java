package com.backend.SolucionesW.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.SolucionesW.Models.Componentes;
import com.backend.SolucionesW.Models.DetalleFormatoInspeccion;
import com.backend.SolucionesW.Models.EstadoConservacion;
import com.backend.SolucionesW.Models.FormatoDeInspeccion;
import com.backend.SolucionesW.Repositories.ComponentesRepository;
import com.backend.SolucionesW.Repositories.DetalleFormatoInspeccionRepository;
import com.backend.SolucionesW.Repositories.EstadoConservacionRepository;
import com.backend.SolucionesW.Repositories.FormatoInspeccionRepository;

@Service
public class DetalleFormatoInspeccionService {
    
    @Autowired
    private DetalleFormatoInspeccionRepository detalleFormatoInspeccionRepository;

    @Autowired
    private FormatoInspeccionRepository formatoDeInspeccionRepository;

    @Autowired
    private ComponentesRepository componentesRepository;

    @Autowired
    private EstadoConservacionRepository estadoConservacionRepository;

    public DetalleFormatoInspeccion Guardar(DetalleFormatoInspeccion detalleFormatoInspeccion) {
        // Validar y asignar componentes si es necesario
        Componentes componentes = detalleFormatoInspeccion.getComponentes();
        if (componentes != null && componentes.getId() != null) {
            componentes = componentesRepository.findById(componentes.getId())
                    .orElseThrow(() -> new RuntimeException("Componente no encontrado"));
            detalleFormatoInspeccion.setComponentes(componentes);
        }
        
        // Validar y asignar estado de conservación si es necesario
        EstadoConservacion estadoConservacion = detalleFormatoInspeccion.getEstadoConservacion();
        if (estadoConservacion != null && estadoConservacion.getId() != null) {
            estadoConservacion = estadoConservacionRepository.findById(estadoConservacion.getId())
                    .orElseThrow(() -> new RuntimeException("Estado de Conservación no encontrado"));
            detalleFormatoInspeccion.setEstadoConservacion(estadoConservacion);
        }
        
        // Guardar el detalle de formato de inspección
        return detalleFormatoInspeccionRepository.save(detalleFormatoInspeccion);
    }
    

    public List<DetalleFormatoInspeccion> listar() {
        return detalleFormatoInspeccionRepository.findAll();
    }
}
