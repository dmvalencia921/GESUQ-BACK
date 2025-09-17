package com.uniquindio.agendaespacio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.uniquindio.agendaespacio.entity.EspacioAcademico;
import com.uniquindio.agendaespacio.entity.EspacioPrograma;
import com.uniquindio.agendaespacio.entity.Facultad;
import com.uniquindio.agendaespacio.entity.Grupo;
import com.uniquindio.agendaespacio.entity.GrupoRelacion;
import com.uniquindio.agendaespacio.entity.Sede;

import java.util.Optional;

@Repository
public interface GrupoRelacionRepository extends JpaRepository<GrupoRelacion, Integer> {

    /* Optional<GrupoRelacion> findByGrupo(Grupo grupo); */
    /*
     * @Query("SELECT gr FROM GrupoRelacion gr WHERE gr.sede = :sede AND gr.facultad = :facultad AND gr.espacioPrograma = :espacioPrograma"
     * )
     * Optional<GrupoRelacion> findBySedeAndFacultadAndEspacioPrograma(
     * 
     * @Param("sede") Sede sede,
     * 
     * @Param("facultad") Facultad facultad,
     * 
     * @Param("espacioPrograma") EspacioPrograma espacioPrograma);
     */

    Optional<GrupoRelacion> findBySedeAndFacultadAndEspacioPrograma(Sede sede, Facultad facultad,
            EspacioPrograma espacioPrograma);

        Optional<GrupoRelacion> findByFacultad(Facultad facultad);

}
