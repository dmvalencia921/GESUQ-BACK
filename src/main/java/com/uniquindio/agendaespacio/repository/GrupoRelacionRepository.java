package com.uniquindio.agendaespacio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uniquindio.agendaespacio.entity.EspacioAcademico;
import com.uniquindio.agendaespacio.entity.Facultad;
import com.uniquindio.agendaespacio.entity.Grupo;
import com.uniquindio.agendaespacio.entity.GrupoRelacion;
import com.uniquindio.agendaespacio.entity.Sede;

import java.util.Optional;


@Repository
public interface GrupoRelacionRepository extends JpaRepository<GrupoRelacion,Integer> {

    Optional<GrupoRelacion> findByGrupo(Grupo grupo);
    Optional<GrupoRelacion> findByGrupoAndSedeAndEspacioAcademicoAndFacultad(Grupo grupo,Sede sede,EspacioAcademico espacioAcademico,Facultad facultad);
  

}
