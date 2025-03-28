package com.uniquindio.agendaespacio.service;

import com.uniquindio.agendaespacio.entity.Facultad;
import com.uniquindio.agendaespacio.entity.SedeFacultad;

import java.util.List;

public interface ISedeFacultadService {
    List<SedeFacultad> crearSedeFacultad(List<SedeFacultad> listaSf);
    List<SedeFacultad> listarSedeFacultad();
    void elminarSedeFacultad(Integer idSedeFacultad );
    void elminarSedeFacultadPorFacultad(Facultad facultad );
}
