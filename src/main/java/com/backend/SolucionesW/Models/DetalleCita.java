package com.backend.SolucionesW.Models;

import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "detallecitas")
public class DetalleCita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String diagnostico;

    @Column(precision = 10, scale = 2)
    private BigDecimal costo;

    @ManyToOne
    @JoinColumn(name = "respuestosId")
    private Repuestos repuestos;

    @ManyToOne
    @JoinColumn(name = "serviciosId")
    private Servicios servicios;

    @ManyToOne
    @JoinColumn(name = "empleadoId")
    private Empleado empleado;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "citaId", nullable = false)
    private Citas cita;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "formatoDeInspeccionId", referencedColumnName = "id")
    private FormatoDeInspeccion formatoDeInspeccion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

    public Repuestos getRepuestos() {
        return repuestos;
    }

    public void setRepuestos(Repuestos repuestos) {
        this.repuestos = repuestos;
    }

    public Servicios getServicios() {
        return servicios;
    }

    public void setServicios(Servicios servicios) {
        this.servicios = servicios;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Citas getCita() {
        return cita;
    }

    public void setCita(Citas cita) {
        this.cita = cita;
    }

    public FormatoDeInspeccion getFormatoDeInspeccion() {
        return formatoDeInspeccion;
    }

    public void setFormatoDeInspeccion(FormatoDeInspeccion formatoDeInspeccion) {
        this.formatoDeInspeccion = formatoDeInspeccion;
    }
}
