package com.backend.SolucionesW.Controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.backend.SolucionesW.Models.Componentes;
import com.backend.SolucionesW.Models.DetalleFormatoInspeccion;
import com.backend.SolucionesW.Models.EstadoConservacion;
import com.backend.SolucionesW.Services.ComponentesService;
import com.backend.SolucionesW.Services.DetalleFormatoInspeccionService;
import com.backend.SolucionesW.Services.EstadoConservacionService;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/detalleFormatoInspeccion")
@CrossOrigin("*")
public class DetalleFormatoInspeccionController {

    @Autowired
    private DetalleFormatoInspeccionService detalleFormatoInspeccionService;

    @Autowired
    private EstadoConservacionService estadoConservacionService;

    @Autowired
    private ComponentesService componentesService;

    @GetMapping("/listar")
    public ArrayList<DetalleFormatoInspeccion> listar() {
        return(ArrayList<DetalleFormatoInspeccion>) detalleFormatoInspeccionService.listar();
    }

    @PostMapping("/registrar")
    public DetalleFormatoInspeccion registrar(@RequestBody DetalleFormatoInspeccion detalle) {
        return this.detalleFormatoInspeccionService.Guardar(detalle);
    }

    @GetMapping("/listarComponentes")
    public ArrayList<Componentes> listarComponentes() {
        return(ArrayList<Componentes>) componentesService.Listar();
    }

    @GetMapping("/listarEstadoConservacion")
    public ArrayList<EstadoConservacion> listarEstadoConservacion() {
        return(ArrayList<EstadoConservacion>) estadoConservacionService.Listar();
    }
}
