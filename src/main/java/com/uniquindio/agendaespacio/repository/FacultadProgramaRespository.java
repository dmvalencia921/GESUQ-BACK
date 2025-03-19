package com.uniquindio.agendaespacio.repository;

import com.uniquindio.agendaespacio.entity.FacultadPrograma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FacultadProgramaRespository extends JpaRepository<FacultadPrograma, Integer> {

    List<FacultadPrograma> findByIdFacultad(Integer idFacultad);
    Optional<FacultadPrograma> findByCodPrograma(String codPrograma);

}
