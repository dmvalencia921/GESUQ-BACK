package com.uniquindio.agendaespacio.rescontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import com.uniquindio.agendaespacio.entity.Sede;
import com.uniquindio.agendaespacio.service.imp.SedeService;


@RestController
@RequestMapping("/api/sede")
public class SedeRestController {

    @Autowired
    private SedeService sedeService;

    @PostMapping("/crearSede")
    @Operation(summary = "Crear una sede", description = "Metodo usado para crear una sede")
    public ResponseEntity<Sede> crearSede(@RequestBody Sede sede) {

        return ResponseEntity.ok(sedeService.crearSede(sede));
    }

    @PutMapping("/actualizarSede")
    @Operation(summary = "Actualizar sede", description = "Metodo para actualizar una sede")
    public ResponseEntity<Sede> actualizarSede(@RequestBody Sede sede){

        return ResponseEntity.ok(sedeService.actualizarSede(sede));
    }

    @GetMapping("/listarSedes")
    @Operation(summary = "Listar sedes", description = "Metodo usado para listar las sedes")    
    public ResponseEntity<List<Sede>> listarSedes(){

        return ResponseEntity.ok(sedeService.listarSedes());
    }

    @DeleteMapping("/eliminarSede/{idSede}")
    public void eliminarSede(@PathVariable Integer idSede){
        sedeService.eliminarSede(idSede);
    }

}
