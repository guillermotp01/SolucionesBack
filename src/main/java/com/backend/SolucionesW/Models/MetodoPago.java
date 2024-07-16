package com.backend.SolucionesW.Models;

import jakarta.persistence.*;
import java.util.List;


@Entity
@Table(name = "metodoPago")
public class MetodoPago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 10)
    private String metodo;

    @OneToMany(mappedBy = "metodoPago")
    private List<Pagos> pagos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public List<Pagos> getPagos() {
        return pagos;
    }

    public void setPagos(List<Pagos> pagos) {
        this.pagos = pagos;
    }

    
}