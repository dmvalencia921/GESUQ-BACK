package com.uniquindio.agendaespacio.service;

import com.uniquindio.agendaespacio.entity.Facultad;
import com.uniquindio.agendaespacio.entity.FacultadPrograma;
import com.uniquindio.agendaespacio.entity.Programa;

import java.util.List;

public interface IFacultadProgramaService {

    List<FacultadPrograma> crearFacultadPrograma(List<FacultadPrograma> listaFp);
    List<FacultadPrograma> listarFacultadPrograma();
    List<FacultadPrograma> listarFacultadProgramaPorFacultad(Facultad facultad);
    void eliminarFacultadPrograma(Programa programa);
    void eliminarFaculPrograma(Integer idFacultadPrograma);
    List<FacultadPrograma> crearFacultadProgramasMasivo(List<FacultadPrograma> facultadProgramas);
}
