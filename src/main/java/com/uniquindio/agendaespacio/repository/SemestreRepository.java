package com.uniquindio.agendaespacio.repository;

import com.uniquindio.agendaespacio.entity.Semestre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SemestreRepository  extends JpaRepository<Semestre, Integer> {
    List<Semestre> findByNoSemestre(Integer noSemestre);
    Semestre findOneByNoSemestreAndIdSemestreNot(Integer Nosemestre, Integer idSemestre);
}
