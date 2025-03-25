package com.uniquindio.agendaespacio.rescontroller;

import com.uniquindio.agendaespacio.entity.ReservaEspacio;
import com.uniquindio.agendaespacio.entity.Usuario;
import com.uniquindio.agendaespacio.service.imp.ReservaEspacioService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservaEspacio")
public class ReservaEspacioRestController {

    @Autowired
    private ReservaEspacioService reservaEspacioService;

    @PostMapping("/crearReserva")
    @Operation(summary = "Crear reserva ", description = "Metodo que permite realiar la reserva")
    public ResponseEntity<ReservaEspacio> crearReservaEspacio( @RequestBody ReservaEspacio reserva) {
        return ResponseEntity.ok(reservaEspacioService.crearReservaEspacio(reserva));
    }

    @GetMapping("/listaReserva")
    @Operation(summary = "Listar las reservas", description = "Metodo que permite listar las reservas")
    public ResponseEntity<List<ReservaEspacio>> listarReservaEspacio() {
        return ResponseEntity.ok(reservaEspacioService.listarReservas());
    }

    @GetMapping("/listarReservaPorUsuario/{noDocumento}")
    @Operation(summary = "listar por usuario", description = "Metodo que permite listar las reserva por usuario")
    public ResponseEntity<List<ReservaEspacio>> listarReservaPorUsuario(@PathVariable String noDocumento) {
        Usuario usuario = new Usuario();
        usuario.setNoDocumento(noDocumento);
        return  ResponseEntity.ok(reservaEspacioService.listarReservaPorUsuario(usuario));
    }
}
