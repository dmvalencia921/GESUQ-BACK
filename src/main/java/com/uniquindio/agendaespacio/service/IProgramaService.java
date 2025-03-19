package com.uniquindio.agendaespacio.service;

import com.uniquindio.agendaespacio.entity.Programa;

import java.util.List;

public interface IProgramaService {

    Programa crearPrograma(Programa programa);
    Programa actualizarPrograma(Programa programa);
    List<Programa> listarProgramas();
    void eliminarPrograma(Integer idPrograma);
}
