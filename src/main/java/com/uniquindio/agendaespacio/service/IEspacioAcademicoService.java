package com.uniquindio.agendaespacio.service;

import com.uniquindio.agendaespacio.entity.EspacioAcademico;

import java.util.List;

public interface IEspacioAcademicoService {
    EspacioAcademico crearEspacioAcademico(EspacioAcademico espacioAcademico);
    EspacioAcademico actualizarEspacioAcademico(EspacioAcademico espacio);
    List<EspacioAcademico> listarEspacioAcademicos();
    void eliminarEspacioAcademico(Integer idEspacioAcademico);
    List<EspacioAcademico> crearEspaciosAcademicosMasivo(List<EspacioAcademico> espaciosAcademicos);
}
