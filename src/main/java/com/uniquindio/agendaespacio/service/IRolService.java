package com.uniquindio.agendaespacio.service;

import com.uniquindio.agendaespacio.entity.Rol;

import java.util.List;

public interface IRolService {

    Rol crearRol(Rol rol);
    List<Rol> listarRol();
    Rol actualizarRol(Rol rol);
    void eliminarRol(Integer idRol);
}
