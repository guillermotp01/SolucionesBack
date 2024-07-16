package com.backend.SolucionesW.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
@Table(name = "empleado")
public class Empleado implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 18)
    private String nombre;

    @Column(nullable = false, length = 18)
    private String apellido;

    @Column(nullable = false)
    private Integer nroDocumento;

    @Column(nullable = false, length = 10)
    private String tipoDocumento;

    @Column(nullable = false)
    private Integer telefono;

    @Column(nullable = false, length = 50)
    private String correo;

    @Column(nullable = false, length = 30)
    private String username;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(nullable = false, length = 20)
    private String direccion;

    @ManyToOne
    @JoinColumn(name = "areaId", nullable = false)
    private Areas area;

    @JsonIgnore
    @OneToMany(mappedBy = "empleado")
    private List<SeguimientoCita> seguimientoCitas;

    @JsonIgnore
    @OneToMany(mappedBy = "empleado")
    private List<DetalleCita> detalleCitas;

    @JsonIgnore
    @OneToMany(mappedBy = "empleado")
    private List<FormatoDeInspeccion> formatoDeInspecciones;

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    Set<GrantedAuthority> authorities = new HashSet<>();
    authorities.add(new SimpleGrantedAuthority("empleado"));
    return authorities;
    }
}
