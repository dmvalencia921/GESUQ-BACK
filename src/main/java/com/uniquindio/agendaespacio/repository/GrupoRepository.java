package com.uniquindio.agendaespacio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uniquindio.agendaespacio.entity.Grupo;


@Repository
public interface GrupoRepository extends JpaRepository<Grupo,Integer> {

    List<Grupo> findByNombreGrupoIgnoreCase(String nombre);
    Grupo findOneByNombreGrupoAndIdGrupoNot(String nombre, Integer idGrupo);

}
