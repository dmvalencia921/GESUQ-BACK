package com.uniquindio.agendaespacio.rescontroller;

import com.uniquindio.agendaespacio.entity.Rol;
import com.uniquindio.agendaespacio.service.imp.RolService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rol")
public class RolRestController {

    @Autowired
    private RolService rolService;

    @PostMapping("/crearRol")
    @Operation(summary = "crear rol", description = "Metodo usado para crear rol")
    public ResponseEntity<Rol> crearRol(@RequestBody  Rol rol) {
        return  ResponseEntity.ok(rolService.crearRol(rol));
    }

    @GetMapping("/listarRol")
    @Operation(summary = "listar roles", description = "Metodo usado para listar los roles")
    public ResponseEntity<List<Rol>> listarRol() {
        return  ResponseEntity.ok(rolService.listarRol());
    }

    @PutMapping("/actualizarRol")
    @Operation(summary = "actualizar rol", description = "metodo usado para actualizar rol")
    public ResponseEntity<Rol> actualizarRol(@RequestBody  Rol rol) {
        return  ResponseEntity.ok(rolService.actualizarRol(rol));
    }

    @DeleteMapping("/eliminarRol/{idRol}")
    @Operation(summary = "eliminar rol", description = "Metodo usado para eliminar el rol por su id")
    public void eliminarRol(@PathVariable  Integer idRol) {
        rolService.eliminarRol(idRol);
    }
}
