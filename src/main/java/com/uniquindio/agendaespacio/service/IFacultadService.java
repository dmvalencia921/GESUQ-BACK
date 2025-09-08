package com.uniquindio.agendaespacio.service;

import com.uniquindio.agendaespacio.entity.Facultad;

import java.util.List;

public interface IFacultadService {
    Facultad crearFacultad(Facultad facultad);
    Facultad actualizarFacultad(Facultad facultad);
    List<Facultad> listarFacultads();
    void eliminarFacultad(Integer idFacultad);
    List<Facultad> crearFacultadesMasivo(List<Facultad> facultades);
}
