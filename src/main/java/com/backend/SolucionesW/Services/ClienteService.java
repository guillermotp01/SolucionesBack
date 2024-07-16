package com.backend.SolucionesW.Services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.SolucionesW.Models.Cliente;
import com.backend.SolucionesW.Repositories.ClienteRepository;


@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> Listar() {
        return clienteRepository.findAll();
    }

    public Cliente Guardar(Cliente u) {
        return clienteRepository.save(u);
    }
    
    public Cliente Obtener(Integer id) {
        return clienteRepository.findById(id).orElse(null);
    }

    public void Eliminar(Integer id) {
        clienteRepository.deleteById(id);
    }

    public Cliente Actualizar(Cliente request, Integer id){
        Cliente cliente = clienteRepository.findById(id).get();
        cliente.setNombre(request.getNombre());
        cliente.setApellido(request.getApellido());
        cliente.setTipoDocumento(request.getTipoDocumento());
        cliente.setNroDocumento(request.getNroDocumento());
        cliente.setCorreo(request.getCorreo());
        cliente.setUsername(request.getUsername());
        cliente.setPassword(request.getPassword());
        cliente.setDireccion(request.getDireccion());
        cliente.setTelefono(request.getTelefono());

        return clienteRepository.save(cliente);
    }

    public Cliente obtenerUsuario(String username) {
        return clienteRepository.findByUsername(username);
    }

    public boolean existeNombreUsuario(String username) {
        return clienteRepository.existsByUsername(username);
    }

    public boolean existeNombre(String nombre) {
        return clienteRepository.existsByNombre(nombre);
    }
}
