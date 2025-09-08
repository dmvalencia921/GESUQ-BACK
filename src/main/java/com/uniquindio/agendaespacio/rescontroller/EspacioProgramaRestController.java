package com.uniquindio.agendaespacio.rescontroller;

import com.uniquindio.agendaespacio.entity.EspacioAcademico;
import com.uniquindio.agendaespacio.entity.EspacioPrograma;
import com.uniquindio.agendaespacio.entity.Programa;
import com.uniquindio.agendaespacio.service.imp.EspacioProgramaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/espacioPrograma")
public class EspacioProgramaRestController {

    @Autowired
    private EspacioProgramaService espacioProgramaService;

    @PostMapping("/crearEspacioPrograma")
    @Operation(summary = "crear espacio programa", description = "Metodo usado para la creacion de espacio prorgama")
    public ResponseEntity<List<EspacioPrograma>> crearEspacioPrograma(@RequestBody List<EspacioPrograma> listaEspacioPrograma) {
        return ResponseEntity.ok(espacioProgramaService.crearEspacioPrograma(listaEspacioPrograma));
    }

    @GetMapping("/listarEspacioPrograma")
    @Operation(summary = "metodo para listar", description = "Metodo para listar los espacios por programa")
    public ResponseEntity<List<EspacioPrograma>> listarEspacioPrograma() {
        return ResponseEntity.ok(espacioProgramaService.listarEspacioPrograma());
    }

    @GetMapping("/listarPorPrograma/{idPrograma}")
    @Operation(summary = "listar por id de programa", description = "Metodo para listar por programa espacio programa")
    public ResponseEntity<List<EspacioPrograma>> listarEspacioProgramaPorPrograma(@PathVariable Integer idPrograma) {
        Programa programa = new Programa();
        programa.setIdPrograma(idPrograma);
        return  ResponseEntity.ok(espacioProgramaService.listarEspacioProgramaPorPrograma(programa));
    }

    @DeleteMapping("/eliminarEspacioPrograma/{idEspacioAcademico}")
    @Operation(summary = "eliminar espacio programa", description = "Metodo usado para eliminar un espacio programa por el cod programa")
    public void eliminarEspacioPrograma(@PathVariable Integer idEspacioAcademico) {
        EspacioAcademico espacioAcademico = new EspacioAcademico();
        espacioAcademico.setIdEspacioAcademico(idEspacioAcademico);
        espacioProgramaService.eliminarEspacioPrograma(espacioAcademico);
    }


    @DeleteMapping("/eliminarEspaProgramaporid/{idEspacioPrograma}")
    @Operation(summary = "eliminar espacio programa", description = "Metodo usado para eliminar un espacio programa por el id ")
    public void eliminarEspacioProgram(@PathVariable Integer idEspacioPrograma) {
        espacioProgramaService.eliminarEspaPrograma(idEspacioPrograma);
    }





}
