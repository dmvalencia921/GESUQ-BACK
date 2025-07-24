package com.uniquindio.agendaespacio.rescontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.uniquindio.agendaespacio.entity.Grupo;
import com.uniquindio.agendaespacio.entity.GrupoRelacion;
import com.uniquindio.agendaespacio.service.imp.GrupoRelacionService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/grupoRelacion")
public class GrupoRelacionRestController {

    @Autowired
    private GrupoRelacionService grupoRelacionService;

    @PostMapping("/crearGrupoRelacion")
    @Operation(summary = "Crear grupo relacion", description = "Metodo que permite crear un grupo relacion")
    public ResponseEntity<GrupoRelacion> crearGrupoRelacion(@RequestBody GrupoRelacion grupoRelacion) {
        return ResponseEntity.ok(grupoRelacionService.crearGrupoRelacion(grupoRelacion));
    }

    @GetMapping("/listarGrupoRelacion")
    @Operation(summary = "Listar los grupos relacion", description = "Metodo que permite listar los grupos relacion")
    public ResponseEntity<List<GrupoRelacion>> listarGrupoRelacion() {
        return ResponseEntity.ok(grupoRelacionService.listarGrupoRelacion());
    }

    @DeleteMapping("/eliminarGrupoRelacion/{idGrupoRelacion}")
    @Operation(summary = "Eliminar grupo relacion", description = "Metodo que permite eliminar un grupo relacion por su id")
    public void eliminarGrupoRelacion(@PathVariable Integer idGrupoRelacion) {
        grupoRelacionService.eliminarGrupoRelacion(idGrupoRelacion);
    }

/*     @DeleteMapping("/eliminarGrupoRelacionporGrupo/{idGrupo}")
    @Operation(summary = "Eliminar por grupo", description = "Metodo que permite eliminar un grupo relacion por grupo")
    public void eliminarGrupoRelacionporGrupo(@PathVariable Integer idGrupo) {
        Grupo grupo = new Grupo();
        grupo.setIdGrupo(idGrupo);
        grupoRelacionService.eliminarGrupoRelacionporGrupo(grupo);
    } */

}
