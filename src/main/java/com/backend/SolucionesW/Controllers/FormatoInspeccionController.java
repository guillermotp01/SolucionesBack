package com.backend.SolucionesW.Controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.backend.SolucionesW.Models.Cliente;
import com.backend.SolucionesW.Models.Empleado;
import com.backend.SolucionesW.Models.FormatoDeInspeccion;
import com.backend.SolucionesW.Services.EmpleadoService;
import com.backend.SolucionesW.Services.FormatoInspeccionService;



@RestController
@RequestMapping("/formatoInspeccion")
@CrossOrigin("*")
public class FormatoInspeccionController {
    
    @Autowired
    private FormatoInspeccionService formatoInspeccionService;

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping("/listar")
    public ArrayList<FormatoDeInspeccion> listar() {
        return (ArrayList<FormatoDeInspeccion>) formatoInspeccionService.Listar();
    }
    
    @PostMapping("/registrar")
    public ResponseEntity<?> registrarFormatoInspeccion(@RequestBody FormatoDeInspeccion formato, Authentication authentication) {
        if (authentication.getPrincipal() instanceof Empleado) {
            Integer empleadoId = ((Empleado) authentication.getPrincipal()).getId();

            if (formato == null) {
                Map<String, Object> response = new HashMap<>();
                response.put("success", false);
                response.put("message", "Necesita ingresar los datos");
                return ResponseEntity.badRequest().body(response);
            }

            try {
                // Asocia el empleado con el formato de inspección
                Empleado empleado = empleadoService.Obtener(empleadoId);
                if (empleado == null) {
                    Map<String, Object> response = new HashMap<>();
                    response.put("success", false);
                    response.put("message", "No se encontró al empleado con ID: " + empleadoId);
                    return ResponseEntity.badRequest().body(response);
                }

                formato.setEmpleado(empleado);

                // Guarda el formato de inspección
                FormatoDeInspeccion formatoGuardado = formatoInspeccionService.Guardar(formato);

                Map<String, Object> response = new HashMap<>();
                response.put("success", true);
                response.put("message", "Formato de inspección registrado correctamente");
                response.put("formatoId", formatoGuardado.getId());

                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            } catch (Exception e) {
                Map<String, Object> response = new HashMap<>();
                response.put("success", false);
                response.put("message", "Error al registrar el formato de inspección: " + e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Acceso denegado. Solo los empleados pueden registrar un formato de inspección.");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        }
    }
}
