package com.uniquindio.agendaespacio.rescontroller;

import com.uniquindio.agendaespacio.entity.FacultadPrograma;
import com.uniquindio.agendaespacio.service.imp.FacultadProgramaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/facultadPrograma")
public class FacultadProgramaRestController {

    @Autowired
    private FacultadProgramaService facultadProgramaService;

    @PostMapping("/crearFacultaPrograma")
    @Operation(summary = "crear facultad programa", description = "Metodo usado para la creacion de facultad prorgama")
    public ResponseEntity<List<FacultadPrograma>> crearFacultadPrograma(@RequestBody  List<FacultadPrograma> listaFacultadPrograma) {
        return ResponseEntity.ok(facultadProgramaService.crearFacultadPrograma(listaFacultadPrograma));
    }

    @GetMapping("/listarFacultadPrograma")
    @Operation(summary = "metodo para listar", description = "Metodo para listar las facultades por programa")
    public ResponseEntity<List<FacultadPrograma>> listarFacultadPrograma() {
        return ResponseEntity.ok(facultadProgramaService.listarFacultadPrograma());
    }

    @GetMapping("/listarPorFacultad/{idFacultad}")
    @Operation(summary = "listar por id de facultad", description = "Metodo para listar por facultar programa")
    public ResponseEntity<List<FacultadPrograma>> listarFacultadProgramaPorFacultad(@PathVariable Integer idFacultad) {
        return  ResponseEntity.ok(facultadProgramaService.listarFacultadProgramaPorFacultad(idFacultad));
    }

    @DeleteMapping("/eliminarFacultadPrograma/{codPrograma}")
    @Operation(summary = "eliminar facultad programa", description = "Metodo usado para eliminar una facultad programa por el cod programa")
    public void eliminarFacultadPrograma(@PathVariable String codPrograma) {
        facultadProgramaService.eliminarFacultadPrograma(codPrograma);
    }
}
