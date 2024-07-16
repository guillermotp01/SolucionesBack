package com.backend.SolucionesW.Controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.backend.SolucionesW.Models.Cliente;
import com.backend.SolucionesW.Models.Marca;
import com.backend.SolucionesW.Models.Modelo;
import com.backend.SolucionesW.Models.Vehiculo;
import com.backend.SolucionesW.Services.ClienteService;
import com.backend.SolucionesW.Services.MarcaService;
import com.backend.SolucionesW.Services.ModeloService;
import com.backend.SolucionesW.Services.VehiculoServicio;

@RestController
@RequestMapping("/vehiculo")
@CrossOrigin("*")
public class VehiculoController {

    @Autowired
    private VehiculoServicio vehiculoService;

    @Autowired
    private ModeloService modeloService;

    @Autowired
    private MarcaService marcaService;

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/listar")
    public ResponseEntity<?> listarVehiculos(Authentication authentication) {
        Cliente cliente = (Cliente) authentication.getPrincipal();
        return ResponseEntity.ok(vehiculoService.listarVehiculos(cliente));
    }

    @PostMapping("/agregar")
    public ResponseEntity<?> agregarVehiculo(@RequestBody Vehiculo vehiculo, Authentication authentication) {
        Integer clienteId = ((Cliente) authentication.getPrincipal()).getId();

        if (vehiculo == null) {
            return ResponseEntity.badRequest().body("El cuerpo de la solicitud 'vehiculo' es requerido");
        }

        try {
            Cliente cliente = clienteService.Obtener(clienteId);
            if (cliente == null) {
                return ResponseEntity.badRequest().body("No se encontró al cliente con ID: " + clienteId);
            }
            vehiculo.setCliente(cliente);

            Vehiculo vehiculoGuardado = vehiculoService.Guardar(vehiculo);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Vehículo registrado correctamente");
            response.put("vehiculoId", vehiculoGuardado.getId()); // Puedes agregar más datos si necesitas

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Error al registrar el vehículo: " + e.getMessage());

            return ResponseEntity.badRequest().body(response);
        }
    }
    

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable("id") Integer vehiculoId) {
        vehiculoService.Eliminar(vehiculoId);
    }

    @GetMapping("/obtener/{id}")
    public ResponseEntity<Vehiculo> obtenerVehiculoPorId(@PathVariable Integer id) {
        Vehiculo vehiculo = vehiculoService.Obtener(id);
        if (vehiculo != null) {
            return ResponseEntity.ok(vehiculo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Vehiculo> actualizar(@PathVariable("id") Integer id, @RequestBody Vehiculo vehiculo) {
        Vehiculo vehiculoExistente = vehiculoService.Obtener(id);
        if (vehiculoExistente == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        vehiculoExistente.setPlaca(vehiculo.getPlaca());
        vehiculoExistente.setColor(vehiculo.getColor());
        vehiculoExistente.setFecha(vehiculo.getFecha());
        vehiculoExistente.setMotor(vehiculo.getMotor());

        if (vehiculo.getCliente() != null && vehiculo.getCliente().getId() != null) {
            Cliente cliente = clienteService.Obtener(vehiculo.getCliente().getId());
            if (cliente != null) {
                vehiculoExistente.setCliente(cliente);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        Vehiculo vehiculoActualizado = vehiculoService.Guardar(vehiculoExistente);
        return new ResponseEntity<>(vehiculoActualizado, HttpStatus.OK);
    }

    @GetMapping("/modelos")
    public List<Modelo> getModelos() {
        return modeloService.Listar();
    }

    @GetMapping("/marcas")
    public List<Marca> getMarcas() {
        return marcaService.Listar();
    }
}
