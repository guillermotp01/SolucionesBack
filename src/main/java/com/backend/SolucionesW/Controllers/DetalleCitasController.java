package com.backend.SolucionesW.Controllers;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.backend.SolucionesW.Services.DetalleCitaService;
import com.backend.SolucionesW.Models.DetalleCita;
import com.backend.SolucionesW.Models.Empleado;
import com.backend.SolucionesW.Repositories.EmpleadoRepository;

@RestController
@RequestMapping("/detalleCita")
@CrossOrigin("*")
public class DetalleCitasController {

    @Autowired
    private DetalleCitaService detalleCitaService;

    @Autowired
    private EmpleadoRepository empleadoRepository;


    @GetMapping("/obtener/{id}")
    public ResponseEntity<Optional<DetalleCita>> getDetalleCitaById(@PathVariable Integer id) {
        Optional<DetalleCita> detalleCita = detalleCitaService.obtenerDetalleCitaPorCodigo(id);
        return ResponseEntity.ok(detalleCita);
    }

    @PutMapping("/{idDetalleCita}/actualizardiagnostico")
    public ResponseEntity<DetalleCita> actualizarDiagnostico(
        @PathVariable Integer idDetalleCita,
        @RequestParam String nuevoDiagnostico,
        @RequestParam Integer empleadoId) {

        Empleado empleado = empleadoRepository.findById(empleadoId)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

        DetalleCita detalleCitaActualizada = detalleCitaService.actualizardiagnostico(idDetalleCita, nuevoDiagnostico, empleado);
        return ResponseEntity.ok(detalleCitaActualizada);
    }

    @PutMapping("/{idDetalleCita}/actualizarservicios")
    public ResponseEntity<DetalleCita> actualizarServicios(
            @PathVariable Integer idDetalleCita,
            @RequestParam Integer serv,
            @RequestParam Integer empleadoId) {

        Empleado empleado = empleadoRepository.findById(empleadoId)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

        DetalleCita detalleCitaActualizada = detalleCitaService.actualizarServicios(idDetalleCita, serv, empleado);
        return ResponseEntity.ok(detalleCitaActualizada);
            }


    @PutMapping("/{idDetalleCita}/actualizarrepuestos")
    public ResponseEntity<DetalleCita> actualizarRepuestos(
            @PathVariable Integer idDetalleCita,
            @RequestParam Integer rep,
            @RequestParam Integer empleadoId){

            Empleado empleado = empleadoRepository.findById(empleadoId)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

                DetalleCita detalleCitaActualizada = detalleCitaService.actualizarRepuestos(idDetalleCita, rep, empleado);
                return ResponseEntity.ok(detalleCitaActualizada);
        }
    
        @PutMapping("/{idDetalleCita}/estado")
        public ResponseEntity<DetalleCita> actualizarEstadoCita(
                @PathVariable Integer idDetalleCita,
                @RequestParam Integer nuevoEstadoCitaId,
                @RequestParam Integer empleadoId) {
    
            Empleado empleado = empleadoRepository.findById(empleadoId)
                    .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
    
            DetalleCita detalleCitaActualizada = detalleCitaService.actualizarEstadoCita(idDetalleCita, nuevoEstadoCitaId, empleado);
            return ResponseEntity.ok(detalleCitaActualizada);
        }
}
