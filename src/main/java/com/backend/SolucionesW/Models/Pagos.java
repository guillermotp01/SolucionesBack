package com.backend.SolucionesW.Models;

import java.math.BigDecimal;


import jakarta.persistence.*;

@Entity
@Table(name = "Pagos")
public class Pagos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 20)
    private String nombre;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal monto;

    @ManyToOne
    @JoinColumn(name = "metodoPagoId", nullable = false)
    private MetodoPago metodoPago;

    @ManyToOne
    @JoinColumn(name = "clienteId", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "citaId", nullable = false)
    private Citas cita;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getMonto() {
        return monto;
    }
}

