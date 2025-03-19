package com.uniquindio.agendaespacio.rescontroller;

import com.uniquindio.agendaespacio.entity.Facultad;
import com.uniquindio.agendaespacio.service.imp.FacultadService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/facultad")
public class FacultadRestController {

    @Autowired
    private FacultadService facultadService;

    @PostMapping("/crearFacultad")
    @Operation(summary = "Crear una facultad", description = "Metodo usado para crear una facultad")
    public ResponseEntity<Facultad> crearFacultad(@RequestBody Facultad facultad) {
        return ResponseEntity.ok(facultadService.crearFacultad(facultad));
    }

    @GetMapping("/listarFacultad")
    @Operation(summary = "Listar facultades", description = "Metodo usado para listar las facultades")
    public ResponseEntity<List<Facultad>> listarFacultads() {
        return ResponseEntity.ok(facultadService.listarFacultads());
    }

    @PutMapping("/actualizarFacultad")
    @Operation(summary = "Actualizar facultad", description = "Metodo para actualizar una facultad")
    public ResponseEntity<Facultad> actulizarFacultad(@RequestBody Facultad facultad) {
        return ResponseEntity.ok(facultadService.actualizarFacultad(facultad));
    }

    @DeleteMapping("/eliminarFacultad/{idFacultad}")
    @Operation(summary = "Eliminar Facultad", description = "Metodo usado para eliminar facultad por su id")
    public void EliminarFacultad(@PathVariable Integer idFacultad) {
        facultadService.eliminarFacultad(idFacultad);
    }
}
