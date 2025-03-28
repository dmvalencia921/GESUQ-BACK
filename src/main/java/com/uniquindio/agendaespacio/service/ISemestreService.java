package com.uniquindio.agendaespacio.service;

import com.uniquindio.agendaespacio.entity.Semestre;

import java.util.List;

public interface ISemestreService {
    Semestre crearSemestre(Semestre semestre);
    Semestre actualizarSemestre(Semestre semestre);
    List<Semestre> listarSemestre();
    void eliminarSemestre(Integer idSemestre);
}
