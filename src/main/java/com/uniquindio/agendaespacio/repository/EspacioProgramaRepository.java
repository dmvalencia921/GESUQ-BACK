package com.uniquindio.agendaespacio.repository;



import com.uniquindio.agendaespacio.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EspacioProgramaRepository extends JpaRepository<EspacioPrograma, Integer> {

    boolean existsByEspacioAcademicoAndPrograma(EspacioAcademico espacio, Programa programa);
    List<EspacioPrograma> findByPrograma(Programa programa);
    Optional<EspacioPrograma>findByEspacioAcademico(EspacioAcademico espacio);

}
