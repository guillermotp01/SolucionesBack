package com.backend.SolucionesW.Models;

import jakarta.persistence.*;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
@Entity
@Table(name = "vehiculo")
public class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 7, nullable = false, unique = true)
    private String placa;

    @Column(length = 15)
    private String color;
    
    @Column(nullable = false)
    private Integer fecha;    // año del auto

    @Column(length = 10)
    private String motor;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "clienteId", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "idModelo", nullable = false)
    private Modelo modelo;

    @JsonIgnore
    @OneToMany(mappedBy = "vehiculo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Citas> citas;
}
