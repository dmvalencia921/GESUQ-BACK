package com.uniquindio.agendaespacio.repository;

import com.uniquindio.agendaespacio.entity.Facultad;
import com.uniquindio.agendaespacio.entity.FacultadPrograma;
import com.uniquindio.agendaespacio.entity.Programa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FacultadProgramaRespository extends JpaRepository<FacultadPrograma, Integer> {

    List<FacultadPrograma> findByFacultad(Facultad facultad);
    Optional<FacultadPrograma>findByPrograma(Programa programa);

}
