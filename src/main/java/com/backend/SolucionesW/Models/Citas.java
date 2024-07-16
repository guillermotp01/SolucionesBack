package com.backend.SolucionesW.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.*;

@Data
@Entity
@Table(name = "citas")
public class Citas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;

    @Column(nullable = false)
    private String hora;

    @Column(nullable = true, precision = 10, scale = 2)
    private BigDecimal  precio;

    @Column(nullable = true, length = 100)
    private String observaciones;

    @ManyToOne
    @JoinColumn(name = "clienteId", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "idVehiculo", nullable = false)
    private Vehiculo vehiculo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tipoCitaId", nullable = false)
    private TipoCita tipoCita;

    @ManyToOne
    @JoinColumn(name = "estadoCitaId", nullable = false)
    private EstadoCita estadoCita;
}