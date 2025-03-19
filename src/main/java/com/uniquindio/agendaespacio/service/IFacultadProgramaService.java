package com.uniquindio.agendaespacio.service;

import com.uniquindio.agendaespacio.entity.FacultadPrograma;

import java.util.List;

public interface IFacultadProgramaService {

    List<FacultadPrograma> crearFacultadPrograma(List<FacultadPrograma> listaFp);
    List<FacultadPrograma> listarFacultadPrograma();
    List<FacultadPrograma> listarFacultadProgramaPorFacultad(Integer Idfacultad);
    void eliminarFacultadPrograma(String codPrograma);
}
