package com.backend.SolucionesW.Services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.backend.SolucionesW.Models.Cliente;
import com.backend.SolucionesW.Models.Empleado;
import com.backend.SolucionesW.Repositories.ClienteRepository;
import com.backend.SolucionesW.Repositories.EmpleadoRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Cliente cliente = clienteRepository.findByUsername(username);
        if (cliente != null) {
            return cliente;
        }

        Empleado empleado = empleadoRepository.findByUsername(username);
        if (empleado != null) {
            return empleado;
        }

        throw new UsernameNotFoundException("Usuario no encontrado");
    }
}