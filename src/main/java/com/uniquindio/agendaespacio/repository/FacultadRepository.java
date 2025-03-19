package com.uniquindio.agendaespacio.repository;

import com.uniquindio.agendaespacio.entity.Facultad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacultadRepository extends JpaRepository<Facultad, Integer> {

    List<Facultad> findByNombreFacultadIgnoreCase(String nombreFacultad);
    Facultad findOneByNombreFacultadAndIdFacultadNot(String nombreFacultad, Integer idFacultad);

}
