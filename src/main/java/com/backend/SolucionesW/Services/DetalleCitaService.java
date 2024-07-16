package com.backend.SolucionesW.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.SolucionesW.Models.Citas;
import com.backend.SolucionesW.Models.DetalleCita;
import com.backend.SolucionesW.Models.Empleado;
import com.backend.SolucionesW.Models.EstadoCita;
import com.backend.SolucionesW.Models.Repuestos;
import com.backend.SolucionesW.Models.Servicios;
import com.backend.SolucionesW.Repositories.CitasRepository;
import com.backend.SolucionesW.Repositories.DetalleCitaRepository;
import com.backend.SolucionesW.Repositories.EstadoCitaRepository;
import com.backend.SolucionesW.Repositories.RespuestosRepository;
import com.backend.SolucionesW.Repositories.ServiciosRepository;


@Service
public class DetalleCitaService {
    
    @Autowired
    private DetalleCitaRepository detalleCitaRepository;

    @Autowired
    ServiciosRepository serviciosRepository;

    @Autowired
    EstadoCitaRepository estadoCitaRepository;
    
    @Autowired
    RespuestosRepository repuestosRepository;

    @Autowired
    CitasRepository citasRepository;

    public List<DetalleCita> Listar() {
        return detalleCitaRepository.findAll();
    }

    public Optional<DetalleCita> obtenerDetalleCitaPorCodigo(Integer codigo) {
        return detalleCitaRepository.findById(codigo); 
    }

    public DetalleCita actualizardiagnostico(Integer id, String actu, Empleado empleado){
        if(!empleado.getArea().getNombreArea().equals("Mecánica")) {
            throw new RuntimeException("El empleado no tiene acceso ");
        }

        DetalleCita detalleCita = detalleCitaRepository.findById(id).orElseThrow(()->new RuntimeException("No se encontró la cita"));

        detalleCita.setDiagnostico(actu);
        return detalleCitaRepository.save(detalleCita);
    }

    
    public DetalleCita actualizarServicios(Integer id, Integer idServicio, Empleado empleado) {
        if (!empleado.getArea().getNombreArea().equals("Mecánica")) {
            throw new RuntimeException("El empleado no tiene acceso");
        }

        DetalleCita detalleCita = detalleCitaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró la cita"));

        Servicios servicio = serviciosRepository.findById(idServicio)
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));

        detalleCita.setServicios(servicio);
        return detalleCitaRepository.save(detalleCita);
    }

    public DetalleCita actualizarRepuestos(Integer id, Integer idRepuesto, Empleado empleado) {
        if (!empleado.getArea().getNombreArea().equals("Mecánica")) {
            throw new RuntimeException("El empleado no tiene acceso");
        }

        DetalleCita detalleCita = detalleCitaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró la cita"));

        Repuestos repuesto = repuestosRepository.findById(idRepuesto)
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));

        detalleCita.setRepuestos(repuesto);
        return detalleCitaRepository.save(detalleCita);
    }

    public DetalleCita actualizarEstadoCita(Integer idDetalleCita, Integer nuevoEstadoCitaId, Empleado empleado) {
        DetalleCita detalleCita = detalleCitaRepository.findById(idDetalleCita)
                .orElseThrow(() -> new RuntimeException("Detalle de cita no encontrado"));

        Citas cita = detalleCita.getCita();

        if (!empleado.getArea().getNombreArea().equals("Asesoramiento")) {
            throw new RuntimeException("El empleado no tiene acceso");
        }

        EstadoCita nuevoEstado = estadoCitaRepository.findById(nuevoEstadoCitaId)
                .orElseThrow(() -> new RuntimeException("Estado de cita no encontrado"));

        cita.setEstadoCita(nuevoEstado);
        citasRepository.save(cita);

        return detalleCita;
    }
}
