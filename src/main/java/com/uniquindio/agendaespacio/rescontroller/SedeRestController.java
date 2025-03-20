package com.uniquindio.agendaespacio.rescontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.uniquindio.agendaespacio.entity.Sede;
import com.uniquindio.agendaespacio.service.imp.SedeService;

@RestController
@RequestMapping("/api/sede")
public class SedeRestController {

    @Autowired
    private SedeService sedeService;

    @PostMapping("/crearSede")
    @Operation(summary = "Crear una sede", description = "Metodo usado para crear una sede")
    public ResponseEntity<Sede> crearSede(@RequestBody Sede sede) {

        return ResponseEntity.ok(sedeService.crearSede(sede));
    }

}
