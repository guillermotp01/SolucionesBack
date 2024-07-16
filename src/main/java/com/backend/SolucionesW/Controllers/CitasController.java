package com.backend.SolucionesW.Controllers;

import org.springframework.web.bind.annotation.*;
import com.backend.SolucionesW.Models.Citas;
import com.backend.SolucionesW.Models.Cliente;
import com.backend.SolucionesW.Models.EstadoCita;
import com.backend.SolucionesW.Models.TipoCita;
import com.backend.SolucionesW.Models.Vehiculo;
import com.backend.SolucionesW.Services.CitasServicio;
import com.backend.SolucionesW.Services.ClienteService;
import com.backend.SolucionesW.Services.EstadoCitaSevice;
import com.backend.SolucionesW.Services.TipoCitaService;
import com.backend.SolucionesW.Services.VehiculoServicio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/citas")
@CrossOrigin("*")
public class CitasController {

    @Autowired
    private CitasServicio citasService;

    @Autowired
    private TipoCitaService tipoCitaService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EstadoCitaSevice estadoCitaSevice;

    @Autowired
    private VehiculoServicio vehiculoServicio;

    @GetMapping("/listar")
    public ResponseEntity<?> listarCitas(Authentication authentication) {
        Cliente cliente = (Cliente) authentication.getPrincipal();
        return ResponseEntity.ok(citasService.listarCitas(cliente));
    }

    @GetMapping("/todasLasCitas")
    public ArrayList<Citas> listarTodasLasCitas() {
        return (ArrayList<Citas>) citasService.Listar();
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> agregarCita(@RequestBody Citas citas, Authentication authentication) {
        Integer clienteId = ((Cliente) authentication.getPrincipal()).getId();

        if (citas == null) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Necesita ingresar los datos");
            return ResponseEntity.badRequest().body(response);
        }

        try {
            Cliente cliente = clienteService.Obtener(clienteId);
            if (cliente == null) {
                Map<String, Object> response = new HashMap<>();
                response.put("success", false);
                response.put("message", "No se encontró al cliente con ID: " + clienteId);
                return ResponseEntity.badRequest().body(response);
            }

            // Verificar existencia del vehículo
            Vehiculo vehiculo = vehiculoServicio.Obtener(citas.getVehiculo().getId());
            if (vehiculo == null) {
                Map<String, Object> response = new HashMap<>();
                response.put("success", false);
                response.put("message", "No se encontró el vehículo con ID: " + citas.getVehiculo().getId());
                return ResponseEntity.badRequest().body(response);
            }

            citas.setCliente(cliente);
            citas.setVehiculo(vehiculo);

            EstadoCita estadoCita = estadoCitaSevice.Obtener(1);
            citas.setEstadoCita(estadoCita);

            Citas citaGuardada = citasService.Guardar(citas);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Cita registrada correctamente");
            response.put("citaId", citaGuardada.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Error al registrar la cita: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @PutMapping("/actualizar/{id}")
    public String putMethodName(@PathVariable String id, @RequestBody String entity) {
        return entity;
    }

    @GetMapping("/obtener/{codigo}")
    public ResponseEntity<Optional<Citas>> obtenerCitaPorCodigo(@PathVariable Integer codigo) {
        Optional<Citas> cita = citasService.obtenerCitaPorCodigo(codigo);
        if (cita != null) {
            return ResponseEntity.ok(cita);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/tipoCita")
    public List<TipoCita> listarTiposCita() {
        return tipoCitaService.Listar();
    }

    @GetMapping("/existeCita/{id}")
    public ResponseEntity<Boolean> existeCitaId(@PathVariable Integer id) {
        boolean exists = citasService.existeId(id);
        return new ResponseEntity<>(exists, HttpStatus.OK);
    }
}
