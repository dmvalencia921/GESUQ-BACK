package com.uniquindio.agendaespacio.service;

import com.uniquindio.agendaespacio.entity.EspacioSemestre;
import com.uniquindio.agendaespacio.entity.Semestre;

import java.util.List;

public interface IEspacioSemestreService {
    List<EspacioSemestre> crearEspacioSemestre(List<EspacioSemestre> listaEs);
    List<EspacioSemestre> listarEspacioSemestre();
    void eliminarEspacioSemestre(Integer idEspacioSemestre);
    void eiminarEspacioSemestrePorSemestre(Semestre semestre);
}
