package com.backend.SolucionesW.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.backend.SolucionesW.Models.Cliente;
import com.backend.SolucionesW.Models.Empleado;
import com.backend.SolucionesW.Repositories.ClienteRepository;
import com.backend.SolucionesW.Repositories.EmpleadoRepository;

@Component
public class CustomUserDetailsService implements AuthenticationProvider  {

@Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        // Primero intenta autenticar como cliente
        Cliente cliente = clienteRepository.findByUsername(username);
        if (cliente != null && password.equals(cliente.getPassword())) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            // Aquí puedes añadir roles y otros authorities
            return new UsernamePasswordAuthenticationToken(username, password, authorities);
        }

        // Si no es cliente, intenta autenticar como empleado
        Empleado empleado = empleadoRepository.findByUsername(username);
        if (empleado != null && password.equals(empleado.getPassword())) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            // Aquí puedes añadir roles y otros authorities
            return new UsernamePasswordAuthenticationToken(username, password, authorities);
        }

        // Si ninguna autenticación es exitosa, lanza excepción
        throw new BadCredentialsException("Credenciales inválidas");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
