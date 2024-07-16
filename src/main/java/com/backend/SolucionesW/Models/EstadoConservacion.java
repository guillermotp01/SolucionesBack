package com.backend.SolucionesW.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
@Table(name = "EstadoConservacion")
public class EstadoConservacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 45)
    private String nombre;

    @JsonIgnore
    @OneToMany(mappedBy = "estadoConservacion")
    private List<DetalleFormatoInspeccion> detalleFormatoInspecciones;
}