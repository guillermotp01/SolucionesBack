package com.backend.SolucionesW.Models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "SeguimientoCita")
public class SeguimientoCita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "citaId", nullable = false)
    private Citas cita;

    @ManyToOne
    @JoinColumn(name = "estadoCitaId", nullable = false)
    private EstadoCita estadoCita;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecha;

    @Column(nullable = false)
    @Temporal(TemporalType.TIME)
    private Date hora;

    @ManyToOne
    @JoinColumn(name = "empleadoId", nullable = false)
    private Empleado empleado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    
}

