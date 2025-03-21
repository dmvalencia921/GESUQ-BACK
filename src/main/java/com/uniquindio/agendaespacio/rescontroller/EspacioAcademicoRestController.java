package com.uniquindio.agendaespacio.rescontroller;

import com.uniquindio.agendaespacio.entity.EspacioAcademico;
import com.uniquindio.agendaespacio.service.imp.EspacioAcademicoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/espacioAcademico")
public class EspacioAcademicoRestController {

    @Autowired
    private EspacioAcademicoService espacioAcademicoService;

    @PostMapping("/crearEspacioAcademico")
    @Operation(summary = "Crear espacio academico", description = "Metodo que permite crear espacios academicos")
    public ResponseEntity<EspacioAcademico> crearEspacioAcademico(@RequestBody EspacioAcademico espacioAcademico) {
        return ResponseEntity.ok(espacioAcademicoService.crearEspacioAcademico(espacioAcademico));
    }

    @PutMapping("/actualizarEspacioAcademico")
    @Operation(summary = "Actualizar espacio academico", description = "Metodo que permite actualizar un espacio academico")
    public ResponseEntity<EspacioAcademico> actualiarEspacioAcademico(@RequestBody EspacioAcademico espacioAcademico) {
        return ResponseEntity.ok(espacioAcademicoService.actualizarEspacioAcademico(espacioAcademico));
    }

    @GetMapping("/listarEspacioAcademico")
    @Operation(summary = "Listar los espaciosa academicos", description = "Metodo que permite listar los espacios academicos")
    public ResponseEntity<List<EspacioAcademico>> listarEspacioAcademico() {
        return ResponseEntity.ok(espacioAcademicoService.listarEspacioAcademicos());
    }

    @DeleteMapping("/eliminarEspacioAcademico/{idEspacioAcademico}")
    @Operation(summary = "Eliminar espacio academico", description = "Metodod que permite eliminar por el id")
    public void eliminarEspacioAcademico( @PathVariable Integer idEspacioAcademico ) {
        espacioAcademicoService.eliminarEspacioAcademico(idEspacioAcademico);
    }
}
