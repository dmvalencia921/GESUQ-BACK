package com.uniquindio.agendaespacio.rescontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;


import com.uniquindio.agendaespacio.entity.Grupo;
import com.uniquindio.agendaespacio.service.imp.GrupoService;



@RestController
@RequestMapping("/api/grupo")
public class GrupoRestController {

    @Autowired
    private GrupoService grupoService;

    @PostMapping("/crearGrupo")
    @Operation(summary = "Crear un grupo", description = "Metodo usado para crear un grupo")
    public ResponseEntity<Grupo> crearGrupo(@RequestBody Grupo grupo){

        return ResponseEntity.ok(grupoService.crearGrupo(grupo));  
    }

    @PutMapping("/actualizarGrupo")
    @Operation(summary = "Actualizar un grupo", description = "Metodo usado para actualizar un grupo")
    public ResponseEntity<Grupo> actualizarGrupo(@RequestBody Grupo grupo){

        return ResponseEntity.ok(grupoService.actualizarGrupo(grupo));
    }

    @GetMapping("/listarGrupos")
    @Operation(summary = "Listar grupos", description = "Metodo usado para listar los grupos")
    public ResponseEntity<List<Grupo>>listarGrupos(){

        return ResponseEntity.ok(grupoService.listaGrupos());
    }

    @DeleteMapping("/eliminarGrupo/{idGrupo}")
    @Operation(summary = "Eliminar un grupo", description = "Metodo usado para eliminar un grupo por su id")
    public void eliminarGrupo(@PathVariable Integer idGrupo){
        grupoService.eliminarGrupo(idGrupo);
    }

}
