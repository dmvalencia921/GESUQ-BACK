package com.uniquindio.agendaespacio.repository;

import com.uniquindio.agendaespacio.entity.EspacioAcademico;
import com.uniquindio.agendaespacio.entity.EspacioSemestre;
import com.uniquindio.agendaespacio.entity.Semestre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EspacioSemestreRepository extends JpaRepository<EspacioSemestre, Integer> {
    List<EspacioSemestre> findByEspacioAcademico(EspacioAcademico academico);
    Optional<EspacioSemestre> findBySemestre(Semestre semestre);
}
