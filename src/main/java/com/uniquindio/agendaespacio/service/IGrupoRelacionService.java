package com.uniquindio.agendaespacio.service;

import java.util.List;

import com.uniquindio.agendaespacio.entity.Grupo;
import com.uniquindio.agendaespacio.entity.GrupoRelacion;

public interface IGrupoRelacionService {

GrupoRelacion crearGrupoRelacion(GrupoRelacion grupoRelacion);
List<GrupoRelacion>listarGrupoRelacion();
void eliminarGrupoRelacion(Integer idGrupoRelacion);
List<GrupoRelacion> crearGrupoRelacionMasivo(List<GrupoRelacion> gruposRelacion);
/* void eliminarGrupoRelacionporGrupo(Grupo grupo); */





}
