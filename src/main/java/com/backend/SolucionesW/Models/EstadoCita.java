package com.backend.SolucionesW.Models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "EstadoCita")
public class EstadoCita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 20)
    private String nombre;
}
