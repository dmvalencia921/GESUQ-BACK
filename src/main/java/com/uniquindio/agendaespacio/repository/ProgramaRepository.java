package com.uniquindio.agendaespacio.repository;

import com.uniquindio.agendaespacio.entity.Programa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgramaRepository extends JpaRepository<Programa, Integer> {

    List<Programa> findByNombreIgnoreCase(String nombre);
    Programa findOneByNombreAndIdProgramaNot(String nombre, Integer idPrograma);
}
