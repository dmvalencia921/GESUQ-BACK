package com.uniquindio.agendaespacio.service;

import java.util.List;

import com.uniquindio.agendaespacio.entity.Sede;

public interface ISedeService {

    Sede crearSede(Sede sede);
    Sede actualizarSede(Sede sede);
    List<Sede> listarSedes();
    void eliminarSede(Integer idSede);


}
