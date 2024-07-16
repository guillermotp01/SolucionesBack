package com.backend.SolucionesW.Models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Marca")
public class Marca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 20)
    private String nombre;
}