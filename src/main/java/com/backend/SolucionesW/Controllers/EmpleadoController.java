package com.backend.SolucionesW.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.SolucionesW.Models.Empleado;
import com.backend.SolucionesW.Services.EmpleadoService;

@RestController
@RequestMapping("/empleado")
@CrossOrigin("*")
public class EmpleadoController {


    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/obtener/{username}")
    public Empleado obtenerUsuario(@PathVariable("username") String username) {
            return empleadoService.obtenerUsuario(username);
    }

    @PostMapping("/registrar")
    public Empleado registrarEmpleado(@RequestBody Empleado empleado) {
        String passwordEncriptado = passwordEncoder.encode(empleado.getPassword());
        empleado.setPassword(passwordEncriptado);
        return this.empleadoService.Guardar(empleado);
    }
}
