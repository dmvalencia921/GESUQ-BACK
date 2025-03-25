package com.uniquindio.agendaespacio.service;

import com.uniquindio.agendaespacio.entity.ReservaEspacio;
import com.uniquindio.agendaespacio.entity.Usuario;

import java.util.List;

public interface IReservaEspacioService {

    ReservaEspacio crearReservaEspacio(ReservaEspacio reserva);
    ReservaEspacio actualizarReserva(ReservaEspacio reserva);
    List<ReservaEspacio> listarReservas();
    List<ReservaEspacio> listarReservaPorUsuario(Usuario usuario);
}
