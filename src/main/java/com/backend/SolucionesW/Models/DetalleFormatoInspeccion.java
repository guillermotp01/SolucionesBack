package com.backend.SolucionesW.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "DetalleFormatoInspeccion")
public class DetalleFormatoInspeccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "formatoDeInspeccionId", nullable = false)
    private FormatoDeInspeccion formatoDeInspeccion;

    @ManyToOne
    @JoinColumn(name = "componentesId", nullable = false)
    private Componentes componentes;

    @ManyToOne
    @JoinColumn(name = "estadoConservacionId", nullable = false)
    private EstadoConservacion estadoConservacion;
}
