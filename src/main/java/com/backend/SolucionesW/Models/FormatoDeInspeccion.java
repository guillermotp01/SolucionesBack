package com.backend.SolucionesW.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
@Table(name = "FormatoDeInspeccion")
public class FormatoDeInspeccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 20)
    private String nombre;

    @Column(nullable = false, length = 45)
    private String descripcion;

    @Column(nullable = false)
    @Temporal(TemporalType.TIME)
    private Date hora;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "empleadoId", nullable = false)
    private Empleado empleado;

    @JsonIgnore
    @OneToMany(mappedBy = "formatoDeInspeccion")
    private List<DetalleFormatoInspeccion> detalleFormatoInspecciones;

    @JsonIgnore
    @OneToOne(mappedBy = "formatoDeInspeccion")
    private DetalleCita detalleCita;
}
