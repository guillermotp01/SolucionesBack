package com.backend.SolucionesW.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
@Table(name = "servicios")
public class Servicios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 20)
    private String nombre;

    @Column(nullable = false)
    @Temporal(TemporalType.TIME)
    private Date duracion;

    @JsonIgnore
    @OneToMany(mappedBy = "servicios")
    private List<DetalleCita> detalleCitas;
}
