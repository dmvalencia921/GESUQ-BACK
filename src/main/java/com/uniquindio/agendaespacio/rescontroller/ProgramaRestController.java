package com.uniquindio.agendaespacio.rescontroller;

import com.uniquindio.agendaespacio.entity.Programa;
import com.uniquindio.agendaespacio.entity.Usuario;
import com.uniquindio.agendaespacio.service.imp.ProgramaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/programa")
public class ProgramaRestController {

    @Autowired
    private ProgramaService programaService;

    @PostMapping("/crearPrograma")
    @Operation(summary = "Crear programa", description = "Metodo para crear un programa")
    public ResponseEntity<Programa> crearPrograma(@RequestBody Programa programa) {
        return ResponseEntity.ok(programaService.crearPrograma(programa));
    }

    @PutMapping("/actualizaPrograma")
    @Operation(summary = "Actualizar programa", description = "Metodo que permite actualiazar un programa")
    public ResponseEntity<Programa> actualizarPrograma(@RequestBody Programa programa) {
        return ResponseEntity.ok(programaService.actualizarPrograma(programa));
    }

    @GetMapping("/listarProgramas")
    @Operation(summary = "listar progrmas", description = "Metodo que permite listar los programas")
    public ResponseEntity<List<Programa>> listarProgramas() {
        return ResponseEntity.ok(programaService.listarProgramas());
    }


    @DeleteMapping("/eliminarPrograma/{idPrograma}")
    @Operation(summary = "eliminar programa", description = "Metodo para eliminar un programa por su id")
    public void eliminarPrograma(@PathVariable Integer idPrograma) {
        programaService.eliminarPrograma(idPrograma);
    }
}
