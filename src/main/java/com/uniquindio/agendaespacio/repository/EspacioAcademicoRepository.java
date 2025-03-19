package com.uniquindio.agendaespacio.repository;

import com.uniquindio.agendaespacio.entity.EspacioAcademico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EspacioAcademicoRepository extends JpaRepository<EspacioAcademico, Integer> {
    List<EspacioAcademico> findByNombreIgnoreCase(String nombre);
    EspacioAcademico findOneByNombreAndIdEspacioAcademicoNot(String nombre, Integer idEspacio);
}
