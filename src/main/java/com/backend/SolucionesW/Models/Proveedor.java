package com.backend.SolucionesW.Models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "proveedor")
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 20)
    private String nombre;

    @Column(length = 10)
    private String telefono;

    @Column(length = 45)
    private String direccion;

    @OneToMany(mappedBy = "proveedor")
    private List<Repuestos> repuestos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Repuestos> getRepuestos() {
        return repuestos;
    }

    public void setRepuestos(List<Repuestos> repuestos) {
        this.repuestos = repuestos;
    }

    
}
