package com.uniquindio.agendaespacio.rescontroller;


import com.uniquindio.agendaespacio.entity.EspacioSemestre;
import com.uniquindio.agendaespacio.entity.Semestre;
import com.uniquindio.agendaespacio.service.imp.EspacioSemetreService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/espacioSemestre")
public class EspacioSemestreRestController {

    @Autowired
    private EspacioSemetreService espacioSemetreService;

    @PostMapping("/crearEspacioSemestre")
    @Operation(summary = "Crear un espacio semestre", description = "Meotod que permite crear un espacio semestre")
    public ResponseEntity<List<EspacioSemestre>> crearEspacioSemestre(List<EspacioSemestre> listEs) {
        return  ResponseEntity.ok(espacioSemetreService.crearEspacioSemestre(listEs));
    }

    @GetMapping("/listarEspacioSemestre")
    @Operation(summary = "Listar espacio semestre", description = "Metodo que permite listar los espacios semestres creados")
    public ResponseEntity<List<EspacioSemestre>> listEspacioSemestre() {
        return  ResponseEntity.ok(espacioSemetreService.listarEspacioSemestre());
    }

    @DeleteMapping("/eliminarEspacioSemestre/{idEspacioSemestre}")
    @Operation(summary = "Eliminar espacio semestre", description = "Metodo que permite listar el espacio semestre")
    void eliminarEspacioSemestre(@PathVariable Integer idEspacioSemestre) {
        espacioSemetreService.eliminarEspacioSemestre(idEspacioSemestre);
    }

    @DeleteMapping("/eliminarPorSemestre/{idSemestre}")
    @Operation(summary = "Eliminar espacio por semestre", description = "Metodo que permite eliminar un espacio semestre por semestre")
    void  eliminarEspacioSemestrePorSemestre(@PathVariable  Integer idSemestre){
        Semestre semestre = new Semestre();
        semestre.setIdSemestre(idSemestre);
        espacioSemetreService.eiminarEspacioSemestrePorSemestre(semestre);
    }
}
