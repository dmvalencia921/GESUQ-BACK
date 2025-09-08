package com.uniquindio.agendaespacio.repository;

import com.uniquindio.agendaespacio.entity.Facultad;
import com.uniquindio.agendaespacio.entity.Sede;
import com.uniquindio.agendaespacio.entity.SedeFacultad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SedeFacultadRepository extends JpaRepository<SedeFacultad, Integer> {

    List<SedeFacultad> findBySede(Sede sede);
    Optional<SedeFacultad> findByFacultad(Facultad facultad);
    Optional<SedeFacultad> findBySedeAndFacultad(Sede sede, Facultad facultad);
}
