package com.uniquindio.agendaespacio.rescontroller;

import com.uniquindio.agendaespacio.entity.Facultad;
import com.uniquindio.agendaespacio.entity.SedeFacultad;
import com.uniquindio.agendaespacio.service.imp.SedeFacultadService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sedeFacultad")
public class SedeFacultadRescontroller {

    @Autowired
    private SedeFacultadService sedeFacultadService;

    @PostMapping("/crearSedeFaculta")
    @Operation(summary = "Crear sede facultad", description = "Metodo que permite crear una sede facultad")
    public ResponseEntity<List<SedeFacultad>> crearSedeFacultad(@RequestBody List<SedeFacultad> listaSedeFacultad) {
        return ResponseEntity.ok(sedeFacultadService.crearSedeFacultad(listaSedeFacultad));
    }


    @GetMapping("/listarSedeFacultad")
    @Operation(summary = "Listar sede facultad", description = "Metodo que permite listar todas las sedes facultades")
    public ResponseEntity<List<SedeFacultad>> listarSedeFacultad() {
        return ResponseEntity.ok(sedeFacultadService.listarSedeFacultad());
    }

    @DeleteMapping("/eliminarSedeFaculta/{idSedeFacultad}")
    @Operation(summary = "Eliminar sede facultad", description = "Metodo que permite eliminar una sede faculta por su id")
    public void eliminarSedeFacultad(@PathVariable  Integer idSedeFacultad) {
        sedeFacultadService.elminarSedeFacultad(idSedeFacultad);
    }

    @DeleteMapping("/eliminarPorFacultad/{idFacultad}")
    @Operation(summary = "Eliminar porfacultad", description = "Metodo que permite eliminar una sede facultad por la facultad")
    public  void elminarSedeFacultadPorFacultad(@PathVariable  Integer idFacultad){
        Facultad facultad = new Facultad();
        facultad.setIdFacultad(idFacultad);
        sedeFacultadService.elminarSedeFacultadPorFacultad(facultad);
    }
}
