package com.backend.SolucionesW.Controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.SolucionesW.Models.Cliente;
import com.backend.SolucionesW.Services.ClienteService;

@RestController
@RequestMapping("/cliente")
@CrossOrigin("*")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    
    @GetMapping("/listar")
    public ArrayList<Cliente> listar() {
        return (ArrayList<Cliente>) clienteService.Listar();
    }

    @PostMapping("/registrar")
    public Cliente registrarCliente(@RequestBody Cliente cliente) {
        String passwordEncriptado = passwordEncoder.encode(cliente.getPassword());
        cliente.setPassword(passwordEncriptado);
        return this.clienteService.Guardar(cliente);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarUsuario(@PathVariable("id") Integer usuarioId) {
        clienteService.Eliminar(usuarioId);
    }

    @GetMapping("/obtener/{username}")
    public Cliente obtenerUsuario(@PathVariable("username") String username) {
            return clienteService.obtenerUsuario(username);
    }

    @PutMapping("/actualizar/{id}")
    public Cliente Actualizar( @RequestBody Cliente cliente, @PathVariable("id") int id){
        return this.clienteService.Actualizar(cliente, id);
    }

    @GetMapping("/existeUsuario/{username}")
    public ResponseEntity<Boolean> existeNombreUsuario(@PathVariable String username) {
        boolean exists = clienteService.existeNombreUsuario(username);
        return new ResponseEntity<>(exists, HttpStatus.OK);
    }

    @GetMapping("/existe/{nombre}")
    public ResponseEntity<Boolean> existeNombre(@PathVariable String nombre) {
        boolean exists = clienteService.existeNombre(nombre);
        return new ResponseEntity<>(exists, HttpStatus.OK);
    }
}
