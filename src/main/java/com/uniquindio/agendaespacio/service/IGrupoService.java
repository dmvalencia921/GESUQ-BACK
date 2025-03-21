package com.uniquindio.agendaespacio.service;

import java.util.List;

import com.uniquindio.agendaespacio.entity.Grupo;

public interface IGrupoService {

    Grupo crearGrupo(Grupo grupo);
    Grupo actualizarGrupo(Grupo grupo);
    List<Grupo> listaGrupos();
    void eliminarGrupo(Integer idGrupo);

}
