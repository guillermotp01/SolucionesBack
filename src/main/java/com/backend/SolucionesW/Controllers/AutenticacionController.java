package com.backend.SolucionesW.Controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import com.backend.SolucionesW.Models.Cliente;
import com.backend.SolucionesW.Models.Empleado;
import com.backend.SolucionesW.Models.JwtRequest;
import com.backend.SolucionesW.Models.JwtResponse;
import com.backend.SolucionesW.Services.ClienteService;
import com.backend.SolucionesW.Services.EmpleadoService;
import com.backend.SolucionesW.Services.Impl.UserDetailsServiceImpl;
import com.backend.SolucionesW.config.JwtUtils;


@RestController
@RequestMapping("/validar")
@CrossOrigin("*")
public class AutenticacionController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private JwtUtils jwtUtils;

@PostMapping("/ingresar")
    public ResponseEntity<?> generarToken(@RequestBody JwtRequest jwtRequest) throws Exception {
    try {
        autenticar(jwtRequest.getUsername(), jwtRequest.getPassword());
    } catch (DisabledException exception) {
        throw new Exception("Usuario deshabilitado: " + exception.getMessage());
    } catch (BadCredentialsException e) {
        throw new Exception("Credenciales inválidas: " + e.getMessage());
    } catch (Exception exception) {
        throw new Exception("Error durante la autenticación: " + exception.getMessage());
    }

    UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
    String token = this.jwtUtils.generateToken(userDetails);
    return ResponseEntity.ok(new JwtResponse(token));
}

    private void autenticar(String username, String password) throws Exception {
        Cliente cliente = clienteService.obtenerUsuario(username);
        Empleado empleado = empleadoService.obtenerUsuario(username);

        if (cliente != null) {
        if (!passwordEncoder.matches(password, cliente.getPassword())) {
            throw new BadCredentialsException("Credenciales inválidas");
        }
        } else if (empleado != null) {
        if (!passwordEncoder.matches(password, empleado.getPassword())) {
            throw new BadCredentialsException("Credenciales inválidas");
        }
        } else {
        throw new UsernameNotFoundException("Usuario no encontrado");
    }
    }

    @GetMapping("/actual-usuario")
    public ResponseEntity<?> obtenerUsuarioActual(Principal principal) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(principal.getName());
        if (userDetails instanceof Cliente) {
            return ResponseEntity.ok((Cliente) userDetails);
        } else if (userDetails instanceof Empleado) {
            return ResponseEntity.ok((Empleado) userDetails);
        }
        return ResponseEntity.badRequest().body("Usuario no encontrado");
    }
}
