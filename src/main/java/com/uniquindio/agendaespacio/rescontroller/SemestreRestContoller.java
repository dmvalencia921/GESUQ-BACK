package com.uniquindio.agendaespacio.rescontroller;

import com.uniquindio.agendaespacio.entity.Semestre;
import com.uniquindio.agendaespacio.service.imp.SemestreService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/semestre")
public class SemestreRestContoller {

    @Autowired
    private SemestreService semestreService;

    @PostMapping("/crearSemetre")
    @Operation(summary = "Crear semestre", description = "Metodo encargado de crear el semestre")
    public ResponseEntity<Semestre> crearSemestre(@RequestBody Semestre semestre) {
        return ResponseEntity.ok(semestreService.crearSemestre(semestre));
    }

    @PutMapping("/actalizarSemestre")
    @Operation(summary = "Actualizar semestre" , description = "")
    public  ResponseEntity<Semestre> actualizarSemestre(@RequestBody Semestre semestre) {
        return ResponseEntity.ok(semestreService.actualizarSemestre(semestre));
    }

    @GetMapping("/listarSemestre")
    @Operation(summary = "Listar semestres", description = "Metodo que permite listar lo semestres creados")
    public ResponseEntity<List<Semestre>> listarSemestres() {
        return ResponseEntity.ok(semestreService.listarSemestre());
    }

    @DeleteMapping("/eliminarSemestre/{idSemestre}")
    @Operation(summary = "Eliminar semestre", description = "Metodo que permite eliminar el semestre por el id")
    public void  eliminarSemestre(@PathVariable Integer idSemestre) {
        semestreService.eliminarSemestre(idSemestre);
    }
}
