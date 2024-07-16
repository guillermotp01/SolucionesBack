package com.backend.SolucionesW.Models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Modelo")
public class Modelo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 45)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "marcaId", nullable = false)
    private Marca marca;
}